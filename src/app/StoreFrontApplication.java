package app;

import java.util.Scanner;

public class StoreFrontApplication {

	public static void main(String[] args) {

		StoreFront store = new StoreFront();
		String command;
		Scanner input = new Scanner(System.in);

		store.openStore();
		System.out.println("Welcome to our store! We will help you with any of your fighting needs!");

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
					break; //TODO: figure out how to break out of here...
				}

				SalableProduct tempProduct = inventory.getProductOffShelf(command);
				cart.addItemToCart(tempProduct);
				System.out.println("Check out your cart!");
				cart.showCart();
				System.out.println(
						"Want to buy more? If so, say Shop! If you are done, say Purchase, RemoveItemFromCart, CancelPurchase, or Leave.");

				command = input.next();
			} else if (command.toLowerCase().equals("purchase")) {
				cart.itemsPurchased();
				cart.clearCart();

				System.out.println(
						"Thanks for your purchase! Want to buy more? If so, say Shop! If you are done, say Purchase, Leave or CancelPurchase.");
				command = input.next();

			} else if (command.toLowerCase().equals("removeitemfromcart")) {
				System.out.println("What do you want to remove from the cart?");
				command = input.next();
				cart.removeItemFromCart(command);

				inventory.getProductToShelf(command);
				

				System.out.println("Check out your cart!");
				cart.showCart();
				System.out.println(
						"Want to buy more? If so, say Shop! If you are done shopping, say Purchase, RemoveItemFromCart, CancelPurchase, or Leave.");
				
				command = input.next();

			} else if (command.toLowerCase().equals("cancelpurchase")) {
				System.out.println("What item do you want to return?");

				inventory.getProductToShelf(command);
				
				

				System.out.println(
						"Want to buy more? If so, say Shop! If you are done, say Purchase, RemoveItemFromCart, CancelPurchase, or Leave.");
				command = input.next();
			}

		}
	}

}
