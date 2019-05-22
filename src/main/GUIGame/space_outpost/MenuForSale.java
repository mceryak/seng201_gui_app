package main.GUIGame.space_outpost;

import java.util.ArrayList;

import game_objects.FoodItem;
import game_objects.MedicalItem;

/*
 * This class is for creating and displaying the menu at the space outpost.
 */
public class MenuForSale {

	// array containing all food types. Obtained from FoodItem.allFoods
	private final FoodItem[] allFoods;

	// array containing all healing types. Obtained from MedicalItem.allMeds
	private final MedicalItem[] allMeds;

	// array containing quantity of each food type, indexes corresponding to the
	// indexes of allFoods
	private int[] foodQuantities;

	// array containing quantity of each healing type, indexes corresponding to the
	// indexes of allMeds
	private int[] medQuantities;

	// true if barter in the crew, means 20% off all items
	private boolean hasBarter;

	/**
	 * This constructor is called 1 time per day. Quantities of items vary each day.
	 * 
	 * @param hasBarter Determines whether there is a barter type in the crew
	 */
	public MenuForSale(boolean hasBarter) {

		this.hasBarter = hasBarter;

		this.allFoods = FoodItem.allFoods;
		this.allMeds = MedicalItem.allMeds;

		this.foodQuantities = new int[6];
		this.medQuantities = new int[3];

		// array used to determine probabilities of quantities of an item
		int[] probabilities;

		// food item quantity is based on rarity of food item
		for (int i = 0; i < 6; i++)
			this.foodQuantities[i] = (int) (Math.random() * this.allFoods[i].getRarity());

		// Chance of no cure: 3/10
		// Chance of 1 cure: 2/5
		// Chance of 2 cures: 1/5
		// Chance of 3 cures: 1/10
		probabilities = new int[] { 0, 0, 0, 1, 1, 1, 1, 2, 2, 3 };
		this.medQuantities[0] = probabilities[(int) (Math.random() * 10)];

		// Chance of 3 small medPacks: 1/10
		// Chance of 4 small medPacks: 1/5
		// Chance of 5 small medPacks: 3/10
		// Chance of 6 small medPacks: 1/5
		// Chance of 7 small medPacks: 1/10
		// Chance of 8 small medPacks: 1/10
		probabilities = new int[] { 3, 4, 4, 5, 5, 5, 6, 6, 7, 8 };
		this.medQuantities[1] = probabilities[(int) (Math.random() * 10)];

		// same probabilities for large medPacks, only subtract 2 from each possibility
		this.medQuantities[2] = probabilities[(int) (Math.random() * 10)] - 2;
	}

	/**
	 * get each item on the menu
	 * 
	 * @return list of menu items
	 */
	public ArrayList<MenuItem> getMenu() {

		ArrayList<MenuItem> ret = new ArrayList<>();

		int x = 330;
		int y = 300;
		for (int i = 0; i < 3; i++) { // medical items
			ret.add(new MenuItem(allMeds[i], medQuantities[i], x, y, hasBarter ? .8 : 1));
			y += 55;
		}

		x += 270;
		y = 280;

		for (int i = 0; i < 6; i++) { // food items
			ret.add(new MenuItem(allFoods[i], foodQuantities[i], x, y, hasBarter ? .8 : 1));
			y += 55;
		}

		return ret;

	}
}