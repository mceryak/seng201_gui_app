package game_objects.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_objects.Item;
import game_objects.MedicalItem;

/**
 * This class provides object for a single slot in the inventory. It can contain
 * an item or be empty.
 */
public class InventorySlot {

	// item represented by this slot
	private Item item;

	// label with image icon of item's icon
	private JLabel slot;

	// name of represented item
	private JLabel lblName;

	// panel behing lblName
	private JPanel pnlName;

	// label showing quantity of represented item
	private JLabel lblQuantity;

	// quantity of represented item
	private int quantity;

	// how much the represented item affects health, hunger, or tiredness
	private JLabel satisfaction;

	// action label
	private JLabel useItemLbl;

	// true if crew member is selected and able to use an item, false otherwise
	private boolean actionAvailable;

	/**
	 * Constructor for InventorySlot
	 * 
	 * @param isCartmanCoin True if this slot is representing inventory's
	 *                      CartmanCoin object. false otherwise
	 */
	public InventorySlot(boolean isCartmanCoin) {

		useItemLbl = new JLabel("Use Item");
		useItemLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		actionAvailable = false;

		item = null; // not representing any item when first initialized
		slot = new JLabel("");
		pnlName = new JPanel();

		if (isCartmanCoin) { // cartman-coin slot is set up differently
			lblQuantity = new JLabel("100");
			quantity = 100;
			lblName = new JLabel("Cartman-coin");
			pnlName.setBackground(new Color(37, 38, 38, 0));
			slot.setIcon(new ImageIcon(InventorySlot.class.getResource("/images_sp/cartmangold_54x54.png")));
		} else { // normal inventory slot
			lblQuantity = new JLabel("");
			quantity = 1;
			lblName = new JLabel("");
			slot.setIcon(null);
			pnlName.setBackground(new Color(37, 38, 38));
		}

		pnlName.setVisible(false);

		lblQuantity.setForeground(new Color(201, 165, 0));
		lblQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		satisfaction = new JLabel("");
		satisfaction.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		satisfaction.setForeground(new Color(0, 255, 48));
		satisfaction.setVisible(false);

		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setVisible(false);

		slot.addMouseListener(new MouseAdapter() {
		});
		hide();
	}

	/**
	 * set location of this slot
	 * 
	 * @param x Location on x-axis
	 * @param y Location on y-axis
	 */
	public void setBounds(int x, int y) {
		slot.setBounds(x, y, 54, 52);
		lblName.setBounds(x, y + 60, 110, 17);
		pnlName.setBounds(x, y + 60, 110, 17);
		useItemLbl.setBounds(x, y + 70, 70, 15);
		lblQuantity.setBounds(x + 50, y + 45, 20, 15);
		satisfaction.setBounds(x, y + 75, 90, 14);
	}

	/**
	 * Add all objects to the current frame
	 * 
	 * @param frame Frame on which to render objects
	 */
	public void addToFrame(JFrame frame) {
		frame.getContentPane().add(useItemLbl);
		frame.getContentPane().add(lblQuantity);
		frame.getContentPane().add(slot);
		frame.getContentPane().add(lblName);
		frame.getContentPane().add(satisfaction);
		frame.getContentPane().add(pnlName);

	}

	/**
	 * called when inventory is opened, show everything about the slot
	 */
	public void reveal() {
		if (item != null && actionAvailable) // slot is representing an item, and a crew member is selected
			useItemLbl.setVisible(true);
		lblQuantity.setVisible(true);
		slot.setVisible(true);
		slot.addMouseListener(new MouseAdapter() {
			/**
			 * display name of item and its benefits
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				useItemLbl.setVisible(false);
				lblName.setVisible(true);
				pnlName.setVisible(true);
				satisfaction.setVisible(true);
			}

			/**
			 * hide name of item and its benefits
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				if (item != null && actionAvailable)
					useItemLbl.setVisible(true);
				lblName.setVisible(false);
				pnlName.setVisible(false);
				satisfaction.setVisible(false);
			}
		});
	}

	/**
	 * called when inventory is closed. hide everything
	 */
	public void hide() {
		useItemLbl.setVisible(false);
		lblName.setVisible(false);
		satisfaction.setVisible(false);
		lblQuantity.setVisible(false);
		slot.setVisible(false);
		slot.removeMouseListener(slot.getMouseListeners()[0]);
	}

	/**
	 * hide action label. called when item gets removed
	 */
	public void hideUseItemLabl() {
		useItemLbl.setVisible(false);
	}

	/**
	 * called when shuffling down items after a removal
	 * 
	 * @param other Slot to take the place of this slot
	 */
	public void replaceSlot(InventorySlot other) {
		item = other.getItem();
		quantity = other.getQuantity();
		slot.setIcon(other.getItem().getIcon());
		lblName.setText(other.getItem().getName());
		lblQuantity.setText("" + quantity);
		satisfaction.setText(other.satisfaction.getText());
	}

	/**
	 * reset all labels that were once associated with an item
	 */
	public void resetSlot() {
		item = null;
		slot.setIcon(null);
		lblName.setText("");
		lblQuantity.setText("");
		satisfaction.setText("");
	}

	/**
	 * render an item in this slot
	 * 
	 * @param newItem  Item to be rendered
	 * @param quantity Amount to be rendered
	 */
	public void renderNewItem(Item newItem, int quantity) {
		if (actionAvailable && inventoryOpen()) // inventory is open and a crew member is selected
			useItemLbl.setVisible(true);
		item = newItem;
		slot.setIcon(newItem.getIcon());
		lblName.setText(newItem.getName());
		lblQuantity.setText("" + quantity);
		if (newItem instanceof MedicalItem)
			satisfaction.setText("Health +" + newItem.getValue());
		else
			satisfaction.setText("Hunger -" + newItem.getValue());
	}

	/**
	 * Modify the slot to show that an item was removed from the inventory
	 * 
	 * @param removedItem Item to be to be decremented in quantity
	 * @return true if removed entirely, false if only decremented
	 */
	public boolean removeItem(Item removedItem) {
		if (quantity == 1) {
			return true;
		} else {
			lblQuantity.setText("" + --quantity);
			return false;
		}
	}

	/**
	 * Change quantity of this item by a specified amount
	 * 
	 * @param mod Specified amount
	 */
	public void modifyQuantity(int mod) {
		quantity += mod;
		lblQuantity.setText("" + quantity);
	}

	/**
	 * let this slot know that a crew member is or is not selected
	 * 
	 * @param tf True -> show action label. False -> hide action label
	 */
	public void setActionAvailable(boolean tf) {
		actionAvailable = tf;
	}

	/**
	 * determines whether the inventory is currently open or not
	 * 
	 * @return true -> inventory is open. false -> inventory is not open
	 */
	public boolean inventoryOpen() {
		return lblQuantity.isVisible();
	}

	/**
	 * @return Item associated with this slot
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @return label associate with use item action
	 */
	public JLabel getUseItemLabel() {
		return useItemLbl;
	}

	/**
	 * @return quantity of item associated with this slot
	 */
	public int getQuantity() {
		return quantity;
	}
}
