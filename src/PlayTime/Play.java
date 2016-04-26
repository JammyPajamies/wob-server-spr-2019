/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayTime;

import core.GameServer;
import core.NetworkManager;
import db.PlayDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metadata.Constants;
import model.Player;
import net.Response.ResponseSDPosition;
import utility.Log;
import net.Response.ResponseSDStartGame;
// TODO: import dataAccessLayer.RaceDAO;
/**
 *
 * @author anu
 */
public class Play {

//    private 
    private Map<Integer, PlayTimePlayer> rPlayers = new HashMap<Integer, PlayTimePlayer>(); //player_id ->PlayerInformation

    private int playID;
    private int mapID;

    private short playersReadyToStart;
    
    public Play(int playID) {
        this.playID = playID;
        
        try {
            PlayDAO.createPlay(playID);
        } catch (SQLException e) {
            Log.println_e("Error in writing record of play " + playID + " into database.");
            Log.println_e(e.getMessage());
        }
        
    }
    
    /**
     * adds a player to this game as a new PlayTimePlayer object in the player map.
     * if a player is being added that is already present, in the case of a reconnection, 
     * the play will send the in-game information to the player.
     * @param player 
     * @throws IOException
     */
    public void addPlayer(Player player) throws IOException{
        try{
            //will throw null pointer exception if player_id is not in rplayers.
            PlayTimePlayer ptp = rPlayers.get(player.getPlayer_id()); 
            //Log.printf_e("Player %i is reconnecting to game %i", player.getPlayer_id(), playID);
            ResponseSDPosition response = new ResponseSDPosition();
            response.setX(ptp.getX());
            response.setY(ptp.getY());
            for (int p_id : getPlayers().keySet()) {
                GameServer.getInstance().getThreadByPlayerID(p_id).send(response);
            }
        }catch(Exception ex){
            
            this.rPlayers.put(player.getPlayer_id(), new PlayTimePlayer(player.getPlayer_id(), playID));
        
            try {
                PlayDAO.createPlayer(player.getPlayer_id(), playID,0);
            } catch (SQLException e) {
                Log.println_e("Error in writing record of player ID " + player.getPlayer_id()+ " in play ID " + playID + " into database.");
                Log.println_e(e.getMessage());
            }
        }
    }

    public Play(List<Player> players, int playID) {
        this.playID = playID;
        int i=1;
        
        try
        {
            PlayDAO.createPlay(playID);
        }
        catch (SQLException e)
        {
            Log.println_e("Error in writing record of race " + playID + " into database.");
            Log.println_e(e.getMessage());
        }
        
        for (Player player : players) {
            this.rPlayers.put(player.getPlayer_id(), new PlayTimePlayer(player.getPlayer_id(), playID));
            
            
            try
            {
                PlayDAO.createPlayer(player.getPlayer_id(), playID,i);
                i++;
            }
            catch (SQLException e)
            {
                Log.println_e("Error in writing record of player ID " + player.getPlayer_id()+ " in race ID " + playID + " into database.");
                Log.println_e(e.getMessage());
            }
             
        }
    }

    public int getID() {
        return this.playID;
    }

    public Map<Integer, PlayTimePlayer> getPlayers() {
        return rPlayers;
    }
    
    public PlayTimePlayer getPlayer(int player_id){
        try{
        return rPlayers.get(player_id);
        }catch(Exception e){
            return null;
        }
    }

    public PlayTimePlayer getOpponent(Player racePlayer) {

        for (PlayTimePlayer player : rPlayers.values()) {
            if (player.getPlayer_id() != racePlayer.getPlayer_id()) {
                return player;
            }
        }

        return null; // error
    }

    public int getOpponentID(int playerID) {

        for (PlayTimePlayer player : rPlayers.values()) {
            if (player.getPlayer_id() != playerID) {
                return player.getPlayer_id();
            }
        }

        return -1; // error
    }

    // USSAGE: Called by RequestRRStartGame.
    // Sends an output to the clients of this race to start the countdown 
    // sequence to the start of a race.
    public void startPlay(int player_id) throws IOException {

        for (int p_id : getPlayers().keySet()) {
            if (p_id == player_id) {
                playersReadyToStart++;
            }
        }

        if (playersReadyToStart == Constants.MAX_NUMBER_OF_PLAYERS) {
            ResponseSDStartGame responseStart = new ResponseSDStartGame();
            for (int p_id : getPlayers().keySet()) {
                //NetworkManager.addResponseForUser(p_id, responseStart);
                // changed to this to reduce start game lag
                // this change made it almost simultaneous start
                GameServer.getInstance().getThreadByPlayerID(p_id).send(responseStart);
            }
        }
    }

    /*
    public void setWinningScore(int playerID, float finalScore) {

        PlayTimePlayer temp = this.rPlayers.get(playerID);
        temp.setFinalScore(finalScore);
        this.rPlayers.put(raceID, temp);
    }
*/

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }


}
