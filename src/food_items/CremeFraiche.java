package food_items;

import javax.swing.ImageIcon;

import game_objects.FoodItem;

/**
 * This class is for creation of CremeFraiches FoodItem
 */
public class CremeFraiche extends FoodItem {

	/**
	 * create new CremeFraiche
	 */
	public CremeFraiche() {
		super("Creme Fraiche", 2, 3, 10);
	}

	@Override
	public int getValue() {
		return 3;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(CremeFraiche.class.getResource("/images_sp/cremefraiche_54x54.png"));
	}

}
