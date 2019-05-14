package sdv.net.Response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseGetPlayersInfo extends GameResponse {
	
	private short status;
	private int player_id;
	private int prey_id;
	private int opponent_player_id;
	private int opponent_prey_id;

    public ResponseGetPlayersInfo() {
        responseCode = NetworkCode.SD_GET_PLAYERS_INFO;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);
        packet.addInt32(player_id);
        packet.addInt32(prey_id);
        packet.addInt32(opponent_player_id);
        packet.addInt32(opponent_prey_id);
        return packet.getBytes();
    }
    
    public void setPlayerId(int player_id) {
    		this.player_id = player_id;
    }
    
    public void setPreyId(int prey_id) {
    		this.prey_id = prey_id;
    }
    
    public void setOpponentPlayerId(int opponent_player_id) {
		this.opponent_player_id = opponent_player_id;
	}
	
	public void setOpponentPreyId(int opponent_prey_id) {
			this.opponent_prey_id = opponent_prey_id;
	}

    public void setStatus(short status) {
        this.status = status;
    }
}
