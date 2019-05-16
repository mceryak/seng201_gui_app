package game_objects.actions;

import java.util.HashSet;

import javax.swing.JFrame;

import game_objects.crew_member.CrewMember;
import main.GUIGame.DayWindow;

/**
 * This object contains instances of Action. It implements the Singleton concept
 * and is instantiated only once
 */
public class ActionSet {

	// set of all actions
	private HashSet<Action> actions;

	// crew member selected to perform an action
	private CrewMember performer;

	// boolean telling us if city wok menu is open
	private boolean panelOpen;

	// only instance of this class
	private static ActionSet single_instance = null;

	/**
	 * private constructor only available in ActionSet class
	 */
	private ActionSet() {

		// menu isn't open until it is clicked
		panelOpen = false;

		// initialize set containing all actions
		actions = new HashSet<>();
	}

	/**
	 * A panel has been opened that disables clicking of characters, hide all
	 * actions and keep hidden while it is open
	 */
	public void openPanel() {
		removePerformer();
		panelOpen = true;
	}

	/**
	 * City wok space outpost has been closed, allow actions to be displayed again
	 */
	public void closePanel() {
		panelOpen = false;
	}

	/**
	 * create only instance of ActionSet class
	 * 
	 * @return The instance
	 */
	public static ActionSet getInstance() {
		if (single_instance == null) {
			single_instance = new ActionSet();
		}
		return single_instance;
	}

	/**
	 * Add a new Action to the set of all actions
	 * 
	 * @param a New Action
	 */
	public void addAction(Action a) {
		actions.add(a);
	}

	/**
	 * Add all actions to the current frame so they can be seen
	 * 
	 * @param frame Frame to be added on
	 */
	public void addActionsToFrame(JFrame frame) {
		for (Action a : actions) {
			a.addToFrame(frame);
		}
	}

	/**
	 * @return currently selected Crew Member
	 */
	public CrewMember getPerformer() {
		return performer;
	}

	/**
	 * find number of crew members chosen as pilots that have actions left. This is
	 * used in determining whether next selected crew member needs to be a pilot
	 * 
	 * @return Number of pilots with an action remaining
	 */
	private int pilotsWithActions() {
		int ret = 0;
		for (CrewMember cm : DayWindow.pilots) {
			if (cm.getActionsLeft() > 0)
				ret++;
		}
		return ret;
	}

	/**
	 * A crew member has been selected, so set up possible actions that this crew
	 * member can perform
	 * 
	 * @param cm Crew Member selected to perform an action
	 */
	public void setPerformer(CrewMember cm) {

		if (!panelOpen) { // city wok outpost isn't open, so we can display actions

			// 0 -> unable to be a pilot
			// 1 -> unable to use an item
			// 2 -> unable to search planet
			// 3 -> unable to sleep
			// 4 -> unable to repair ship
			HashSet<Integer> blackList = new HashSet<>();

			if (cm.getActionsLeft() == 1 && !DayWindow.pilots.contains(cm)
					&& DayWindow.haveActions - pilotsWithActions() - (2 - DayWindow.pilots.size()) == 0) // must be a
																											// pilot
			{
				blackList.add(1);
				blackList.add(2);
				blackList.add(3);
				blackList.add(4);
			} else {
				if (DayWindow.pilots.size() == 2 || DayWindow.pilots.contains(cm)) // already enough pilots, or cm is
																					// already a pilot
					blackList.add(0);
			}

			// update performer
			performer = cm;

			// show all available actions
			for (Action a : actions) {
				if (a.shouldShowAction(blackList))
					a.setPerformer(cm);
			}
		}
	}

	/**
	 * Crew Member has been deselected or just performed an action. either way, show
	 * that this crew member has been deselected
	 */
	public void removePerformer() {
		performer = null;
		for (Action a : actions) {
			a.setPerformer(null);
		}
	}
}
