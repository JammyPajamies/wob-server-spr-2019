package sdv.net.Request;

import java.io.IOException;

import sdv.PlayTime.PlayTimePlayer;
import sdv.db.DAO;
import sdv.db.PlayDAO;
import sdv.db.PlayInfoDAO;
import sdv.net.Response.ResponseSetPlayerFishSelection;
import shared.metadata.Constants;
import shared.util.DataReader;

public class RequestSetPlayerFishSelection extends GameRequest {

	private int player_id;
	private int species_id;

	private ResponseSetPlayerFishSelection responsePlayerFishSelection;

    public RequestSetPlayerFishSelection() {
        responses.add(responsePlayerFishSelection = new ResponseSetPlayerFishSelection());
    }
	
	@Override
	public void parse() throws IOException {
		player_id = DataReader.readInt(dataInput);
		species_id = DataReader.readInt(dataInput);
	}

	@Override
	public void doBusiness() throws Exception {
		
		try {
			// DB
	        // check connection
	        if (DAO.getDataSource().getConnection() != null) {
	            System.out.println("Successfully connected to database.\n");
	        }
	        PlayTimePlayer playTimePlayer = PlayInfoDAO.getPlayerInfo(player_id);
	        playTimePlayer.setRunnerSpeciesID(species_id);
	        
	        PlayDAO.updatePlay(playTimePlayer);
	        
			responsePlayerFishSelection.setStatus((short) Constants.SUCCESS_STATUS_CODE); // db update successful
		} catch (Exception e) {
			e.printStackTrace();
			responsePlayerFishSelection.setStatus((short) Constants.FAILURE_STATUS_CODE); // db update not successful
		}
	}
}
