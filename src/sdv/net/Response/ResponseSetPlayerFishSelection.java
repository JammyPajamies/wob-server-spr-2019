package sdv.net.Response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseSetPlayerFishSelection extends GameResponse {
	
	private short status;

    public ResponseSetPlayerFishSelection() {
        responseCode = NetworkCode.SD_PLAYER_FISH_SELECTION;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);
        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }
}

