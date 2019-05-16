package food_items;

import javax.swing.ImageIcon;

import game_objects.FoodItem;
import main.GUIGame.DayWindow;

/**
 * This class is for creation of MemberBerries FoodItem
 */
public class MemberBerries extends FoodItem {

	/**
	 * create new MemberBerries
	 */
	public MemberBerries() {
		super("Member Berries", 15, 30, 5);
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(DayWindow.class.getResource("/images_sp/memberberries_54x52.png"));
	}

	@Override
	public int getValue() {
		return 30;
	}

}
