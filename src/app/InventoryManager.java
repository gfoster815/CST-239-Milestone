package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Creates class InventoryManager to manage inventory
 */
public class InventoryManager {
	private ArrayList<SalableProduct> catalog;

	/**
	 * Creates object InventoryManager to have an array of products
	 */
	public InventoryManager() {
		catalog = new ArrayList<SalableProduct>();
	}

	/**
	 * @param product is added to the array of products
	 */
	public void addProductToInventory(SalableProduct product) {
		catalog.add(product);
	}

	/**
	 * displays full inventory of the catalog
	 */
	public void showInventory() {

		Collections.sort(catalog);

		
		for (int i = 0; i < catalog.size(); i++) {
			System.out.println(catalog.get(i).getName() + " $" + catalog.get(i).getPrice() + " Quantity: "
					+ catalog.get(i).getQuantity());
		}
	}

	/**
	 * @param name is grabbed and identifies product to purchase
	 * @return the product that is grabbed from the shelf, and decreases inventory
	 *         quantity by 1
	 */
	public SalableProduct getProductOffShelf(String name) {
		SalableProduct product = null;
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name)) {
				catalog.get(i).decrementQuantity();
				return catalog.get(i);
			}
		}
		return product;
	}
	/**
	 * Increases quantity of items once they are all returned, with the CancelPurchase
	 * @param itemsInCart is an array of SalableProducts
	 */
	public void returnAllProducts(ArrayList<SalableProduct> itemsInCart) {
		for (int i = 0; i < itemsInCart.size(); i++) {
			itemsInCart.get(i).incrementQuantity();

		}
	}

	/**
	 * @param name is grabbed and identifies product that is returned to the shelf
	 * @return the product that is put onto the shelf, and increases inventory
	 *         quantity by 1. Individual cancel purchase
	 */
	public SalableProduct cancelPurchase(String name) {
		SalableProduct product = null;
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name)) {
				catalog.get(i).incrementQuantity();
				return catalog.get(i);
			}
		}
		return product;
	}
	/**
	 * Verifies that item selected is a valid product in inventory
	 * @param name
	 * @return boolean
	 */
	public boolean isValidItem(String name) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	

}