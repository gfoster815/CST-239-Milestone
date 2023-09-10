package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ArmorFileService {

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
		}
	}

	public static ArrayList<SalableProduct> readFromFile(String filename) {
		ArrayList<SalableProduct> products = new ArrayList<SalableProduct>();

		try {
			File file = new File(filename);
			Scanner s = new Scanner(file);

			while (s.hasNext()) {
				String json = s.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				SalableProduct product = objectMapper.readValue(json, SalableProduct.class);

				if (product.getProductType().equals("Armor")) {
					Armor tempProduct = new Armor(product.getName(), product.getDescription(), product.getPrice(),
							product.getQuantity(), product.getProductType());
					products.add(tempProduct);
				}

				else if (product.getProductType().equals("Health")) {
					Health tempProduct = new Health(product.getName(), product.getDescription(), product.getPrice(),
							product.getQuantity(), product.getProductType());
					products.add(tempProduct);

				} else if (product.getProductType().equals("Weapon")) {
					Weapon tempProduct = new Weapon(product.getName(), product.getDescription(), product.getPrice(),
							product.getQuantity(), product.getProductType());
					products.add(tempProduct);

				}

			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return products;
	}

	public static void main(String[] args) {
		/*
		 * ArrayList<SalableProduct> products = new ArrayList<SalableProduct>();
		 * products.add(new Weapon("Sword", "Does 3 melee damage", 59.99, 3));
		 * products.add(new Weapon("Mace", "Does 5 melee damage", 69.99, 3));
		 * products.add(new Armor("Shield", "Protects 4 melee damage", 59.99, 3));
		 * products.add(new Armor("Helmet", "Protects 1 melee damage", 19.99, 3));
		 * products.add(new Health("Potion", "Drink, then heals 5 damage", 39.99, 3));
		 * products.add(new Health("Sandwich", "Eat, then heals 2 damage", 14.99, 3));
		 * 
		 * for (int x = 0; x < products.size(); ++x) { saveToFile("Inventory.json",
		 * products.get(x), true); }
		 * 
		 * ArrayList<SalableProduct> productList = readFromFile("Inventory.json"); for
		 * (SalableProduct product : productList) { String text = product.getName() +
		 * "," + product.getDescription() + "," + Double.toString(product.getPrice()) +
		 * "," + Integer.toString(product.getQuantity());
		 * 
		 * System.out.println(text); }
		 */
	}

}
