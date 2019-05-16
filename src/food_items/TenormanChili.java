package food_items;

import javax.swing.ImageIcon;

import game_objects.FoodItem;

/**
 * This class is for creation of TenormanChili FoodItem
 */
public class TenormanChili extends FoodItem {

	/**
	 * create new TenormanChili
	 */
	public TenormanChili() {
		super("Tenorman Chili", 40, 80, 5);
	}

	@Override
	public int getValue() {
		return 80;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(TenormanChili.class.getResource("/images_sp/tenormanchli_54x54.png"));
	}

}
