package food_items;

import javax.swing.ImageIcon;

import game_objects.FoodItem;

/**
 * This class is for creation of Thwizlers FoodItem
 */
public class Thwizlers extends FoodItem {

	/**
	 * create new Thwizlers
	 */
	public Thwizlers() {
		super("Thwizlers", 20, 40, 5);
	}

	@Override
	public int getValue() {
		return 40;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Thwizlers.class.getResource("/images_sp/thwizzlers54x54.png"));
	}

}
