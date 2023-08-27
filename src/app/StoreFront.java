package app;

/**
 * creates Storefront class: TBD if needed. Created for 'initializing state of
 * store'
 */
public class StoreFront {
	private boolean isOpen;

	/**
	 * creates object for StoreFront
	 */
	public StoreFront() {
		isOpen = false;
	}

	/**
	 * @return status of store
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Opens the store
	 */
	public void openStore() {
		isOpen = true;
	}

	/**
	 * Closes the store
	 */
	public void closeStore() {
		isOpen = false;
	}

}
