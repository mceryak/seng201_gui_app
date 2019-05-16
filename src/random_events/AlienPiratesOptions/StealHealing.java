package random_events.AlienPiratesOptions;

import java.util.HashMap;

import game_objects.MedicalItem;
import game_objects.inventory.Inventory;

/**
 * This class is one of the options for alien pirates to raid crew's inventory.
 * They attemp to steal a medical item.
 */
public class StealHealing {

	/**
	 * attempt to remove a medical item if one exists in the inventory
	 * 
	 * @param inventory Inventory to check for medical items
	 * @return String stating what was stolen if anything
	 */
	public String steal(Inventory inventory) {

		HashMap<MedicalItem, Integer> medMap = inventory.getMedicalItems();

		if (medMap.size() > 0) { // crew has meds to be stolen
			Object[] meds = medMap.keySet().toArray();
			MedicalItem stolenMed = (MedicalItem) meds[(int) (Math.random() * medMap.size())];
			inventory.removeItem(stolenMed);
			System.out.println("They got away with 1 " + stolenMed.getName());
			return "-1 " + stolenMed.getName();
		} else {
			return "";
		}
	}

}
