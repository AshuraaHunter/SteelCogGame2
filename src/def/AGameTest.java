package def;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AGameTest extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3752828081632999334L;
	final static int CLIENT_PORT = 5656;
	final static int SERVER_PORT = 5556;
	
	private JLabel AgentLimeLabel;
	private ImageIcon AgentLimeImage;
	
	private Container content;
	
	private String cmd = "";
	
	//set up a communication socket
	Socket s = new Socket("localhost", SERVER_PORT);
	
	public AGameTest() throws UnknownHostException, IOException {
		super("Steel Cog"); // window title
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		AgentLimeLabel = new JLabel();
		AgentLimeImage = new ImageIcon(getClass().getResource("AgentLime.png"));
		AgentLimeLabel.setIcon(AgentLimeImage);
		AgentLimeLabel.setSize(16,16);
		
		content = getContentPane();
		content.setBackground(Color.black);
		setLayout(null);
		
		add(AgentLimeLabel);
		
		AgentLimeLabel.setLocation(50, 500);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		handleSocket();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws IOException {
		AGameTest myGame = new AGameTest();
		myGame.setVisible(true);
		
		final ServerSocket client = new ServerSocket(CLIENT_PORT);
		
		//set up listening server
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				synchronized(this) {
					
					System.out.println("Waiting for server responses...");
					while(true) {
						Socket s2;
						try {
							s2 = client.accept();
							AService myService = new AService(s2);
							Thread t = new Thread(myService);
							t.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("client connected");
						System.out.println("here1");
					}
				}
			}
		});
		t1.start();
		System.out.println("here2");
	}

	public void handleSocket() throws UnknownHostException, IOException {
		//Initialize data stream to send data out
		OutputStream outstream = s.getOutputStream();
		PrintWriter out = new PrintWriter(outstream);
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					if (cmd != "null" || cmd != "") {
						System.out.println("Sending: "+cmd);
						out.println(cmd);
						out.flush();
					} try {
						Thread.sleep(1000);
					} catch (Exception e) {
						
					}
				}
			}
		});
		t2.start();
		
		s.close();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			cmd = ("PLAYER 1 UP\n");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			cmd = ("PLAYER 1 DOWN\n");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			cmd = ("PLAYER 1 LEFT\n");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			cmd = ("PLAYER 1 RIGHT\n");
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			cmd = ("PLAYER 1 INTERACT\n");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		cmd = "";
	}
}