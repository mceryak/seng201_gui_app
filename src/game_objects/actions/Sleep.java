package game_objects.actions;

import java.util.HashSet;

import game_objects.crew_member.CrewMember;
import main.GUIGame.StoryLine;

/**
 * This class represents the Sleep action
 */
public class Sleep extends Action {

	/**
	 * Constructor for Sleep action
	 */
	public Sleep() {

		// describe label for this action
		super("Sleep");

		// se location on top of bed drawing
		super.setBounds(560, 500, 105, 90);
	}

	/**
	 * Show this action when the selected crew member does not have to be a pilot
	 */
	@Override
	public boolean shouldShowAction(HashSet<Integer> blackList) {
		return !blackList.contains(3);
	}

	/**
	 * reduce tiredness of selected crew member
	 */
	@Override
	public void performAction(CrewMember cm) {
		StoryLine.updateLabel(cm.getName() + " feels more alert after a nice rest");
		super.performAction(cm);
		cm.sleep();
	}

}
