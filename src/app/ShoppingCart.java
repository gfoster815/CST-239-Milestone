package app;

import java.util.ArrayList;

/**
 * Creates class ShoppingCart to manage the cart the user has when shopping. An
 * array of products
 */
public class ShoppingCart {
	private ArrayList<SalableProduct> cart;

	/**
	 * creates ShoppingCart array list
	 */
	public ShoppingCart() {
		cart = new ArrayList<SalableProduct>();

	}

	/**
	 * @param product is added to the cart
	 */
	public void addItemToCart(SalableProduct product) {
		cart.add(product);

	}

	/**
	 * @param name is used to find the product to take out of the cart, and removes
	 *             it from the list
	 */
	public void removeItemFromCart(String name) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getName().equals(name)) {
				cart.remove(i);
				break;
			}
		}

	}

	/**
	 * Prints out the array list of products that is currently in the user's
	 * shopping cart.
	 */
	public void showCart() {
		if (cart.size() == 0) {
			System.out.println("Your cart is empty.");
		}
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getName() + " $" + cart.get(i).getPrice());
		}
	}
/** 
 * @return Array of ShoppingCart because InventoryManager and ShoppingCart do not have a direct relationship. 
 */
	public ArrayList<SalableProduct> getCart(){
			return cart;
	}
	
	/**
	 * empties cart when items are purchased
	 */
	public void clearCart() {
		cart.clear();
	}
	
	/**
	 * Verifies that item selected is a valid product in cart
	 * @param name
	 * @return boolean
	 */
	public boolean isValidItem(String name) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
