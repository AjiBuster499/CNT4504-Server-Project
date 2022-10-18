
/**
 * Name: Samuel Mikell
 * Class: CNT4504 Computer Networking
 * Date: 22 September 2022 (Started)
 * Project: Iterative Socket Server (Server)
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

	// Usage: java Server <port>
	public static void main(String[] args) {
		if (args.length < 1)
			return;

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

				int receivedCommand = 0;
				do {
					receivedCommand = Integer.parseInt(reader.readLine());
					switch (receivedCommand) {
						case 0: {
							// Do Nothing
							System.out.println("Client Disconnected");
							break;
						}
						case 1: {
							// Send back the Date and Time
							writer.println(new Date().toString());
							break;
						}
						case 2: {
							// Send back the Uptime of the server
							String[] cmd = { "/bin/bash", "-c", "uptime" };
							Process proc = new ProcessBuilder(cmd).start();
							BufferedReader bufReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
							String line = "";
							while ((line = bufReader.readLine()) != null) {
								writer.println(line);
							}

							try {
								proc.waitFor();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
						case 3: {
							// Send back the memory usage
							String[] cmd = { "/bin/bash", "-c", "free" };
							Process proc = new ProcessBuilder(cmd).start();
							BufferedReader bufReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
							String line = "";
							while ((line = bufReader.readLine()) != null) {
								writer.println(line);
							}

							try {
								proc.waitFor();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
						case 4: {
							String[] cmd = { "/bin/bash", "-c", "netstat" };
							Process proc = new ProcessBuilder(cmd).start();
							BufferedReader bufReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
							String line = "";
							while ((line = bufReader.readLine()) != null) {
								writer.println(line);
							}

							try {
								proc.waitFor();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
						case 5: {
							String[] cmd = { "/bin/bash", "-c", "who" };
							Process proc = new ProcessBuilder(cmd).start();
							BufferedReader bufReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
							String line = "";
							while ((line = bufReader.readLine()) != null) {
								writer.println(line);
							}

							try {
								proc.waitFor();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
						case 6: {
							String[] cmd = { "/bin/bash", "-c", "ps", "-aux" };
							Process proc = new ProcessBuilder(cmd).start();
							BufferedReader bufReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
							String line = "";
							while ((line = bufReader.readLine()) != null) {
								writer.println(line);
							}

							try {
								proc.waitFor();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							break;
						}
						default: {
							writer.println("ERROR: This is not a valid command!");
							break;
						}
					}

				} while (receivedCommand != 0);

				// Be nice and close the socket.
				socket.close();
			}

		} catch (IOException e) {
			System.out.println("Server Exception: " + e.getMessage());
		}
	}

}
