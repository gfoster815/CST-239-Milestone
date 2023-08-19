package app;

import java.util.ArrayList;

public class StoreFront {
	private boolean isOpen;

	public StoreFront() {
		isOpen = false;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void openStore() {
		isOpen = true;
	}

	public void closeStore() {
		isOpen = false;
	}




}
