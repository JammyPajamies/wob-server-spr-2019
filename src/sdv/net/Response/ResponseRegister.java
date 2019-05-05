package sdv.net.Response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseRegister extends GameResponse {
	
	private short status;

    public ResponseRegister() {
        responseCode = NetworkCode.SD_REGISTER;
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