package def;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//processing routine on server (B)
public class AService implements Runnable {

	private Socket s;
	private Scanner in;
	private AgentLime myAgentLime;
	private LavaWall[] myLavaWall = new LavaWall[3];
	private Finish myFinish;
	private Box myBox;
	private LavaWallStopBtn myStopBtn;
	
	public AService(Socket aSocket) {
		this.s = aSocket;
	}

	// might have to pass in labels too
	public AService (Socket aSocket, AgentLime aLime, LavaWall aLW0, LavaWall aLW1, LavaWall aLW2,
			Finish aFinish, Box aBox, LavaWallStopBtn aStopBtn) {
		this.s = aSocket;
		this.myAgentLime = aLime;
		this.myLavaWall[0] = aLW0;
		this.myLavaWall[1] = aLW1;
		this.myLavaWall[2] = aLW2;
		this.myFinish = aFinish;
		this.myBox = aBox;
		this.myStopBtn = aStopBtn;
	}
	public void run() {
		
		try {
			in = new Scanner(s.getInputStream());
			processRequest( );
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	//processing the requests
	public void processRequest () throws IOException {
		//if next request is empty then return
		while(true) {
			if(!in.hasNext( )){
				return;
			}
			String command = in.next();
			if (command.equals("Quit")) {
				return;
			} else {
				executeCommand(command);
			}
		}
	}
	
	public void executeCommand(String command) throws IOException{
		if (command.equals("UPDATELIME")) {
			//strip out coordinates
			//update the label
			int playerNo = in.nextInt();
			String playerAction = in.next();
			int playerX = in.nextInt();
			int playerY = in.nextInt();
			System.out.println("Player "+playerNo+" "+playerAction + " "+playerX+", "+playerY);
		}
	}
}
