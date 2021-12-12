package def;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//processing routine on server (B)
public class AService implements Runnable {

	private Socket s;
	private Scanner in;
	private AgentLime myAgentLime;
	private LavaWall[] myLavaWall = new LavaWall[3];
	private Finish myFinish;
	private Box myBox;
	private Goal myGoal;
	private LavaWallStopBtn myStopBtn;
	private JLabel AgentLimeLabel, FinishLabel, BoxLabel, GoalLabel;
	private JLabel[] LavaWallLabel = new JLabel[3];
	private int timeLeft;
	
	public AService(Socket aSocket) {
		this.s = aSocket;
	}

	// might have to pass in labels too
	public AService (Socket aSocket, AgentLime aLime, LavaWall aLW0, LavaWall aLW1, LavaWall aLW2,
			Finish aFinish, Box aBox, Goal myGoal, LavaWallStopBtn aStopBtn, JLabel aLimeLabel,
			JLabel aLWLabel0, JLabel aLWLabel1, JLabel aLWLabel2, JLabel aFinishLabel, JLabel aBoxLabel,
			JLabel aGoalLabel, int aTime) {
		this.s = aSocket;
		this.myAgentLime = aLime;
		this.myLavaWall[0] = aLW0;
		this.myLavaWall[1] = aLW1;
		this.myLavaWall[2] = aLW2;
		this.myFinish = aFinish;
		this.myBox = aBox;
		this.myStopBtn = aStopBtn;
		
		this.AgentLimeLabel = aLimeLabel;
		this.LavaWallLabel[0] = aLWLabel0;
		this.LavaWallLabel[1] = aLWLabel1;
		this.LavaWallLabel[2] = aLWLabel2;
		this.FinishLabel = aFinishLabel;
		this.BoxLabel = aBoxLabel;
		this.GoalLabel = aGoalLabel;
		
		this.timeLeft = aTime;
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
			if (command.equals("QUIT")) {
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
			//int playerNo = in.nextInt(); // useful for id
			int playerX = in.nextInt();
			int playerY = in.nextInt();
			int playerSpace = in.nextInt();
			int playerAlive = in.nextInt();
			int playerScore = in.nextInt();
			
			myAgentLime.setX(playerX);
			myAgentLime.setY(playerY);
			myAgentLime.setIsSpace(playerSpace);
			if (playerAlive == 0) {
				myAgentLime.setIsAlive(false);
			}
			myAgentLime.setScore(playerScore);
			AgentLimeLabel.setLocation(myAgentLime.getX(), myAgentLime.getY());
			
			System.out.println("service output of AGENTLIME: "+playerX+" "+playerY+" "+playerSpace+" "+playerAlive+" "+playerScore);
		} else if (command.equals("UPDATELAVAWALL0")) {
			int lw0Y = in.nextInt();
			int lw0Direction = in.nextInt();
			int lw0Moving = in.nextInt();
			
			myLavaWall[0].setY(lw0Y);
			myLavaWall[0].setDirection(lw0Direction);
			if (lw0Moving==1) {
				myLavaWall[0].setMoving(true);
			} else {
				myLavaWall[0].setMoving(false);
			}
			LavaWallLabel[0].setLocation(myLavaWall[0].getX(),myLavaWall[0].getY());
		} else if (command.equals("UPDATELAVAWALL1")) {
			int lw1Y = in.nextInt();
			int lw1Direction = in.nextInt();
			int lw1Moving = in.nextInt();
			
			myLavaWall[1].setY(lw1Y);
			myLavaWall[1].setDirection(lw1Direction);
			if (lw1Moving==1) {
				myLavaWall[1].setMoving(true);
			} else {
				myLavaWall[1].setMoving(false);
			}
			LavaWallLabel[1].setLocation(myLavaWall[1].getX(),myLavaWall[1].getY());
		} else if (command.equals("UPDATELAVAWALL2")) {
			int lw2Y = in.nextInt();
			int lw2Direction = in.nextInt();
			int lw2Moving = in.nextInt();
			
			myLavaWall[2].setY(lw2Y);
			myLavaWall[2].setDirection(lw2Direction);
			if (lw2Moving==1) {
				myLavaWall[2].setMoving(true);
			} else {
				myLavaWall[2].setMoving(false);
			}
			LavaWallLabel[2].setLocation(myLavaWall[2].getX(),myLavaWall[2].getY());
		} else if (command.equals("UPDATEFINISH")) {
			int finishX = in.nextInt();
			int finishY = in.nextInt();
			
			myFinish.setX(finishX);
			myFinish.setY(finishY);
			
			FinishLabel.setLocation(myFinish.getX(),myFinish.getY());
		} else if (command.equals("UPDATEBOX")) {
			int boxX = in.nextInt();
			int boxY = in.nextInt();
			
			myBox.setX(boxX);
			myBox.setY(boxY);
			
			BoxLabel.setLocation(myBox.getX(),myBox.getY());
		} else if (command.equals("UPDATEGOAL")) {
			int wasClaimed = in.nextInt();
			
			if (wasClaimed==1) {
				myGoal.setIsClaimed(true);
				GoalLabel.setIcon(new ImageIcon(getClass().getResource("goalFull.png")));
			} else {
				myGoal.setIsClaimed(false);
			}
		} else if (command.equals("UPDATESTOPBTN")) {
			
		} else if (command.equals("UPDATETIME")) {
			this.timeLeft = in.nextInt();
		}
	}
}
