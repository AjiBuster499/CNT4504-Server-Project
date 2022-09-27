/**
 * Name: Samuel Mikell
 * Class: CNT4504 Computer Networking
 * Date: 22 September 2022 (Started)
 * Project: Iterative Socket Server (Server)
 */
import java.net.*;
import java.io.*;

public class Server {

	// Usage: java Server <port>
	public static void main(String[] args) {
		if (args.length < 1) return;

		// Verify the port
		int port = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port: " + port);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New Client Connected");

				// Setup read
				// Read with reader.readLine()
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));

				// Setup write
				// Write with writer.println(text)
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);

				// TODO: Receive Actions
				do {
					
				} while (false);
				
				// Be nice and close the socket.
				socket.close();
			}

		} catch (IOException e) {
			System.out.println("Server Exception: " + e.getMessage());
		}
	}
}
