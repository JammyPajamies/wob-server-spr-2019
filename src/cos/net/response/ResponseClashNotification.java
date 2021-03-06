package cos.net.response;

import cos.metadata.NetworkCode;
import cos.model.MatchRecord;
import cos.util.GamePacket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhi on 7/10/16.
 */
public class ResponseClashNotification extends GameResponse {
	List<MatchRecord> matchRecords = new ArrayList<MatchRecord>();
	public ResponseClashNotification(){
		response_id = NetworkCode.CLASH_NOTIFICATION;
	}

	public void addGame(MatchRecord matchRecord){
		matchRecords.add(matchRecord);
	}
	@Override
	public byte[] getBytes() {
		GamePacket packet = new GamePacket(response_id);
		packet.addInt32(matchRecords.size());
		for(MatchRecord matchRecord : matchRecords) {
			packet.addString(matchRecord.opponentName);
			packet.addString(matchRecord.matchResult);
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String playDate = df.format(matchRecord.playedOn);
			packet.addString(playDate);
			// Also add level?
		}
		return packet.getBytes();
	}
}
