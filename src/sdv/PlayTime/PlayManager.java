/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.PlayTime;

import sdv.core.GameServer;
import sdv.core.NetworkManager;
import sdv.db.PlayDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import shared.metadata.Constants;
import sdv.model.Player;
import sdv.net.Response.ResponsePlayInit;
import sdv.net.Response.ResponseSDEndGame;
import sdv.net.Response.ResponseSDReconnect;
import shared.util.Log;

/**
 *
 * @author anu
 */
public class PlayManager {

    // Singleton Instance
    public static PlayManager manager;
    
    private int lastPlayId = -1;

    // Regerence Tables
    private Map<Integer, Play> playList = new HashMap<Integer, Play>(); //PlayID -> play
    public static Map<Integer, Play> playerPlayList; //PlayerID -> play

    private List<Player> players = new ArrayList<Player>(); //used to create a play

    public static PlayManager getInstance() {
        if (manager == null) {
            manager = new PlayManager();
            playerPlayList = new LinkedHashMap<Integer, Play>(20, 0.75f);
        }

        return manager;
    }

    public Play createPlay(int player_id) {
        Play play = null;
        players.clear();

//        if (players.isEmpty()) {
//            players.add(GameServer.getInstance().getActivePlayer(player_id));
//        } else {
//            if (player_id != players.get(0).getPlayer_id()) {
                // random generator used for generating map
            	
            		if(lastPlayId == -1) {
            			lastPlayId = 102;
            		} else {
            			lastPlayId++;
            		}
                Random randomGenerator = new Random();
                // create playID randomly
                int playID = lastPlayId;
                
                System.out.println("Play ID:" + playID);
                players.add(GameServer.getInstance().getActivePlayer(player_id));
                play = new Play(players, playID);  // fix 2nd parameter
                play.setMapID(randomGenerator.nextInt(101));
                play.playerId = player_id;
                //System.out.println("Map ID:" + play.getMapID());
                add(play);
                // Respond to Players to load the Runner scene
                ResponsePlayInit response = new ResponsePlayInit();
                for (int p_id : play.getPlayers().keySet()) {
                    NetworkManager.addResponseForUser(p_id, response);
                }
//                players.clear();
//            }

//        }
        return play;
    }

    public Play createPlay(int player_id, int playID) throws IOException{
      synchronized(this) {  // synchronized block added by Rupal on 3-30-2017
        // Play play = this.playList.get(playID);
        Play play = null;
        if(playerPlayList.size() % 2 == 0) {
        		// even number of players
        		// start new game
        		return createPlay(player_id);
        } else {
        		// odd number of players
        		// add player to last game
        	
        		Iterator it = playerPlayList.entrySet().iterator();
            while (it.hasNext()) {
            		Map.Entry pair = (Entry) it.next();
            		play = (Play) pair.getValue();
            }
        }
        
        if(play != null && play.getPlayers().size() == Constants.MAX_NUMBER_OF_PLAYERS) {
            
	    		// start a new play.
	    		// previous play has 2 players already.
	    	
	    		return createPlay(player_id);
	    	
	    }
        
        if (play == null) {

            System.out.println("Creating a Play with id = [" + playID + "]");
            play = new Play(playID);
            play.playerId = player_id;
            Random randomGenerator = new Random();
            play.setMapID(randomGenerator.nextInt(101));
            playList.put(play.getID(), play);
        } else {
            System.out.println("Play with id = [" + playID + "] "
                    + "already exists, add player " + player_id);
            play.opponentId = player_id;
        }
        
        play.addPlayer(GameServer.getInstance().getActivePlayer(player_id));
        playerPlayList.put(player_id, play);
        
        //sends playinit response to both users
            
        return play;
      }
    }

    /**
     * This method will end a play and delete the existing instances of a play
     *
     * @param playID is the caller's play ID i.e. race id
     * @param playerID is the caller's player ID
     * @throws Exception
     * @throws SQLException
     */
    public void endPlay(int playID, int playerID, float finalscore, int status) throws SQLException,Exception {
        Play play = playList.get(playID);
        playList.remove(playID);
        
        // check if play exists
        // this eliminates loser calling end play
        if (play != null) {
            int opponentID = play.getOpponentID(playerID);

            // remove play instances
            playerPlayList.remove(playerID);
            playerPlayList.remove(opponentID);
            
            // create resposes
            ResponseSDEndGame response = new ResponseSDEndGame();
            response.setHighestScore(finalscore);

            // set the winner player 
            response.setWinningPlayer(String.valueOf(playerID));
 
            // send responses to both players
            for (int p_id : play.getPlayers().keySet()) {
                if (playerID == p_id) {
                    response.setStatus(1); // status '1' means you won
                } else if (status ==3){
                 response.setStatus(3);  // status '3' means match draw
                } 
                else {
                    response.setStatus(2); // status '2' means you lost
                }
                GameServer.getInstance().getThreadByPlayerID(p_id).send(response);
            }
            //update database that the players have left the room.
            try {
                PlayDAO.leavePlay(playerID, playID);
            } catch (SQLException e) {
                Log.println_e("Error in removing record of player ID " + playerID+ " in race ID " + playID + " from database.");
                Log.println_e(e.getMessage());
            }
            try {
                PlayDAO.leavePlay(opponentID, playID);
            } catch (SQLException e) {
                Log.println_e("Error in removing record of player ID " + opponentID+ " in race ID " + playID + " from database.");
                Log.println_e(e.getMessage());
            }
            
            try{
                PlayDAO.endPlay(playID, playerID, finalscore);
            }catch(SQLException e){}
            
            //System.out.println("endRace");
        }
    }
    
    public void removePlayerFromPlayList(int player_id) {
        playerPlayList.remove(player_id);
    }

    public void destroyPlay(int play_id) {
        playList.remove(play_id);
    }

    public Play add(Play play) {
        for (int id : play.getPlayers().keySet()) {
            playerPlayList.put(id, play);
        }
        return playList.put(play.getID(), play);
    }

    public Play getPlayByPlayerID(int playerID) {
        try{
        return playerPlayList.get(playerID);
        }catch(Exception e){
            return null;
        }
    }


}
