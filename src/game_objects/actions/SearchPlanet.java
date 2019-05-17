package game_objects.actions;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import game_objects.Crew;
import game_objects.FoodItem;
import game_objects.MedicalItem;
import game_objects.crew_member.CrewMember;
import main.GUIGame.StoryLine;

/**
 * This class represents the SearchPlanet action
 */
public class SearchPlanet extends Action {

	// if true, no longer possible to find another ship part on current day
	public static boolean foundSpaceBusPart = false;

	// crew chosen by player
	private Crew crew;

	public SearchPlanet(Crew playersCrew) {

		// define label text
		super("Search Planet");

		// set location so it is over rubble drawing
		super.setBounds(680, 547, 140, 20);

		// set crew
		crew = playersCrew;
	}

	/**
	 * Search the planet for an item, spacebus part, or nothing. If item is found,
	 * add item to crew's inventory. If spacebus part is found, decrement missing
	 * pieces. If nothing, do nothing.
	 *
	 * @param c Crew Member performing search of planet
	 */
	@Override
	public void performAction(CrewMember c) {

		// decrement amount of actions this crew member has left
		super.performAction(c);

		SwingWorker<Integer[], Void> bgThread = new SwingWorker<>() { // run in background thread

			/**
			 * @return int array of size 2. index 0 = search option. index 1 = not null iff
			 *         search option describes finding a food or medical item. If not null,
			 *         then index 1 describes the index of the randomly chosen item.
			 * @throws Exception
			 */
			@Override
			protected Integer[] doInBackground() throws Exception {

				// randomly choose option, giving pioneer type better odds
				int option = (int) (Math.random() * (foundSpaceBusPart ? 4 : (c.getType() == "Pioneer" ? 6 : 5)));

				// if food or medical item, choose random item
				int randIndex;

				switch (option) {
				case 0:

					// find cartman-coins

					// P(10) = 4/7. P(20) = 2/7. P(30) = 1/7
					int[] ccProbabilities = new int[] { 10, 10, 10, 10, 20, 20, 30 };
					int amt = ccProbabilities[(int) (Math.random() * 6)];
					return new Integer[] { amt };

				case 1:

					// found food item

					randIndex = (int) (Math.random() * 6);
					return new Integer[] { 1, randIndex };

				case 2:

					// found medical item

					randIndex = (int) (Math.random() * 3);
					return new Integer[] { 2, randIndex };

				case 3:

					// found nothing

					return new Integer[] { 3 };

				case 4: // found space-bus part
				case 5: // found space-bus part
					foundSpaceBusPart = true;
				}
				return new Integer[] { 4 };
			}

			@Override
			protected void done() {
				try {
					Integer[] ret = get();

					if (c.getType() == "Pioneer" && ret[0] != 3) // if pioneer found something, decrease their hunger
						c.modifyHunger(-20);

					switch (ret[0]) {
					case 1: // found food item
						StoryLine.updateLabel(c.getName() + " found " + FoodItem.allFoods[ret[1]].getName());
						crew.getInventory().addItem(FoodItem.allFoods[ret[1]]);
						break;
					case 2: // found medical item
						StoryLine.updateLabel(c.getName() + " found " + MedicalItem.allMeds[ret[1]].getName());
						crew.getInventory().addItem(MedicalItem.allMeds[ret[1]]);
						break;
					case 3: // found nothing
						StoryLine.updateLabel(
								c.getName() + " didn't find anything. Or if they did, they kept it secret");
						break;
					case 4: // found spacebus part
						StoryLine.updateLabel(c.getName() + " found a missing piece of your spacebus!");
						c.modifyHunger(-80);
						crew.getShip().findPiece();
						break;
					case 10: // found 10 cartman-coins
					case 20: // found 20 cartman-coins
					case 30: // found 30 cartman-coins
						StoryLine.updateLabel(c.getName() + " found " + ret[0] + " Cartman-coins!");
						crew.getInventory().modifyCCAmount(ret[0]);
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		bgThread.execute();
	}

	/**
	 * Show action when selected crew member does not have to be a pilot
	 */
	@Override
	public boolean shouldShowAction(HashSet<Integer> blackList) {
		return !blackList.contains(2);
	}
}
