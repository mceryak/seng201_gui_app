package random_events.AlienPiratesOptions;

import game_objects.inventory.Inventory;

/**
 * This class is one of the options for alien pirates to raid crew's inventory.
 * They attemp to steal money.
 */
public class StealMoney {

	/**
	 * Pirate Aliens succeed in stealing money if the crew has any money, otherwise
	 * it fails. Stolen money is deducted from the crew's balance.
	 * 
	 * @param inventory Inventory is needed to modify quantity of cartman-coins
	 * 
	 * @return int Amount stolen
	 */
	public String steal(Inventory inventory) {

		// get crew's balance
		int balance = inventory.getCCAmount();

		if (balance > 0) { // crew has money to be stolen by the pirates

			// set probabilities for a certain amount being stolen and choose one randomly
			int[] probabilities = new int[] { 10, 10, 10, 15, 20, 25 };
			int amt = probabilities[(int) (Math.random() * 6)];

			// if (ammount decided > current balance), then set ammount to current balance
			amt = amt > balance ? balance : amt;

			// remove amount from balance and report balance
			inventory.modifyCCAmount(-amt);
			inventory.getGui().modifyCC(-amt);

			return amt + " gold";

		} else { // crew has no money, try another option

			return "";

		}
	}

}