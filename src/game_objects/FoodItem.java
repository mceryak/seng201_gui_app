package game_objects;

import javax.swing.ImageIcon;

import food_items.CremeFraiche;
import food_items.Fingerlings;
import food_items.MemberBerries;
import food_items.SaltyChocolateBalls;
import food_items.TenormanChili;
import food_items.Thwizlers;

/**
 * Abstract class for creation of food items
 */
public abstract class FoodItem implements Item {

	// public immutable array of all food items
	public static final FoodItem[] allFoods = new FoodItem[] { new Thwizlers(), new Fingerlings(), new CremeFraiche(),
			new MemberBerries(), new SaltyChocolateBalls(), new TenormanChili() };

	// name of this food item
	private String name;

	// price of this food item
	private int price;

	// how much this food item reduces hunger
	private int nutrition;

	// how rare this food item is (lower -> more rare)
	private int rarity;

	/**
	 * Constructor for FoodItem
	 * 
	 * @param name      String name of this food item
	 * @param price     int price of this food item
	 * @param nutrition int how much hunger gets reduced
	 * @param rarity    int rarity
	 */
	public FoodItem(String name, int price, int nutrition, int rarity) {
		this.name = name;
		this.price = price;
		this.nutrition = nutrition;
		this.rarity = rarity;
	}

	/**
	 * get image of this food
	 * 
	 * @return ImageIcon associated with this food item
	 */
	public abstract ImageIcon getIcon();

	/**
	 * get name of this food
	 *
	 * @return String name of this food item
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get price of this food item
	 * 
	 * @return int price
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * get amount of reduction to hunger
	 * 
	 * @return int amount hunger is reduced
	 */
	public int getNutrition() {
		return this.nutrition;
	}

	/**
	 * get how rare this food item is
	 * 
	 * @return int rarity
	 */
	public int getRarity() {
		return this.rarity;
	}

	/**
	 * hash manually so same food items can increase in quantity in hashmap
	 * 
	 * @return int hash value
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	/**
	 * 2 food items are equal if they have the same name
	 * 
	 * @return boolean determining if 2 food items are equal
	 */
	@Override
	public boolean equals(Object other) {
		return other instanceof FoodItem && this.name == ((FoodItem) other).name;
	}
}
