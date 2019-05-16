package random_events;

import game_objects.Crew;
import game_objects.RandomEvent;
import random_events.AlienPiratesOptions.StealFood;
import random_events.AlienPiratesOptions.StealHealing;
import random_events.AlienPiratesOptions.StealMoney;

/**
 * This class is 1 of 3 possible classes called at the start of each new day
 * after the first. Alien pirates board the crew's spacebus
 */
public class AlienPirates implements RandomEvent {

	/**
	 * Alien pirates steal one of: cartman-coin, food item, medical item
	 */
	public String causeChaos(Crew crew) {

		String ret = "Last night, alien pirates raided your inventory. ";

		String stolenGoods = "";

		// choose random category of item
		int randomCategory = (int) (Math.random() * 3);

		// make sure we try all three options, one time each
		int count = 0;

		while (++count < 4)
			switch (randomCategory) {
			case 0: // pirates attempt to steal (random) amount gold based on probabilities
				stolenGoods = new StealMoney().steal(crew.getInventory());
				if (stolenGoods.length() > 0) { // steal successful
					return ret + "-" + stolenGoods;
				} else { // try to steal something else
					randomCategory++;
				}

				break;

			case 1: // pirates attempt to steal food
				stolenGoods = new StealFood().steal(crew.getInventory());
				if (stolenGoods.length() > 0) { // steal successful
					return ret + stolenGoods;
				} else { // try to steal something else
					randomCategory++;
				}

				break;

			case 2: // pirates steal healing items
				stolenGoods = new StealHealing().steal(crew.getInventory());
				if (stolenGoods.length() > 0) { // steal successful
					return ret + stolenGoods;
				} else { // try to steal something else
					randomCategory = 0;
				}
			}

		// Nothing in crew's inventory
		return "Alien pirates raided your inventory, but they couldn't find anything to steal, so they insulted you instead.";

	}
}