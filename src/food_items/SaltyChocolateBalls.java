package food_items;

import javax.swing.ImageIcon;

import game_objects.FoodItem;

/**
 * This class is for creation of SaltyChocolateBalls FoodItem
 */
public class SaltyChocolateBalls extends FoodItem {

	/**
	 * create new salty chocolate balls
	 */
	public SaltyChocolateBalls() {
		super("Salty Choclate Balls", 5, 20, 2);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(SaltyChocolateBalls.class.getResource("/images_sp/chocballs_54x54.png"));
	}

}
