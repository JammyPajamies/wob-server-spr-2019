
package sdv.net.Request;

import sdv.PlayTime.PlayManager;
import sdv.core.GameServer;
import java.io.IOException;
import java.util.HashMap;
import sdv.model.Prey;
import sdv.net.Response.ResponseNpcFishPosition;
import shared.util.DataReader;

/**
 * an update sent by the host client for movement of NPC fish.
 * @author Karl
 */
public class RequestNpcFishPosition extends GameRequest {
    private HashMap<Integer, Prey> fishMap = new HashMap<Integer, Prey>(); //prey_id -> prey object
    private int numFish;  //the number of fish in the request.
    private ResponseNpcFishPosition response;
    private int p_id;
    
    @Override
    public void parse() throws IOException {
        numFish = Integer.parseInt(DataReader.readString(dataInput));
        // System.out.println("Num fish is " + numFish);
        
        Prey fish;
        while(numFish > 0) {
        		try {
        			fish = new Prey();
                    int preyID = Integer.parseInt(DataReader.readString(dataInput));
                    // System.out.println("Prey id: " + preyID);
                    fish.setPrey_id(preyID);
                    fish.setSpecies_id(Integer.parseInt(DataReader.readString(dataInput)));
                    fish.setX(Float.parseFloat(DataReader.readString(dataInput)));
                    fish.setY(Float.parseFloat(DataReader.readString(dataInput)));
                    fish.setRotation(Float.parseFloat(DataReader.readString(dataInput)));
                    fishMap.put(fish.getPrey_id(),fish);
                    // System.out.println("Parsed. ");
                    numFish--;
        		} catch (Exception e) {
        			
        		}
        }
    }

    @Override
    public void doBusiness() throws Exception {
		try{
	        response = new ResponseNpcFishPosition();
	        response.setNpcFishMap(fishMap);
	        
	        //The playerID of the opponent of the player who sent the request
	        p_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getPlayer_id())
	                .getOpponent(client.getPlayer()).getPlayer_id();
	        
	        // System.out.println("opponent id: " + p_id);
	        
	        if(GameServer.getInstance().getActivePlayer(p_id) != null){
	        		GameServer.getInstance().getThreadByPlayerID(p_id).send(response);
	        }
		} catch(Exception ex){
			
		}
    }
    
}
