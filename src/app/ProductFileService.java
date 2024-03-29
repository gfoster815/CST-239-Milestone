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
public class ProductFileService<T> implements FileService {
	
	private ArrayList<T> products;
	/**
	 * Creates Class type variable so that Jackson objectmapper can read which class is being used
	 */
	private Class<T> type;
	/**
	 * Constructor for object of ProductFileService
	 * @param type is the Class (In this case, Weapon, Armor, or Health
	 */
	public ProductFileService(Class<T> type) {
		this.type = type;
	}


	public void saveToFile(String filename, T product, boolean append) {

		PrintWriter pw;

		try {
			File file = new File(filename);
			FileWriter fw = new FileWriter(file, append);
			pw = new PrintWriter(fw);

			// Write Product as JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(product);
			pw.println(json);

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was an issue accessing this file. Please ensure your file is where it should be.");
		}
	}

	public  ArrayList<T> readFromFile(String filename) {
		products = new ArrayList<T>();

		try {
			File file = new File(filename);
			Scanner s = new Scanner(file);

			while (s.hasNext()) {
				String json = s.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				T product = objectMapper.readValue(json, type);

				products.add(product);

			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was an issue accessing this file. Please ensure your file is where it should be.");
		}

		return products;
	}
	/**
	 * Method to return json files raw for Admin
	 * @param filename
	 * @return
	 */
	public ArrayList<String> readRawFile(String filename) {
		ArrayList<String> products = new ArrayList<String>();
		try {
			File file = new File(filename);
			Scanner s = new Scanner(file);

			while (s.hasNext()) {
				String json = s.nextLine();
				products.add(json);
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There was an issue accessing this file. Please ensure your file is where it should be.");
		}

		return products;
	}

}
