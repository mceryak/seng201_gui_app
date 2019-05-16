package game_objects;

import javax.swing.ImageIcon;

/**
 * This class creates the CartmanCoin object
 */
public class CartmanCoin implements Item {

	// quantity
	private int amount;

	public CartmanCoin() {
		amount = 100; // initial quantity = 100
	}

	@Override
	public String getName() {
		return "Cartman-coin";
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(CartmanCoin.class.getResource("/images_sp/cartmangold_54x54.png"));
	}

	@Override
	public int getValue() {
		return amount;
	}

	/**
	 * change quantity of cartman-coins by specified amount
	 * 
	 * @param mod specified int amount to change quantity by
	 */
	public void modifyAmount(int mod) {
		amount += mod;
	}

	/**
	 * CartmanCoins don't have a price
	 */
	@Override
	public int getPrice() {
		return -1;
	}

}
