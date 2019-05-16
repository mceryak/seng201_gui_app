package game_objects;

import medical_items.MedPack_Large;
import medical_items.MedPack_Small;
import medical_items.SpacePlagueCure;

/**
 * Abstract class for creation of medical items
 */
public abstract class MedicalItem implements Item {

	// public immutable array of all medical items
	public static final MedicalItem[] allMeds = new MedicalItem[] { new SpacePlagueCure(), new MedPack_Small(),
			new MedPack_Large() };

	// name of medical item
	private String name;

	// price of medical item
	private int price;

	// amount of restored health
	private int healthRestore;

	/**
	 * Constructor for MedicalItem
	 * 
	 * @param name          String name of item
	 * @param price         int price of item
	 * @param healthRestore int amount of health restored
	 */
	public MedicalItem(String name, int price, int healthRestore) {
		this.name = name;
		this.price = price;
		this.healthRestore = healthRestore;
	}

	/**
	 * get whether this item can cure space plague
	 * 
	 * @return boolean storing whether the medical item can cure space plague
	 */
	public abstract boolean curesSpacePlague();

	/**
	 * get name of this item
	 *
	 * @return String name of item
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * get price of this item
	 * 
	 * @return price of this item
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * get amount of health restored by this item
	 * 
	 * @return int health restore value
	 */
	public int getHealthRestore() {
		return this.healthRestore;
	}

	/**
	 * 2 medical items should hash to the same value if their names are equal
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	/**
	 * 2 medical items are equal if their names are equal
	 */
	@Override
	public boolean equals(Object other) {
		return other instanceof MedicalItem && this.name == ((MedicalItem) other).name;
	}

}
