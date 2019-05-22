package game_objects.inventory;

import java.util.HashMap;

import game_objects.CartmanCoin;
import game_objects.FoodItem;
import game_objects.Item;
import game_objects.MedicalItem;

/**
 * This class creates the inventory for the player
 */
public class Inventory {

	// hashmap containing food items and their quantities
	private HashMap<FoodItem, Integer> foodMap;

	// hashmap containing medical items and their quantities
	private HashMap<MedicalItem, Integer> medMap;

	// CartmanCoin object
	private CartmanCoin cartmanCoin;

	// GUI representation of inventory
	private InventoryGui gui;

	/**
	 * Constructor for Inventory
	 */
	public Inventory() {
		gui = new InventoryGui();
		foodMap = new HashMap<>();
		medMap = new HashMap<>();
		cartmanCoin = new CartmanCoin();
	}

	/**
	 * @return GUI representation of inventory
	 */
	public InventoryGui getGui() {
		return gui;
	}

	/**
	 * @return HashMap of food items with quantities
	 */
	public HashMap<FoodItem, Integer> getFoodItems() {
		return foodMap;
	}

	/**
	 * @return HashMap of medical items with quantities
	 */
	public HashMap<MedicalItem, Integer> getMedicalItems() {
		return medMap;
	}

	/**
	 * change amount of cartman-coins in inventory
	 * 
	 * @param mod positive or negative integer to add to current quantity of
	 *            cartman-coins
	 */
	public void modifyCCAmount(int mod) {

		// change actual value
		cartmanCoin.modifyAmount(mod);
	}

	/**
	 * @return quantity of cartman-coins in inventory
	 */
	public int getCCAmount() {
		return cartmanCoin.getValue();
	}

	/**
	 * Add an item to the inventory and show item and quantity in gui
	 * 
	 * @param item Item to be added
	 */
	public void addItem(Item item) {

		if (item instanceof MedicalItem) {
			if (medMap.containsKey(item)) { // item already exists, so increment quantity
				medMap.put((MedicalItem) item, medMap.get(item) + 1);
			} else { // item does not exist in inventory
				medMap.put((MedicalItem) item, 1);
			}
		} else {
			if (foodMap.containsKey(item)) { // item already exists, so increment quantity
				foodMap.put((FoodItem) item, foodMap.get(item) + 1);
			} else { // item does not exist in inventory
				foodMap.put((FoodItem) item, 1);
			}
		}

	}

	/**
	 * remove an item from inventory
	 * 
	 * @param item Item to be removed
	 */
	public void removeItem(Item item) {

		if (item instanceof FoodItem) {
			FoodItem food = (FoodItem) item;
			if (foodMap.get(item) == 1) { // remove entirely
				foodMap.remove(food);
			} else { // decrement quantity
				foodMap.put(food, foodMap.get(food) - 1);
			}

		} else {
			MedicalItem med = (MedicalItem) item;
			if (medMap.get(item) == 1) { // remove entirely
				medMap.remove(med);
			} else { // decrement quantity
				medMap.put(med, medMap.get(med) - 1);
			}

		}
	}

	/**
	 * Add an item to the inventory and show item and quantity in gui. Reduce
	 * cartman-coin quantity by price
	 * 
	 * @param item  Item to be added
	 * @param price Amount of cartman-coins used to purchase item
	 */
	public void addItem(Item item, int price) {

		if (item instanceof MedicalItem) {
			if (medMap.containsKey(item)) { // item already exists, so increment quantity
				medMap.put((MedicalItem) item, medMap.get(item) + 1);
			} else { // item doesn't exist yet
				medMap.put((MedicalItem) item, 1);
			}
		} else {
			if (foodMap.containsKey(item)) { // item already exists, so increment quantity
				foodMap.put((FoodItem) item, foodMap.get(item) + 1);
			} else { // item doesn't exist yet
				foodMap.put((FoodItem) item, 1);
			}
		}
		modifyCCAmount(-price);
	}

}