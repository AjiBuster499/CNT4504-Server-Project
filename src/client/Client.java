
/**
 * Name: Samuel Mikell
 * Class: CNT4504 Computer Networking
 * Date: 22 September 2022 (Started)
 * Project: Iterative Socket Server (Client)
 */

import java.net.*;
import java.time.Duration;
import java.time.Instant;
import java.io.*;
import java.util.*;

public class Client {

	// Usage: java Client <hostname> <port>
	public static void main(String[] args) {
		if (args.length < 2)
			return;

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

			// List of inputCommand valid options:
			// 0: Quit
			// 1: Date and Time
			// 2: Uptime
			// 3: Memory Use
			// 4: Netstat
			// 5: Current Users
			// 6: Running Processes
			int inputCommand = 0;
			do {
				System.out.println("What action would you like to perform today?");
				System.out.println("The following actions are available:");
				System.out.println("1: Date and Time");
				System.out.println("2: Uptime");
				System.out.println("3: Memory Usage");
				System.out.println("4: Netstats");
				System.out.println("5: Current Users");
				System.out.println("6: Running Processes");
				System.out.println("Or enter 0 to quit.");
				inputCommand = scanner.nextInt();

				if (inputCommand != 0) {
					System.out.println("How many clients would you like to spin up?");
					System.out.println("Options: 1, 5, 10, 15, 20, 25");
					int threadCount = scanner.nextInt();

					// time taken to complete once.
					long elapsedTime = 0;
					// complete list of times.
					ArrayList<Long> times = new ArrayList<Long>();
					for (int i = 0; i < threadCount; i++) {
						final int in = inputCommand;
						Instant start = Instant.now();
						Thread th = new Thread(new Runnable() {
							// Running out of names.
							// This is a wonkly alias to inputCommand in order to get around scoping and
							// names.
							int p = in;

							@Override
							public void run() {
								try {
									writer.println(p);
									// Wait for response.
									String res;
									res = reader.readLine();
									System.out.println(res);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
						th.start();
						Instant finish = Instant.now();
						// Collecting our times.
						elapsedTime = Duration.between(start, finish).toNanos();
						times.add(elapsedTime);
					}
					// Mathing the times.
					long avgTime = 0;
					System.out.println("Per Client Time:");
					for (long time : times) {
						avgTime += time;
						System.out.println(time + " ms");
					}
					System.out.println("Total Time: " + avgTime + " ms"); // The name is a little misleading, because we haven't
																	// averaged just yet.
					avgTime /= threadCount;
					System.out.println("Average Time: " + avgTime + " ms");

				}

			} while (inputCommand != 0);

			// Be nice and close your streams
			scanner.close();

		} catch (UnknownHostException e) {
			System.out.println("Server not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}

	}
}
