package main.GUIGame.space_outpost;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game_objects.Item;
import game_objects.MedicalItem;
import game_objects.inventory.Inventory;

/**
 * This class creates a menu item for the space outpost window
 */
public class MenuItem {

	// item up for sale
	private Item item;

	// label stating quantity of item
	private JLabel lblQuantity;

	// label containing image of item
	private JLabel image;

	// name of item
	private JLabel name;

	// describes how item helps health, hunger, etc.
	private JLabel description;

	// how many coins it costs to buy item
	private int price;

	// clickable label to add to inventory for [price] amount of coins
	private JLabel buy;

	// quantity of item
	private int quantity;

	/**
	 * Constructor for MenuItem
	 * 
	 * @param item     Item being sold
	 * @param q        quantity
	 * @param x        location on x-axis
	 * @param y        location on y-axos
	 * @param discount percent discount (!= 1 iff barter is in crew)
	 */
	public MenuItem(Item item, int q, int x, int y, double discount) {
		this.item = item;
		quantity = q;
		image = new JLabel("");

		lblQuantity = new JLabel("" + q);
		lblQuantity.setBounds(x + 48, y + 45, 10, 10);
		lblQuantity.setForeground(new Color(201, 165, 0));
		lblQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		image.setIcon(item.getIcon());
		image.setBounds(x, y, 54, 54);

		name = new JLabel(item.getName());
		name.setForeground(new Color(255, 200, 200));
		name.setBounds(x + 60, y + 14, 150, 15);

		description = new JLabel(
				item instanceof MedicalItem ? "+" + item.getValue() + " health" : "-" + item.getValue() + " hunger");
		description.setBounds(x + 60, y + 31, 100, 15);
		description.setForeground(new Color(255, 190, 190));

		price = (int) (item.getPrice() * discount);

		buy = new JLabel("BUY " + price + "CC");
		buy.setBounds(x + 190, y + 20, 70, 15);
		buy.setForeground(new Color(255, 153, 0));
	}

	/**
	 * called when space outpost menu is closed, hide all items.
	 */
	public void hide() {
		lblQuantity.setVisible(false);
		image.setVisible(false);
		name.setVisible(false);
		description.setVisible(false);
		buy.setVisible(false);
	}

	/**
	 * called when space outpost menu is opened, show all items
	 */
	public void show() {
		lblQuantity.setVisible(true);
		image.setVisible(true);
		name.setVisible(true);
		description.setVisible(true);
		buy.setVisible(true);
	}

	/**
	 * render on the current frame
	 * 
	 * @param frame     Currently visible frame
	 * @param inventory Player's inventory
	 */
	public void render(JFrame frame, Inventory inventory) {
		buy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // change color so player know it is clickable
				buy.setForeground(new Color(55, 5, 5));
			}

			@Override
			public void mouseExited(MouseEvent e) { // change color back
				buy.setForeground(new Color(255, 153, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) { // buy the item if affordable
				if (inventory.getCCAmount() >= price && quantity > 0) {
					inventory.addItem(item, price);
					inventory.getGui().renderItem(item, 1);
					inventory.getGui().modifyCC(-price);
					lblQuantity.setText("" + --quantity);
				}
			}
		});
		frame.getContentPane().add(lblQuantity);
		frame.getContentPane().add(image);
		frame.getContentPane().add(name);
		frame.getContentPane().add(description);
		frame.getContentPane().add(buy);
	}
}
