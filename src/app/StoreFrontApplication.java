package app;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Creates class of StoreFrontApplication for the main method to drive the store
 * front.
 * 
 * @author gfost
 * @version 3.0
 */
public class StoreFrontApplication {

	public static void main(String[] args) {
		/**
		 * Creates store front and sets up the command for prompted inputs
		 */
		StoreFrontApplication store = new StoreFrontApplication();
		String command;
		Scanner input = new Scanner(System.in);
		/**
		 * Initializes inventory
		 */
		InventoryManager inventory = new InventoryManager();
		/**
		 * Initializes cart
		 */
		ShoppingCart cart = new ShoppingCart();

		store.open(inventory);
		/**
		 * Begins process of shopping
		 */
		command = input.next();

		while (command.toLowerCase() != "leave") {

			if (command.toLowerCase().equals("shop")) {
				store.Purchase(inventory, cart, command, input);
				command = input.next();
			} else if (command.toLowerCase().equals("cancelpurchase")) {
				store.cancelPurchase(inventory, cart, command, input);
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
		store.close();
		input.close();
	}

	private void open(InventoryManager inventory) {
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

		System.out.println("Welcome to Garrett's Fantasy Costco! We will help you with any of your fighting needs!");
		System.out.println("What would you like to do? Say: Shop or Leave.");
	}

	private void Purchase(InventoryManager inventory, ShoppingCart cart, String command, Scanner input) {
		System.out.println("Great! Check out our wares! Type an item you would like to purchase. If none, say Stop.");
		inventory.showInventory();
		command = input.next();
		boolean isValid = inventory.isValidItem(command);
		if (command.toLowerCase().equals("stop")) {
			System.out.println("Okay! Would you like to shop, cancelPurchase, or leave?");
		}
		/**
		 * takes product that is mentioned to add to cart and take out of inventory
		 */

		else if (isValid) {
			SalableProduct tempProduct = inventory.getProductOffShelf(command);
			cart.addItemToCart(tempProduct);

			System.out.println("Check out your purchases in your shopping cart!");
			cart.showCart();

			System.out.println("Want to buy more? If so, say Shop! If you are done, say CancelPurchase, or Leave.");
		} else {
			System.out.println(
					"Whoops! We don't have that item. Let's start again. Do you want to Shop, CancelPurchase, or Leave?");
		}
	}

	private void cancelPurchase(InventoryManager inventory, ShoppingCart cart, String command, Scanner input) {
		System.out.println(
				"What item do you want to return out of your shopping cart? If you want to return them all, say ReturnAll.");
		cart.showCart();
		command = input.next();
		boolean isValid = cart.isValidItem(command);

		if (command.toLowerCase().equals("returnall")) {
			ArrayList<SalableProduct> itemsPurchased = new ArrayList<SalableProduct>();
			itemsPurchased = cart.getCart();
			inventory.returnAllProducts(itemsPurchased);
			cart.clearCart();
		} else if (isValid) {
			cart.removeItemFromCart(command);
			inventory.cancelPurchase(command);
			System.out.println("Thanks. Here is your cart of purchases now.");
		} else {
			System.out.println("Whoops! That's not an item you can remove from cart. Below is your cart.");
		}

		cart.showCart();

		System.out.println(
				"If you would like to return an item, say cancelPurchase again. If you'd like to purchase something instead, say Shop! If you are done, say Leave.");

	}

	private void close() {
		System.out.println("Thanks for coming!");
	}

}
