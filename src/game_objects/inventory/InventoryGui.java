package game_objects.inventory;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import game_objects.Item;
import main.GUIGame.DayWindow;

/**
 * GUI represenation of Inventory
 */
public class InventoryGui {

	// array containing each inventory slot
	private InventorySlot[] slots = new InventorySlot[10];

	// space containing each inventory slot
	private JLabel inventorySpace;

	// inventory text label
	private JLabel inventoryLbl;

	// inventory image
	private JLabel inventory;

	// item just added
	private ItemAdded added;

	public InventoryGui() {

		added = new ItemAdded();

		// initialize inventory slots

		for (int i = 0; i < 10; i++) {
			InventorySlot slot = new InventorySlot(i == 9 ? true : false); // last slot is for the gold
			slot.setBounds(424 + (int) (i * 66.5), 25);
			slots[i] = slot;
		}

		// inventory text label

		inventoryLbl = new JLabel("Inventory");
		inventoryLbl.setBounds(1125, 75, 70, 40);
		inventoryLbl.setForeground(new Color(28, 28, 28));

		// inventory space

		inventorySpace = new JLabel("");
		inventorySpace.setIcon(new ImageIcon(DayWindow.class.getResource("/images_sp/inventory_space_700x116.png")));
		inventorySpace.setBounds(400, 10, 700, 116);
		inventorySpace.setVisible(false);

		// inventory icon

		inventory = new JLabel("");
		inventory.setIcon(new ImageIcon(DayWindow.class.getResource("/images_sp/inventory_50x63.png")));
		inventory.setBounds(1130, 10, 55, 88);
		inventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // change image to show label is clickable
				inventory
						.setIcon(new ImageIcon(DayWindow.class.getResource("/images_sp/inventory_selected_55x68.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) { // change image back to normal
				inventory.setIcon(new ImageIcon(DayWindow.class.getResource("/images_sp/inventory_50x63.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) { // open/close inventory space

				if (inventorySpace.isVisible()) { // hide inventory space and each slot
					for (InventorySlot slot : slots) {
						slot.hide();
					}
					inventorySpace.setVisible(false);
				} else { // show inventory space and each slot
					for (InventorySlot slot : slots) {
						slot.reveal();
					}
					inventorySpace.setVisible(true);
				}
			}
		});
	}

	/**
	 * 
	 * 
	 * @param item
	 * @param amt
	 */
	public void renderItem(Item item, int amt) {

		// show item added animation

		added.setNewItem(item, amt);

		SwingWorker<Integer[], Void> bgThread = new SwingWorker<>() {

			/**
			 * find which slot to add/increment item
			 * 
			 * @return int array of size 2 with index 0 corresponding to inventory slot,
			 *         index 2 corresponding to either of: 0 -> modify item quantity, 1 ->
			 *         render new item
			 * @throws Exception
			 */
			@Override
			protected Integer[] doInBackground() throws Exception { // run background thread

				// get names for each slot to compare later
				String[] names = new String[10];
				for (int i = 0; i < 10; i++) {
					names[i] = slots[i].getItem() != null ? slots[i].getItem().getName() : "";
				}

				if (amt > 1) { // we know item is cartman-coin
					return new Integer[] { 9, 1 };
				}
				int i = 0;
				Integer[] ret = new Integer[] { 0, 0 }; // index 2 default = 0 (new item)
				while (names[i] != "")
					if (names[i] == item.getName()) { // item exists in slot i, so modify quantity
						ret[0] = i;
						ret[1] = 1;
						break;
					} else
						i++;

				// render new item in next available inventory slot
				ret[0] = i;
				return ret;
			}

			/**
			 * show that item has been added in gui
			 */
			@Override
			protected void done() {
				Integer[] ret;
				try {

					ret = get();

					if (ret[1] == 1) {
						slots[ret[0]].modifyQuantity(amt);
					} else {
						slots[ret[0]].renderNewItem(item, 1);
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		bgThread.execute();
	}

	/**
	 * show new cartman-coin quantity in gui
	 *
	 * @param amt positive/negative amount to add to quantity
	 */
	public void modifyCC(int amt) {
		slots[9].modifyQuantity(amt);
	}

	/**
	 * show removal of item or decrement of item quantity in gui
	 * 
	 * @param item Item to be removed or decremented in quantity
	 */
	public void removeOne(Item item) {
		int i;

		// find corrrect slot
		for (i = -1; !slots[++i].getItem().equals(item);)
			;
		if (slots[i].removeItem(item)) { // item was removed entirely, so we need to shuffle down rest of inventory
			slots[i].resetSlot();
			while (slots[++i].getItem() != null) {
				slots[i - 1].replaceSlot(slots[i]);
				slots[i].resetSlot();
			}

			// hide action option for previously filled inventory slot
			slots[i - 1].hideUseItemLabl();
		}
	}

	/**
	 * @return array of inventory slots
	 */
	public InventorySlot[] getSlots() {
		return slots;
	}

	/**
	 * Actions are avaiable, so change color
	 */
	public void whiteInventoryLabel() {
		inventoryLbl.setForeground(new Color(255, 255, 255));
	}

	/**
	 * Actions no longer available, so change color back to normal
	 */
	public void blackInventoryLabel() {
		inventoryLbl.setForeground(new Color(28, 28, 28));
	}

	/**
	 * put each slot in the currently visible frame
	 * 
	 * @param frame Currently visible frame
	 */
	public void render(JFrame frame) {

		for (int i = 0; i < 10; i++) {
			slots[i].addToFrame(frame);
		}

		// item added display

		added.render(frame);

		frame.getContentPane().add(inventoryLbl);
		frame.getContentPane().add(inventorySpace);
		frame.add(inventory);
	}
}
