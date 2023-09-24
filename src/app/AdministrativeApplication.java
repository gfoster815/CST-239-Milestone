package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Application to run and kick off commands
 */
public class AdministrativeApplication {
	
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	public static void main(String[] args) throws IOException, InterruptedException {

		AdministrativeApplication admin = new AdministrativeApplication();
		admin.start("127.0.0.1", 6666);

		Scanner scnr = new Scanner(System.in);

		
		String message = null;
		String response = null;
		// Runs loop to allow input until finished.
		do {
			System.out.println("Input command. Your options are:");
			System.out.println("View inventory:                      R");
			System.out.println("Update inventory of current product: U|ProductName|Quantity");
			System.out.println("Add new product to inventory:        U|ProductType|ProudctName|Description|Price|Quantity");
			System.out.println("To exit program:                     QUIT");
			message = scnr.nextLine();
			response = admin.sendMessage(message);
			// Returns products in a line instead of one long string
			if (message.equalsIgnoreCase("R")) {
				String[] responseTokens = response.split("\\, "); 
				for (int i = 0; i < responseTokens.length; ++i) {
					System.out.print(responseTokens[i] + "\n");
				}
			}
			else {
			System.out.println(response + "\n");
			}
		} while (!message.equalsIgnoreCase("QUIT"));


		scnr.close();

	}
	
	/**
	 * Makes connection to server
	 * 
	 * @param ip
	 * @param port
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void start(String ip, int port) throws UnknownHostException, IOException {

		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	/**
	 * Method to send message and receive response from server
	 * 
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	public String sendMessage(String msg) throws IOException {
		out.println(msg);
		return in.readLine();
	}

	/**
	 * Cleans up connection on admin side
	 * @throws IOException
	 */
	public void cleanup() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}


}











