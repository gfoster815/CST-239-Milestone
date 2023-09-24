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
	private ProductFileService<Armor> armorFileRaw;
	private ProductFileService<Health> healthFileRaw;
	private ProductFileService<Weapon> weaponFileRaw;
	private SortByNameComparator nameComparator = new SortByNameComparator();
	private SortByPriceComparator priceComparator = new SortByPriceComparator();
	private ArrayList<String> rawCatalog;

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
 * Displays raw inventory to the Admin user
 * @return Arraylist string
 */
	public ArrayList<String> showInventoryToAdmin() {
		armorFileRaw = new ProductFileService<Armor>(Armor.class);
		healthFileRaw = new ProductFileService<Health>(Health.class);
		weaponFileRaw = new ProductFileService<Weapon>(Weapon.class);

		ArrayList<String> rawArmor = armorFileRaw.readRawFile("armorInventory.json");
		ArrayList<String> rawHealth = healthFileRaw.readRawFile("healthInventory.json");
		ArrayList<String> rawWeapon = weaponFileRaw.readRawFile("weaponInventory.json");

		rawCatalog = new ArrayList<String>();
		rawCatalog.addAll(rawWeapon);
		rawCatalog.addAll(rawHealth);
		rawCatalog.addAll(rawArmor);

		return rawCatalog;

	}

	/**
	 * @param product is added to the array of products
	 */
	public void addProductsToInventory() {
		armorFile = new ProductFileService<Armor>(Armor.class);
		healthFile = new ProductFileService<Health>(Health.class);
		weaponFile = new ProductFileService<Weapon>(Weapon.class);

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
		Collections.sort(catalog, nameComparator);
		iterateList(catalog);
	}

	/**
	 * displays full inventory of the catalog with different sorting options
	 * 
	 * @param command is the command of which way the sorting will occur
	 */
	public void showInventory(String command) {
		if (command.equals("sortbynameasc")) {
			Collections.sort(catalog, nameComparator);
			iterateList(catalog);

		} else if (command.equals("sortbynamedesc")) {
			Collections.sort(catalog, nameComparator.reversed());
			iterateList(catalog);

		} else if (command.equals("sortbypriceasc")) {
			Collections.sort(catalog, priceComparator);
			iterateList(catalog);
		} else if (command.equals("sortbypricedesc")) {
			Collections.sort(catalog, priceComparator.reversed());
			iterateList(catalog);

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
	 * Adds weapon product to the inventory
	 * @param product
	 */
	public void addWeaponInventory(Weapon product) {
		weaponFile.saveToFile("WeaponInventory.json", product, true);

	}
	/**
	 * Adds armor product to the inventory
	 * @param product
	 */
	public void addArmorInventory(Armor product) {
		armorFile.saveToFile("ArmorInventory.json", product, true);

	}
	/**
	 * Adds health product to the inventory
	 * @param product
	 */
	public void addHealthInventory(Health product) {
		healthFile.saveToFile("HealthInventory.json", product, true);

	}
/**
 * For admin side only - adjusts inventory for product by input
 * @param name
 * @param count
 * @return
 */
	public int adjustInventoryCount(String name, int count) {
		SalableProduct tempProduct = null;
		addProductsToInventory();
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name)) {
				tempProduct = catalog.get(i);
				catalog.get(i).incrementQuantity(count);
			}
		}
		adjustInventory();
		return tempProduct.getQuantity();
		
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

	private void iterateList(ArrayList<SalableProduct> catalog) {
		for (int i = 0; i < catalog.size(); i++) {
			System.out.println(catalog.get(i).getName() + " $" + catalog.get(i).getPrice() + " Quantity: "
					+ catalog.get(i).getQuantity());
		}
	}
	/**
	 * Resets inventory for User on front end so that while admin makes changes while a User is shopping, it updates with up to date products
	 */
	public void resetInventory() {
		catalog.clear();
	}

}