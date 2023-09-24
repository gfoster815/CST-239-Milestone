package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server class creating the Server and creating sockets/ports so that it can
 * connect to the Admin.
 */
public class Server {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
/**
 * Runs the server code that reads Admin input and handles it as necessary.
 * @param port 6666
 * @throws IOException
 */
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();

		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
/**
 * Creates inventory manager inside Server to handle Admin input and connect admin to inventory manager
 */
		InventoryManager inventoryAdminManager = new InventoryManager();

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.indexOf("U") == 0) {
				// Sets up existing Inventory to be edited
				inventoryAdminManager.addProductsToInventory();
				// Splits input from Admin to manipulate products
				String[] tokens = inputLine.split("\\|");
				// Handles adding more inventory of an existing product
				if (tokens.length == 3) {
					String productName = tokens[1];
					boolean isValidInput = inventoryAdminManager.isValidItem(productName);
					if (isValidInput) {
						int quantityAdded;
						try {
							quantityAdded = Integer.parseInt(tokens[2]);
						} catch (NumberFormatException e) {
							out.println("The quantity was not a valid input. Please try again.");
							continue;
						}
						int newQuantity = inventoryAdminManager.adjustInventoryCount(productName, quantityAdded);
						out.println(String.format("New quantity of the " + productName + " product is " + newQuantity));
					} else {
						out.println("Invalid product given. The product name is Case sensitive. Please try again.");
						continue;
					}
				}
				// Handles adding a new product to the system
				else if (tokens.length == 6) {

					String productName = tokens[2];
					String productDescription = tokens[3];
					Double productPrice = null;
					int productQuantity = 0;
					try {
						productPrice = Double.parseDouble(tokens[4]);
					} catch (NumberFormatException e) {
						out.println("The Price was not a valid input. Please try again.");
						continue;
					}
					try {
						productQuantity = Integer.parseInt(tokens[5]);
					} catch (NumberFormatException e) {
						out.println("The quantity was not a valid input. Please try again.");
						continue;
					}
					boolean productAlreadyExists = inventoryAdminManager.isValidItem(productName);

					if (!productAlreadyExists) {
						if (tokens[1].equalsIgnoreCase("Weapon")) {
							Weapon product = new Weapon(productName, productDescription, productPrice, productQuantity);
							inventoryAdminManager.addWeaponInventory(product);
						} else if (tokens[1].equalsIgnoreCase("Health")) {
							Health product = new Health(productName, productDescription, productPrice, productQuantity);
							inventoryAdminManager.addHealthInventory(product);
						} else if (tokens[1].equalsIgnoreCase("Armor")) {
							Armor product = new Armor(productName, productDescription, productPrice, productQuantity);
							inventoryAdminManager.addArmorInventory(product);
						}
						out.println(
								String.format("The " + productName + " was added to the " + tokens[1] + " inventory."));

					} else if (productAlreadyExists) {
						out.println("This product already exists. Please try again.");
						continue;
					}

				} else {
					out.println("Invalid Input. Try again.");
					continue;
				}
				// Returns inventory results
			} else if ("R".equals(inputLine)) {
				ArrayList<String> inventory = new ArrayList<String>();
				inventory = inventoryAdminManager.showInventoryToAdmin();

				out.println(inventory);
				continue;
			} else if ("QUIT".equals(inputLine)) {
				out.println("Finished updating inventory. Have a nice day.");
				break;
			} else {
				out.println("Invalid Input. Try again.");
			}
		}
			

	}
/**
 * Cleans up server connection when done
 * @throws IOException
 */
	public void cleanup() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}
/**
 * Kicks off server
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {

		Server server = new Server();
		server.start(6666);
		server.cleanup();

	}

}
