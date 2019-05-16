package food_items;

import javax.swing.ImageIcon;

import game_objects.FoodItem;

/**
 * This class is for creation of Fingerlings FoodItem
 */
public class Fingerlings extends FoodItem {

	/**
	 * create new Fingerlings
	 */
	public Fingerlings() {
		super("Fingerlings", 10, 20, 10);
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Fingerlings.class.getResource("/images_sp/fingerlings_54x54.png"));
	}

}
