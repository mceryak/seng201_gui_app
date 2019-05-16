package game_objects.actions;

import java.util.HashSet;

import javax.swing.JLabel;

import game_objects.FoodItem;
import game_objects.Item;
import game_objects.MedicalItem;
import game_objects.crew_member.CrewMember;
import game_objects.inventory.Inventory;
import game_objects.inventory.InventorySlot;

/**
 * This class represents the UseItem action. It does not perform an action
 * itself, but creates all useitem actions for all items in player's inventory
 */
public class UseItem extends Action {

	// player's inventory
	private Inventory inventory;

	/**
	 * Constructor for UseItem action
	 * 
	 * @param invntry  player's inventory
	 * @param hasMedic True if a medic is alive in the crew, false otherwise
	 */
	public UseItem(Inventory invntry, boolean hasMedic) {

		// set a blank action label
		super(new JLabel(""));
		inventory = invntry;

		// get each slot in inventory
		InventorySlot[] slots = inventory.getGui().getSlots();

		for (int i = 0; i < 9; i++) { // create an action for each slot
			InventorySlot slot = slots[i];
			Action action = new Action(slot.getUseItemLabel()) {

				/**
				 * apply item from this slot to the selected crew member and remove this item
				 * from the inventory
				 */
				@Override
				public void performAction(CrewMember cm) {
					super.performAction(cm);
					Item item = slot.getItem();
					inventory.removeItem(item);
					if (item instanceof FoodItem) {
						cm.eatFood((FoodItem) item);
					} else {
						cm.heal((MedicalItem) item, hasMedic);
					}
				}

				/**
				 * display only if this slot contains an item
				 */
				@Override
				public void display() {
					slot.setActionAvailable(true);
					if (slot.getItem() != null && slot.inventoryOpen())
						super.display();
				}

				@Override
				public void hide() {
					slot.setActionAvailable(false);
					super.hide();
				}

				@Override
				public boolean shouldShowAction(HashSet<Integer> blackList) {
					return !blackList.contains(1);
				}

			};

			// set location according to slot position
			action.setBounds(424 + (int) (i * 66.5), 85, 57, 17);

			// add to set of all actions
			ActionSet.getInstance().addAction(action);
		}
	}

	/**
	 * change inventory label to white so player knows actions exist in the
	 * inventory
	 */
	@Override
	public void display() {
		inventory.getGui().whiteInventoryLabel();
	}

	/**
	 * change inventory label color back to normal
	 */
	@Override
	public void hide() {
		inventory.getGui().blackInventoryLabel();
	}

	/**
	 * this action has no event
	 */
	@Override
	public void performAction(CrewMember cm) {
	}

	/**
	 * do not display if crew member has to be a pilot
	 */
	@Override
	public boolean shouldShowAction(HashSet<Integer> blackList) {
		return !blackList.contains(1);
	}
}
