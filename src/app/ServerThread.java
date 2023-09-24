package app;

import java.io.IOException;
import java.lang.Thread;
/**
 * Class that allows Threads of a server to be created
 */
public class ServerThread extends Thread {
	
	public void run() {
		AdminService server = new AdminService();
		try {
			server.start(6666);				
			server.cleanup();
		}
		catch (IOException  e){
			e.printStackTrace();
		}
	}

}
