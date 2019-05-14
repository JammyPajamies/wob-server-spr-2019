package sdv.net.Request;

import java.io.IOException;

import sdv.db.DAO;
import sdv.db.PlayInfoDAO;
import sdv.net.Response.ResponseGetPlayerFishSelection;
import shared.metadata.Constants;
import shared.util.DataReader;

public class RequestGetPlayerFishSelection extends GameRequest {

	private int player_id;

	private ResponseGetPlayerFishSelection responseGetPlayerFishSelection;

    public RequestGetPlayerFishSelection() {
        responses.add(responseGetPlayerFishSelection = new ResponseGetPlayerFishSelection());
    }
	
	@Override
	public void parse() throws IOException {
		player_id = DataReader.readInt(dataInput);
	}

	@Override
	public void doBusiness() throws Exception {
		
		try {
			// DB
	        // check connection
	        if (DAO.getDataSource().getConnection() != null) {
	            System.out.println("Successfully connected to database.\n");
	        }
	        
	        int species_id = PlayInfoDAO.getSpeciesId(player_id);
	        responseGetPlayerFishSelection.setSpeciesId(species_id);
	        responseGetPlayerFishSelection.setStatus((short) Constants.SUCCESS_STATUS_CODE); // db fetch successful
		} catch (Exception e) {
			e.printStackTrace();
			responseGetPlayerFishSelection.setStatus((short) Constants.FAILURE_STATUS_CODE); // db fetch not successful
		}
	}
}
