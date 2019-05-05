package sdv.net.Request;

import java.io.IOException;

import sdv.db.UsersDAO;
import sdv.net.Response.ResponseRegister;
import shared.util.DataReader;
import shared.util.Log;

public class RequestRegister extends GameRequest {

	private String username;
	private String password;
	
	private ResponseRegister responseRegister;

    public RequestRegister() {
        responses.add(responseRegister = new ResponseRegister());
    }
	
	@Override
	public void parse() throws IOException {
        String skip = DataReader.readString(dataInput).trim();
		username = DataReader.readString(dataInput).trim();
		password = DataReader.readString(dataInput).trim();
	}

	@Override
	public void doBusiness() throws Exception {
		Log.printf("\nRegistering user '%s'...", username);
        
        try {
        		// DB
            if(UsersDAO.addUserToDb(username, password))
            		Log.println("User registration successful");
            else 
            		Log.println_e("User registration not successful");
        } catch (Exception e) {
        		e.printStackTrace();
        }
	}
}
