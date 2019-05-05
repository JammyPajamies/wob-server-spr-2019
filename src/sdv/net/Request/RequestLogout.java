package sdv.net.Request;

import java.io.IOException;

import sdv.core.GameClient;
import sdv.core.GameServer;
import sdv.db.DAO;
import sdv.db.PlayDAO;
import sdv.model.Player;
import sdv.net.Response.ResponseLogout;
import shared.metadata.Constants;
import shared.util.DataReader;
import shared.util.Log;

public class RequestLogout extends GameRequest {

	private int player_id;
	private int play_id;
	
	private ResponseLogout responseLogout;

    public RequestLogout() {
        responses.add(responseLogout = new ResponseLogout());
    }
	
	@Override
	public void parse() throws IOException {
		player_id = DataReader.readInt(dataInput);
		play_id = DataReader.readInt(dataInput);
	}

	@Override
	public void doBusiness() throws Exception {
		Log.printf("User '%s' is requesting logout...", player_id);
		
		// Local
        GameClient thread = GameServer.getInstance().getThreadByPlayerID(player_id);
        Player player = thread.getPlayer();
        if(player != null) {
    			Log.printf("Logging out player: " + player.toString());
        		thread.removePlayerData();
        		responseLogout.setStatus((short) Constants.SUCCESS_STATUS_CODE); // Logout successful
        } else {
			Log.printf("Error logging out player.");
        		responseLogout.setStatus((short) Constants.FAILURE_STATUS_CODE); // Logout not successful
        }
        
        try {
            PlayDAO.leavePlay(player_id, play_id);
        } catch (Exception e) {
        		e.printStackTrace();
        }
	}
}
