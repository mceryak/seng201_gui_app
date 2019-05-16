package game_objects;

import javax.swing.ImageIcon;

/**
 * Interface shared by all items
 */
public interface Item {

	/**
	 * get name of the item
	 * 
	 * @return String name of item
	 */
	public String getName();

	/**
	 * get image of the item
	 * 
	 * @return ImageIcon associated with item
	 */
	public abstract ImageIcon getIcon();

	/**
	 * get value of the item
	 * 
	 * @return int value which is a number affecting health, hunger, or cartman-coin
	 *         quantity
	 */
	public abstract int getValue();

	/**
	 * get price of the item
	 * 
	 * @return price of this item
	 */
	public abstract int getPrice();
}
