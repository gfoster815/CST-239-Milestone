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
public class HealthFileService implements FileService {

	public static void saveToFile(String filename, Health product, boolean append) {

		PrintWriter pw;

		try {
			File file = new File(filename);
			FileWriter fw = new FileWriter(file, append);
			pw = new PrintWriter(fw);

			// Write Health as JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(product);
			pw.println(json);

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was an issue accessing this file. Please ensure your file is where it should be.");
		}
	}

	public static ArrayList<Health> readFromFile(String filename) {
		ArrayList<Health> products = new ArrayList<Health>();

		try {
			File file = new File(filename);
			Scanner s = new Scanner(file);

			while (s.hasNext()) {
				String json = s.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				Health product = objectMapper.readValue(json, Health.class);

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
