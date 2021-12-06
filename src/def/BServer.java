import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//server app
public class BServer {

	public static void main(String[] args) throws IOException {

		final int SERVER_PORT = 5556;
		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Waiting for clients to connect...");
		while(true) {
			Socket s = server.accept();
			System.out.println("client connected");
			
			BService myService = new BService (s);
			Thread t = new Thread(myService);
			t.start();
		}
		

	}

}
