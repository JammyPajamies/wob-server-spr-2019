
package sdv.net.Request;

import sdv.PlayTime.Play;
import sdv.PlayTime.PlayManager;
import sdv.PlayTime.PreySpawning;
import sdv.core.GameServer;
import java.io.IOException;
import shared.metadata.Constants;
import sdv.net.Response.ResponseDestroyPrey;
import sdv.net.Response.ResponseReSpawnPrey;
import shared.util.DataReader;
import shared.util.Log;

/**
 * request from client to destroy a prey in the map on the server.
 * a response will be sent to the opponent to let them know this change.
 * @author Karl
 */
public class RequestDestroyPrey extends GameRequest{
    
    private int prey_id; //the prey being destroyed.
    private int species_id; // species being destroyed
    private int player_id; //the opponent player to send an update to.
    private ResponseDestroyPrey response = new ResponseDestroyPrey();
    private ResponseReSpawnPrey response2 = new ResponseReSpawnPrey();
    
    public RequestDestroyPrey(){}
    
    public void parse() throws IOException{
        
        species_id = DataReader.readInt(dataInput);
        prey_id = DataReader.readInt(dataInput);
    }
    
     public void doBusiness() throws Exception{
        
        response.setPreyId(prey_id);
        Play play = PlayManager.getInstance().getPlayByPlayerID(client.getPlayer().getPlayer_id());
        // decrease number on destroy
        if(species_id==0){
            play.species0--;
        }
        else if(species_id==1){
            play.species1--;
        }
        else if(species_id==2){
            play.species2--;
        }
        else if(species_id==3){
            play.species3--;
        }
         else if(species_id==4){
            play.species4--;
        }
         else if(species_id==5){
            play.species5--;
        }
         else if(species_id==6){ 
            play.species6--;
        }
         else if(species_id==7){
            play.species7--;
        }
       
        //The playerID of the opponent of the player who sent the request
        player_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
        //sending the eaten preyID to the opponent, for them to update their view.
        GameServer.getInstance().getThreadByPlayerID(player_id).send(response);
        
        // check total number of fishes alive and send request to host client to spawn fishes
        if((play.species1+ play.species2 + play.species3 +play.species4+ play.species5 + play.species6+ play.species7 +play.species0)< Constants.MIN_PREY){
            if(play.species0<3){
                response2.setSpecies_id(0);
                response2.setNum_of_prey(3);
                play.species0 += 3;
                
            }
            else if(play.species1<3){
                 response2.setSpecies_id(1);
                response2.setNum_of_prey(3);
                play.species1 += 3;
              
            }
            else if(play.species2<2){
                 response2.setSpecies_id(2);
                response2.setNum_of_prey(3);
                play.species2 += 3;
                
            }
              else if(play.species3<2){
                 response2.setSpecies_id(3);
                response2.setNum_of_prey(3);
                play.species3 += 3;
                
            }
              else if(play.species4<3){
                 response2.setSpecies_id(4);
                response2.setNum_of_prey(3);
                play.species4 += 3;
                
            }
              else if(play.species5<3){
                 response2.setSpecies_id(5);
                response2.setNum_of_prey(3);
                play.species5 += 3;
                
            }
              else if(play.species6<2){
                 response2.setSpecies_id(6);
                response2.setNum_of_prey(3);
                play.species6 += 3;
                
            }
              else if(play.species7<3){
                 response2.setSpecies_id(7);
                response2.setNum_of_prey(3);
                play.species7 += 3;
                
            }
          GameServer.getInstance().getThreadByPlayerID(play.HOST_client_id).send(response2);  
        }
    }
    
}
