package main.GUIGame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import crew_member_types.Barter;
import crew_member_types.Chef;
import game_objects.Crew;
import game_objects.FoodItem;
import game_objects.actions.ActionSet;
import game_objects.crew_member.Character;
import game_objects.crew_member.CharacterPane;
import game_objects.crew_member.CrewMember;
import main.GUIManager;
import main.GUIGame.space_outpost.SpaceOutpostMenu;
import random_events.CreateRandomEvent;

/**
 * Window showing current day and presenting the player with status of crew
 * members and ability to perform actions. Main part of the game is here.
 */
public class DayWindow {

	// number of crew members that have > 0 actions remaining
	public static int haveActions;

	// set of pilots for the current day
	public static HashSet<CrewMember> pilots;

	// frame being rendered
	private JFrame frame;

	// window controller
	private GUIManager man;

	// current day we are on
	private int curDay;

	// last day before game is over
	private int lastDay;

	// crew chosen by player
	private Crew crew;

	// 0 means game still going, 1 means game over and play again
	private int playAgain = 0;

	/**
	 * Constructor for day window. called once per day.
	 * 
	 * @param manager     window controller
	 * @param cur         current day we are on
	 * @param last        last day before game is over
	 * @param playersCrew crew chosen by player
	 */
	public DayWindow(GUIManager manager, int cur, int last, Crew playersCrew) {
		man = manager;
		curDay = cur;
		lastDay = last;
		crew = playersCrew;

		haveActions = crew.numAlive();
		pilots = new HashSet<>();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				initialize();
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

//		System.out.println("day window initialize: " + SwingUtilities.isEventDispatchThread());

		// frame

		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		// game over panel

		GameOver gameOverScreen = new GameOver(frame, new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playAgain = 1;
				finishedWindow();
			}
		});

		// reset pilot count to 0

		crew.getShip().getGui().resetAction();

		// add all actions to this frame

		ActionSet.getInstance().addActionsToFrame(frame);

		// render blank inventory spaces to be filled in later

		crew.getInventory().getGui().render(frame);

		// Next Day button and space outpost

		JButton btnNextDay = new JButton("");

		SpaceOutpostMenu spw = new SpaceOutpostMenu(crew.getInventory(), btnNextDay,
				crew.getAbilityIdentifiers().contains(Barter.abilityIdentifier));
		spw.render(frame);

		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (crew.getShip().getPiecesToFind() == 0) { // game won
					StoryLine.updateLabel("You've found every missing piece. Your crew can now return home!");
					gameOverScreen.render(curDay, lastDay, crew);
					return;
				}

				if (NewDayEvents.startDay(crew)) { // only 1 crew member, can't fly spacebus
					StoryLine.updateLabel("You can't fly to a new planet with just one crew member. Game over. Sorry.");
					gameOverScreen.render(curDay, lastDay, crew);
					return;
				}

				if (++curDay > 1) { // do random event
					StoryLine.updateLabel(new CreateRandomEvent().create(crew));
				}

				// check if game is over
				int numAlive = crew.numAlive();
				if (numAlive == 0 || curDay > lastDay || crew.getShip().getShieldHealth() == 0) {
					System.out.println("here");
					btnNextDay.setEnabled(false);
					if (numAlive == 0)
						StoryLine.updateLabel("The rest of your crew died over night");
					else if (curDay > lastDay)
						StoryLine.updateLabel("You're out of time");
					else
						StoryLine.updateLabel("Your ship has been completely destroyed");
					gameOverScreen.render(curDay - 1, lastDay, crew);
				} else
					finishedWindow();
			}
		});
		btnNextDay.setIcon(new ImageIcon(DayWindow.class.getResource("/images/nextDayButton2.png")));
		btnNextDay.setBounds(frame.getWidth() - 170, frame.getHeight() - 114, 150, 74);
		frame.getContentPane().add(btnNextDay);

		// character pane

		new CharacterPane(crew, frame, 16);

		// character images on planet

		int x = 445;
		int y = 280;

		for (CrewMember cm : crew.getCrewMembers().keySet()) {
			if (crew.getCrewMembers().get(cm)) { // only render if they are alive
				Character c = cm.getGuiObject().getCharacter();
				c.render(frame, x, y - (c.getHeight() - 100));
				x += c.getWidth() + 10;
			}
		}

		// spaceship and status

		crew.getShip().getGui().render(frame);

		// add story line dialog to frame

		StoryLine.initialize(frame);

		// if crew contains a chef, throw in a random food item every other day

		if (curDay % 2 == 0 && crew.getAbilityIdentifiers().contains(Chef.abilityIdentifier)) {
			FoodItem f = FoodItem.allFoods[(int) (Math.random() * 6)];
			crew.getInventory().addItem(f);
		}

		// background design

		BackgroundDesign.render(frame, curDay, lastDay);

	}

	/**
	 * @return Integer determining whether game is still going or game is over and
	 *         player wants to play again (0 or 1, respectively).
	 */
	public int closeWindow() {
		frame.dispose();
		return playAgain;
	}

	/**
	 * done with current day, load next window
	 */
	public void finishedWindow() {
		man.closeDayWindow(this);
	}

}
