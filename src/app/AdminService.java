package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Admin Service class, creates setup to connect to Server, and prints text to
 * the Server, as well as receiving response that it is connected, or not
 * connected to the server
 */
public class AdminService {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

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
