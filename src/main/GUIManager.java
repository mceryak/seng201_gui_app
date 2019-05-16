package main;

import java.awt.EventQueue;

import crew_member_types.Medic;
import game_objects.Crew;
import game_objects.actions.ActionSet;
import game_objects.actions.SearchPlanet;
import game_objects.actions.Sleep;
import game_objects.actions.UseItem;
import main.GUIGame.DayWindow;
import main.GUIPregame.DaysNameSizeWindow;
import main.GUIPregame.PickCrewMembersWindow;
import main.GUIPregame.StartWindow;

/**
 * This class controls which GUI window we are currently looking at
 */
public class GUIManager {

	// Player's chosen crew
	private Crew crew;

	// last day before game is over
	private int lastDay;

	// day we start on
	private int curDay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUIManager man = new GUIManager();

		// run game in event queue dispatch thread
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				man.launchStartWindow();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIManager() {
		curDay = 1;
	}

	/**
	 * launch the start window
	 */
	public void launchStartWindow() {
		new StartWindow(this);
	}

	/**
	 * Close the start window and load next
	 * 
	 * @param sm StartWindow that was open
	 */
	public void closeStartWindow(StartWindow sm) {
		if (sm.closeWindow() == 1)
			launchRulesWindow();
		else
			launchDaysNameSizeWindow();

	}

	/**
	 * Launch Rules Window
	 */
	public void launchRulesWindow() {
		RulesWindow rw = new RulesWindow(this);
	}

	/**
	 * Close rules window and go back to start window
	 * 
	 * @param rw RulesWindow that was open
	 */
	public void closeRulesWindow(RulesWindow rw) {
		rw.closeWindow();
		launchStartWindow();
	}

	/**
	 * Launch DaysNameSize Window
	 */
	public void launchDaysNameSizeWindow() {
		new DaysNameSizeWindow(this);
	}

	/**
	 * Close DaysNameSize window and load pick crew members window
	 * 
	 * @param pw DaysNameSizeWindow that was open
	 */
	public void closeDaysNameSizeWindow(DaysNameSizeWindow pw) {
		pw.closeWindow();

		// get size of crew given by player
		int crewSize = pw.getCrewSize();

		// get last day chosen by player
		lastDay = pw.getDays();

		// compute number of pieces needed to find to win
		int pieces = lastDay * 2 / 3;

		// get crew name given by player
		String crewName = pw.getCrewName();

		// get spacebus name given by player
		String spacebusName = pw.getSpacebusName();

		// create crew
		crew = new Crew(crewName, spacebusName, pieces);

		// have player pick crew members
		launchPickCrewMembersWindow(crew, crewSize);
	}

	/**
	 * Launch PickCrewMembers window
	 * 
	 * @param crew     Crew that was just created by player
	 * @param crewSize Size of crew just chosen by player
	 */
	public void launchPickCrewMembersWindow(Crew crew, int crewSize) {
		new PickCrewMembersWindow(this, crew, crewSize);
	}

	/**
	 * close PickCrewMembers window and load Day window
	 * 
	 * @param pcmw PickCrewMembersWindow that was just open
	 */
	public void closePickCrewMembersWindow(PickCrewMembersWindow pcmw) {
		pcmw.closeWindow();

		// setup actions now that crew is set
		ActionSet actionList = ActionSet.getInstance();
		actionList.addAction(new Sleep());
		actionList.addAction(new SearchPlanet(crew));
		actionList.addAction(
				new UseItem(crew.getInventory(), crew.getAbilityIdentifiers().contains(Medic.abilityIdentifier)));

		// launch day window
		launchDayWindow();
	}

	/**
	 * Launch Day window
	 */
	public void launchDayWindow() {
		new DayWindow(this, curDay++, lastDay, crew);
	}

	/**
	 * close day window and check if we play again or continue
	 * 
	 * @param dw DayWindow that was just open
	 */
	public void closeDayWindow(DayWindow dw) {
		int gameOver = dw.closeWindow();
		if (gameOver == 1) { // game is over, player hit play again
			curDay = 1;
			launchStartWindow();
		} else // game is not over
			launchDayWindow();
	}
}
