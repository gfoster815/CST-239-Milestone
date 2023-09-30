package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import app.SalableProduct;
import app.Weapon;
import app.InventoryManager;

public class InventoryManagerTests {

	final String testArmorFile = "ArmorInventoryUnitTestData.json";
	final String testHealthFile = "HealthInventoryUnitTestData.json";
	final String testWeaponFile = "WeaponInventoryUnitTestData.json";


	@Test
	public void adjustInventoryCountTest() {
		
		InventoryManager inventoryTest = new InventoryManager();
		
		int newQuantity = inventoryTest.adjustInventoryCount("Mace", 3, testArmorFile, testHealthFile, testWeaponFile);
		Assert.assertEquals(newQuantity, 6);
		
		int nextNewQuantity = inventoryTest.adjustInventoryCount("Mace", -3, testArmorFile, testHealthFile, testWeaponFile);
		Assert.assertEquals(nextNewQuantity, 3);
		
	}
	@Test
	public void getProductOffShelfTest() {

		InventoryManager inventoryTest = new InventoryManager();

		inventoryTest.addProductsToInventory(testArmorFile, testHealthFile, testWeaponFile);

		SalableProduct product = inventoryTest.getProductOffShelf("Sword");
		Assert.assertEquals(2, product.getQuantity());
		
		SalableProduct product2 = inventoryTest.getProductOffShelf("Shield");
		Assert.assertEquals(3, product2.getQuantity());
		
		SalableProduct product3 = inventoryTest.getProductOffShelf("Potion");
		Assert.assertEquals(4, product3.getQuantity());
		
	}
	@Test
	public void returnAllProductsTest() {
		
		InventoryManager inventoryTest = new InventoryManager();
		ArrayList<SalableProduct> cart = new ArrayList<SalableProduct>();
		
		Weapon axe1 = new Weapon("Axe", "Does 5 melee damage", 69.99, 3);
		Weapon axe2 = new Weapon("Axe", "Does 5 melee damage", 69.99, 3);
		
		cart.add(axe1);
		cart.add(axe2);
		
		inventoryTest.addProductsToInventory(testArmorFile, testHealthFile, testWeaponFile);
		inventoryTest.returnAllProducts(cart);
		
		SalableProduct product = inventoryTest.getProductOffShelf("Axe");
		// Unable to get quantity without the getProductsOffShelf
		Assert.assertEquals(4, product.getQuantity());
		
	}
	
	@Test
	public void cancelPurchaseTest() {
		InventoryManager inventoryTest = new InventoryManager();
		ArrayList<SalableProduct> cart = new ArrayList<SalableProduct>();
		
		Weapon axe1 = new Weapon("Axe", "Does 5 melee damage", 69.99, 3);
		
		cart.add(axe1);
		
		inventoryTest.addProductsToInventory(testArmorFile, testHealthFile, testWeaponFile);
		SalableProduct product = inventoryTest.cancelPurchase("Axe");
		
		Assert.assertEquals(4, product.getQuantity());
	}
	
	@Test
	public void isValidItemTest() {
		
		InventoryManager inventoryTest = new InventoryManager();
		inventoryTest.addProductsToInventory(testArmorFile, testHealthFile, testWeaponFile);
		
		Assert.assertEquals(true, inventoryTest.isValidItem("Sword"));
		Assert.assertEquals(false, inventoryTest.isValidItem("Swrd"));

	}
	
	@Test
	public void adjustInventoryTest() {
		
		InventoryManager inventoryTest = new InventoryManager();
		inventoryTest.addProductsToInventory(testArmorFile, testHealthFile, testWeaponFile);
		
		boolean success = inventoryTest.adjustInventory(testArmorFile, testHealthFile, testWeaponFile);
		
		Assert.assertEquals(true, success);
		
	}
	
	@Test
	public void addProductsToInventoryTest() {
		
		InventoryManager inventoryTest = new InventoryManager();
		boolean success = inventoryTest.addProductsToInventory(testArmorFile, testHealthFile, testWeaponFile);
		
		Assert.assertEquals(true, success);

		
	}
	
	

}
