package app;

import java.io.IOException;
import java.util.Scanner;
/**
 * Application to run and kick off commands
 */
public class AdministrativeApplication {
	public static void main(String[] args) throws IOException, InterruptedException {

		AdminService admin = new AdminService();
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

}
