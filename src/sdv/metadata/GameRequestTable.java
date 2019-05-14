/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.metadata;

import java.util.HashMap;
import java.util.Map;
import sdv.net.Request.GameRequest;
import shared.util.Log;
import shared.metadata.NetworkCode;

/**
 *
 * @author anu
 */
public class GameRequestTable {
     private static Map<Short, Class> requestTable = new HashMap<Short, Class>(); // Request Code -> Class

    /**
     * Initialize the hash map by populating it with request codes and classes.
     */
    public static void init() {
        // Populate the table using request codes and class names
        /*add(Constants.CMSG_AUTH, "RequestLogin");
        add(Constants.CMSG_RACE_INIT, "RequestPlayInit");
        add(Constants.CMSG_SDENDGAME, "RequestSDEndGame");
        add(Constants.CMSG_SDSTARTGAME, "RequestSDStartGame");  
        add(Constants.CMSG_KEYBOARD, "RequestSDKeyboard");
        add(Constants.CMSG_POSITION, "RequestSDPosition");
        add(Constants.CMSG_REQ_PREY,"RequestPrey");
        add(Constants.CMSG_EAT_PREY,"RequestDestroyPrey");
        add(Constants.CMSG_SCORE,"RequestScore");
        add(Constants.CMSG_HEARTBEAT,"RequestHeartbeat"); 
        add(Constants.CMSG_NPCPOSITION,"RequestNpcFishPosition");*/
        
        add(NetworkCode.SD_GAME_LOGIN, "RequestLogin");
        add(NetworkCode.SD_PLAY_INIT, "RequestPlayInit");
        add(NetworkCode.SD_END_GAME, "RequestSDEndGame");
        add(NetworkCode.SD_START_GAME, "RequestSDStartGame");  
        add(NetworkCode.SD_KEYBOARD, "RequestSDKeyboard");
        add(NetworkCode.SD_PLAYER_POSITION, "RequestSDPosition");
        add(NetworkCode.SD_PREY,"RequestPrey");
        add(NetworkCode.SD_EAT_PREY,"RequestDestroyPrey");
        add(NetworkCode.SD_SCORE,"RequestScore");
        add(NetworkCode.SD_HEARTBEAT,"RequestHeartbeat");
        add(NetworkCode.SD_NPCPOSITION, "RequestNpcFishPosition" );
    }

    /**
     * Map the request code number with its corresponding request class, derived
     * from its class name using reflection, by inserting the pair into the
     * table.
     *
     * @param code a value that uniquely identifies the request type
     * @param name a string value that holds the name of the request class
     */
    public static void add(short code, String name) {
        try {
            requestTable.put(code, Class.forName("sdv.net.Request." + name));
        } catch (ClassNotFoundException e) {
            System.out.println("lalalalal");
            Log.println_e(e.getMessage());
        }
    }

    /**
     * Get the instance of the request class by the given request code.
     *
     * @param request_code a value that uniquely identifies the request type
     * @return the instance of the request class
     */
    
    public static GameRequest get(short request_code) {
        GameRequest request = null;

        try {
            Class name = requestTable.get(request_code);

            if (name != null) {
            		// Log.printf("\nRequest received [%d]", request_code);
                request = (GameRequest) name.newInstance();
                request.setID(request_code);
            } else {
                Log.printf_e("\nUnrecognized request code [%d]", request_code);
            }
        } catch (Exception e) {
            Log.println_e(e.getMessage());
        }

        return request;
    }

}
