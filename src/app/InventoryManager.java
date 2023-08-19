package app;

import java.util.ArrayList;

public class InventoryManager {
	private ArrayList<SalableProduct> catalog;

	public InventoryManager() {
		catalog = new ArrayList<SalableProduct>();
	}

	public void addProductToInventory(SalableProduct product) {
		catalog.add(product);
	}

	public void showInventory() {

		for (int i = 0; i < catalog.size(); i++) {
			System.out.println(catalog.get(i).getName() + " $" + catalog.get(i).getPrice() + " Quantity: " + catalog.get(i).getQuantity());
		}
	}

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
	
	public SalableProduct getProductToShelf(String name) {
		SalableProduct product = null;
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name)) {
				catalog.get(i).incrementQuantity();
				return catalog.get(i);
			}
		}
		return product;
	}

	
}