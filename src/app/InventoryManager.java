package app;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Creates class InventoryManager to manage inventory
 */
public class InventoryManager {
	private ArrayList<Weapon> weaponCatalog;
	private ArrayList<Health> healthCatalog;
	private ArrayList<Armor> armorCatalog;
	private ArrayList<SalableProduct> catalog;
	private ProductFileService<Armor> armorFile;
	private ProductFileService<Health> healthFile;
	private ProductFileService<Weapon> weaponFile;
	/**
	 * Creates object InventoryManager to have an array of products
	 */
	public InventoryManager() {
		catalog = new ArrayList<SalableProduct>();
		weaponCatalog = new ArrayList<Weapon>();
		armorCatalog = new ArrayList<Armor>();
		healthCatalog = new ArrayList<Health>();

	}

	/**
	 * @param product is added to the array of products
	 */
	public void addProductsToInventory() {
		armorFile = new ProductFileService(Armor.class);
		healthFile = new ProductFileService(Health.class);
		weaponFile = new ProductFileService(Weapon.class);

		
		weaponCatalog = weaponFile.readFromFile("weaponInventory.json");
		healthCatalog = healthFile.readFromFile("healthInventory.json");
		armorCatalog = armorFile.readFromFile("armorInventory.json");
		catalog.addAll(armorCatalog);
		catalog.addAll(healthCatalog);
		catalog.addAll(weaponCatalog);

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
 * This will adjust inventory files for products when a product is purchased or
 * returned
 */
	public void adjustInventory() {
		for (int i = 0; i < armorCatalog.size(); i++) {
			if (i == 0) {
				armorFile.saveToFile("ArmorInventory.json", armorCatalog.get(i), false);
			} else {
				armorFile.saveToFile("ArmorInventory.json", armorCatalog.get(i), true);
			}
		}
		for (int i = 0; i < healthCatalog.size(); i++) {
			if (i == 0) {
				healthFile.saveToFile("HealthInventory.json", healthCatalog.get(i), false);
			} else
				healthFile.saveToFile("HealthInventory.json", healthCatalog.get(i), true);
		}

		for (int i = 0; i < weaponCatalog.size(); i++) {
			if (i == 0) {
				weaponFile.saveToFile("WeaponInventory.json", weaponCatalog.get(i), false);
			} else {
				weaponFile.saveToFile("WeaponInventory.json", weaponCatalog.get(i), true);
			}

		}
	}

	/**
	 * Increases quantity of items once they are all returned, with the
	 * CancelPurchase
	 * 
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
	 * 
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