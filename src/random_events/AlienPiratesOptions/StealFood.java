package random_events.AlienPiratesOptions;

import java.util.HashMap;

import game_objects.FoodItem;
import game_objects.inventory.Inventory;

/**
 * This class is one of the options for alien pirates to raid crew's inventory.
 * They attemp to steal food.
 */
public class StealFood {

	/**
	 * if food exists in crew's inventory, one food item will be removed
	 * 
	 * @param inventory Inventory to check for food
	 * @return String stating whether an item was stolen or nothing happened
	 */
	public String steal(Inventory inventory) {

		HashMap<FoodItem, Integer> foodMap = inventory.getFoodItems();

		if (foodMap.size() > 0) { // crew has food to be stolen by the pirates
			Object[] foods = foodMap.keySet().toArray();
			FoodItem stolenFood = (FoodItem) foods[(int) (Math.random() * foodMap.size())];
			inventory.removeItem(stolenFood);
			try {
				inventory.getGui().removeOne(stolenFood);
			} catch (NullPointerException e) {
				System.out.println("null b/c test");
			}
			return "-1 " + stolenFood.getName();
		} else { // crew has no food, try other option
			return "";
		}
	}
}