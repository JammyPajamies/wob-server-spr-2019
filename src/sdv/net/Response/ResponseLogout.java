package sdv.net.Response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseLogout extends GameResponse {
	
	private short status;

    public ResponseLogout() {
        responseCode = NetworkCode.SD_GAME_LOGOUT;
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
