package game_objects.actions;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game_objects.crew_member.CrewMember;

/**
 * An Action is when a crew member clicks on an Action's label. The Action event
 * is performed
 */
public abstract class Action {

	// Actions' label. click on to perform action with crew member
	private JLabel label;

	// Crew Member performing the action
	private CrewMember performer;

	/**
	 * Constructor for Action
	 * 
	 * @param l Label created already
	 */
	public Action(JLabel l) {
		label = l;
		initialize();
	}

	/**
	 * Constructor for Action
	 * 
	 * @param eventName String used to create label
	 */
	public Action(String eventName) {
		label = new JLabel(eventName);
		initialize();
	}

	/**
	 * Don't show action's label because crew member cannot currently perform this
	 * action
	 */
	public void hideAction() {
		setPerformer(null);
	}

	/**
	 * Determine if this action should be shown as an option for the crew member to
	 * perform
	 * 
	 * @param blackList Set containing ints representing which actions cannot be
	 *                  performed
	 * @return True or False, should show action or shouldn't show action
	 */
	public abstract boolean shouldShowAction(HashSet<Integer> blackList);

	/**
	 * finish setting up label. add mouse listener for entering and clicking.
	 */
	private void initialize() {

		label.setVisible(false); // not visible until crew member is selected
		label.setForeground(new Color(255, 255, 255));
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) { // change to yellowish color
				label.setForeground(new Color(255, 205, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) { // change back to white
				label.setForeground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) { // perform action with selected crew member

				// decrement actions left of performer
				performer.performAction();

				// perform the action's event
				performAction(performer);
			}
		});
	}

	/**
	 * Set location and width and height of the action's label
	 * 
	 * @param x Location on X-Axis
	 * @param y Location on Y-Axis
	 * @param w Width
	 * @param h Height
	 */
	public void setBounds(int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
	}

	/**
	 * Add the action's label to the current frame
	 * 
	 * @param frame Current DayWindow frame
	 */
	public void addToFrame(JFrame frame) {
		frame.getContentPane().add(label);
	}

	/**
	 * Crew member has either been selected or deselected
	 * 
	 * @param cm CrewMember being selected or null
	 */
	public void setPerformer(CrewMember cm) {

		if (cm == null) // deselected, or attempted selection of crew member with 0 actions
			hide();
		else if (performer == null) // selected. label not currently displayed, so display
			display();

		// update the performer
		performer = cm;
	}

	/**
	 * Action event being performed. Deselect crew member. Implement this method
	 * further in extensions of this class
	 * 
	 * @param cm Crew Member performing action.
	 */
	public void performAction(CrewMember cm) {
		ActionSet.getInstance().removePerformer();
	}

	/**
	 * This method is called when this action is determined to be a valid option for
	 * the currently selected crew member. display label to player
	 */
	public void display() {
		label.setVisible(true);
	}

	/**
	 * hide label when crew member is deselected
	 */
	public void hide() {
		label.setVisible(false);
	}
}
