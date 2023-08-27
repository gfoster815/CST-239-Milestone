package app;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Creates class of StoreFrontApplication for the main method to drive the store
 * front.
 * 
 * @author gfost
 * @version 2.0
 */
public class StoreFrontApplication {

	public static void main(String[] args) {
		/**
		 * Creates store front and sets up the command for prompted inputs
		 */
		StoreFront store = new StoreFront();
		String command;
		Scanner input = new Scanner(System.in);
		store.openStore();
		System.out.println("Welcome to Garrett's Fantasy Costco! We will help you with any of your fighting needs!");
		/**
		 * Initializes inventory
		 */
		InventoryManager inventory = new InventoryManager();

		Weapon sword = new Weapon("Sword", "Does 3 melee damage", 49.99, "slashing");
		inventory.addProductToInventory(sword);
		Weapon mace = new Weapon("Mace", "Does 5 melee damage", 69.99, "bludgeoning");
		inventory.addProductToInventory(mace);
		Armor shield = new Armor("Shield", "Protects 4 melee damage", 59.99, "body");
		inventory.addProductToInventory(shield);
		Armor helmet = new Armor("Helmet", "Prtects 1 melee damage", 19.99, "head");
		inventory.addProductToInventory(helmet);
		Health potion = new Health("Potion", "Drink, then heals 5 damage", 39.99, 5);
		inventory.addProductToInventory(potion);
		Health sandwich = new Health("Sandwich", "Eat, then heals 2 damage", 14.99, 2);
		inventory.addProductToInventory(sandwich);

		/**
		 * Begins process of shopping
		 */
		System.out.println("What would you like to do? Say: Shop, CancelPurchase, or Leave.");

		command = input.next();
		ShoppingCart cart = new ShoppingCart();

		while (command.toLowerCase() != "leave") {

			if (command.toLowerCase().equals("shop")) {
				System.out
						.println("Great! Check out our wares! Type an item you would like to buy. If none, say Leave.");

				inventory.showInventory(); // TODO: show sorted

				command = input.next();
				if (command.toLowerCase().equals("leave")) {
					break;
				}
				/**
				 * takes product that is mentioned to add to cart and take out of inventory
				 */

				boolean isValid = inventory.isValidItem(command);
				if (isValid) {
					SalableProduct tempProduct = inventory.getProductOffShelf(command);
					cart.addItemToCart(tempProduct);
					System.out.println("Check out your cart!");
					cart.showCart();

					System.out.println(
							"Want to buy more? If so, say Shop! If you are done, say Purchase, RemoveItemFromCart, CancelPurchase, or Leave.");
				} else {
					System.out.println(
							"Whoops! We don't have that item. Let's start again. Do you want to Shop, Purchase, ReemoveItemFromCart, CancelPurchase, or Leave?");
				}

				command = input.next();
			} else if (command.toLowerCase().equals("purchase")) {
				// Allows the list of cart items to be temporarily turned into an array of
				// salable products, then moved over to Inventory since there is no direct
				// relationship between Cart and Inventory
				ArrayList<SalableProduct> itemsPurchased = new ArrayList<SalableProduct>();
				itemsPurchased = cart.getCart();
				inventory.purchaseProducts(itemsPurchased);
				cart.clearCart();

				System.out.println(
						"Thanks for your purchase! Want to buy more? If so, say Shop! If you are done, say Purchase, Leave or CancelPurchase.");
				command = input.next();
				/**
				 * Puts item back from cart to the shelf
				 */
			} else if (command.toLowerCase().equals("removeitemfromcart")) {
				System.out.println("What do you want to remove from the cart?");
				command = input.next();
				cart.removeItemFromCart(command);

				inventory.cancelPurchase(command);

				System.out.println("Check out your cart!");
				cart.showCart();
				System.out.println(
						"Want to buy more? If so, say Shop! If you are done shopping, say Purchase, RemoveItemFromCart, CancelPurchase, or Leave.");

				command = input.next();
				/**
				 * Returns an item to the store
				 */
			} else if (command.toLowerCase().equals("cancelpurchase")) {
				System.out.println("What item do you want to return?");
				command = input.next();
				inventory.cancelPurchase(command);

				System.out.println(
						"Want to buy more? If so, say Shop! If you are done, say Purchase, RemoveItemFromCart, CancelPurchase, or Leave.");
				command = input.next();
			} else {
				if (command.toLowerCase().equals("leave")) {
					break;
				} else {
					System.out.println("Whoops! That wasn't an option. What would you like to do?");
					command = input.next();
				}
			}

		}

		System.out.println("Thanks for coming!");
		store.closeStore();
		input.close();
	}

}
