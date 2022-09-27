/**
 * Name: Samuel Mikell
 * Class: CNT4504 Computer Networking
 * Date: 22 September 2022 (Started)
 * Project: Iterative Socket Server (Client)
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	
	// Usage: java Client <hostname> <port>
	public static void main(String[] args) {
		if (args.length < 2) return;

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		try (Socket socket = new Socket(hostname, port)) {

			// Setup read
			// read with reader.readLine()
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			// Setup write
			// Write with writer.println(text)
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);

			// Do stuff
			Scanner scanner = new Scanner(System.in);
			System.out.println("What action would you like to perform today?");
			// TODO: Actions

			// Be nice and close your streams
			scanner.close();


		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}


	}
}
