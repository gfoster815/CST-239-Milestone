package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Will handle json records for the weaponInventory.json file
 */
public class ArmorFileService implements FileService {

	public static void saveToFile(String filename, SalableProduct product, boolean append) {

		PrintWriter pw;

		try {
			File file = new File(filename);
			FileWriter fw = new FileWriter(file, append);
			pw = new PrintWriter(fw);

			// Write Car as JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(product);
			pw.println(json);

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was an issue accessing this file. Please ensure your file is where it should be.");
		}
	}

	public static ArrayList<Armor> readFromFile(String filename) {
		ArrayList<Armor> products = new ArrayList<Armor>();

		try {
			File file = new File(filename);
			Scanner s = new Scanner(file);

			while (s.hasNext()) {
				String json = s.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				Armor product = objectMapper.readValue(json, Armor.class);

				products.add(product);

			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was an issue accessing this file. Please ensure your file is where it should be.");
		}

		return products;
	}

}
