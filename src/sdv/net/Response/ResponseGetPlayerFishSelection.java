package sdv.net.Response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseGetPlayerFishSelection extends GameResponse {
	
	private short status;
	private int species_id;

    public ResponseGetPlayerFishSelection() {
        responseCode = NetworkCode.SD_PLAYER_FISH_SELECTION;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);
        packet.addInt32(species_id);
        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
    
    public void setSpeciesId(int species_id) {
        this.species_id = species_id;
    }
}
