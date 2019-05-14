package sdv.core;

import java.io.IOException;
import java.util.Map;

import sdv.net.Response.ResponseHeartbeat;

public class HeartbeatAgent implements Runnable {
	
	private int DEFAULT_SAMPLING_PERIOD = 1; //seconds
	public static boolean kill = false;
	Map<String, GameClient> activeThreads;

	public HeartbeatAgent() {
		
	}

	private void collectData() {
		GameServer gameServer = GameServer.getInstance();
		activeThreads = gameServer.getActiveThreads();
	}

	public void sendData() {
		for (Map.Entry<String, GameClient> entry : activeThreads.entrySet()) {
			GameClient client = entry.getValue();
			try {
				System.out.println("\nHeartbeat.... " + client.getID() + "\n");
				client.send(new ResponseHeartbeat());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		try {
			while(!kill) {
				this.collectData();
				this.sendData();
				Thread.sleep(DEFAULT_SAMPLING_PERIOD * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
