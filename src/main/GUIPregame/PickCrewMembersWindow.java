package main.GUIPregame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import crew_member_types.Barter;
import crew_member_types.Chef;
import crew_member_types.Juggernaut;
import crew_member_types.Medic;
import crew_member_types.Pilot;
import crew_member_types.Pioneer;
import game_objects.Crew;
import game_objects.Images;
import game_objects.crew_member.CrewMember;
import main.GUIManager;

/**
 * This class lets the player pick their crew members
 */
public class PickCrewMembersWindow {

	// visible frame
	private JFrame frame;

	// gui controller
	private GUIManager man;

	// player's picked crew
	private Crew crew;

	// pre-determined size of crew
	private int crewSize;

	// size of crew as it is picked
	private int curCrewSize;

	// temporary and modifiable list of crew members
	private ArrayList<CrewMember> tempCrew = new ArrayList<>();

	// So two crew members don't have the same name
	private HashSet<String> namesBlackList = new HashSet<>();

	// crew member types
	private final String[] types = new String[] { "Pioneer", "Medic", "Pilot", "Juggernaut", "Barter", "Chef" };

	// current type of crew member being chosen;
	private String curType = "";

	// ready to start game button
	private JButton btnReady;

	// filled in as crew members are picked
	private JLabel[] memberSpots = new JLabel[4];

	// labels of the names of the crew members that are picked
	private JLabel[] memberSpotLabels = new JLabel[4];

	// remove buttons below each of the filled member spots
	private JButton[] removeSpots = new JButton[4];

	// pick buttons below each crew member type
	private JButton[] pickButtons = new JButton[6];

	// labels shown in place of pick button once one of a type of crew member has
	// been picked
	private JLabel[] maxReachedLabels = new JLabel[6];

	/**
	 * Constructor for PickCrewMembersWindow
	 * 
	 * @param manager        gui controller
	 * @param crewToBeFormed crew that has a specified size
	 * @param size           specified size of the crew
	 */
	public PickCrewMembersWindow(GUIManager manager, Crew crewToBeFormed, int size) {
		man = manager;
		crew = crewToBeFormed;
		crewSize = size;
		curCrewSize = 0;
		initialize();
		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * dispose the frame
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * close this window and open next
	 */
	public void finishedWindow() {
		man.closePickCrewMembersWindow(this);
	}

	/**
	 * Player opting for different crew member
	 * 
	 * @param start index after the removed Crew Member (to shuffle down rest of
	 *              crew)
	 */
	private void removeCrewMember(int start) {

		// crew is no longer ready to start game
		btnReady.setEnabled(false);

		// get crew member being removed and type
		CrewMember toRemove = tempCrew.get(start - 1);
		tempCrew.remove(toRemove);
		String type = toRemove.getType();
		int index;
		for (index = 0; type != types[index++];)
			;

		// reset pick button for removed crew member type
		pickButtons[--index].setVisible(true);
		maxReachedLabels[index].setVisible(false);

		// removed name can be used again
		namesBlackList.remove(toRemove.getName());

		// start shuffling down rest of crew
		int count = start;
		JLabel moveUp = memberSpots[count];

		if (moveUp == null || moveUp.getIcon() == null) // none came after
			memberSpotLabels[count - 1].setText("Empty"); // set name of removed to 'Empty'
		else
			while (moveUp.getIcon() != null) { // at least one came after

				// shuffle this one down 1 index
				ImageIcon temp = (ImageIcon) moveUp.getIcon();
				memberSpotLabels[count - 1].setText(memberSpotLabels[count].getText());
				memberSpotLabels[count].setText("Empty");
				memberSpots[count++ - 1].setIcon(temp);
				if (count == crewSize)
					break;

				// continue shuffling down
				moveUp = memberSpots[count];
			}

		// current crew size decremented
		curCrewSize--;
		memberSpots[count - 1].setIcon(null);
		removeSpots[count - 1].setVisible(false);
	}

	/**
	 * add a crew member to the temporary list and show in memberspots
	 * 
	 * @param name Name given to new crew member by player
	 */
	public void addCrewMember(String name) {

		// show that this crew member can be removed
		removeSpots[curCrewSize].setVisible(true);

		// no longer able to use this name
		namesBlackList.add(name);

		switch (curType) { // update labels for current type
		case "Pioneer":
			pickButtons[0].setVisible(false);
			maxReachedLabels[0].setVisible(true);
			memberSpots[curCrewSize].setIcon(Images.pioneerSquare);
			tempCrew.add(new Pioneer(name));
			break;
		case "Medic":
			pickButtons[1].setVisible(false);
			maxReachedLabels[1].setVisible(true);
			memberSpots[curCrewSize].setIcon(Images.medicSquare);
			tempCrew.add(new Medic(name));
			break;
		case "Pilot":
			pickButtons[2].setVisible(false);
			maxReachedLabels[2].setVisible(true);
			memberSpots[curCrewSize].setIcon(Images.pilotSquare);
			tempCrew.add(new Pilot(name));
			break;
		case "Juggernaut":
			pickButtons[3].setVisible(false);
			maxReachedLabels[3].setVisible(true);
			memberSpots[curCrewSize].setIcon(Images.juggernautSquare);
			tempCrew.add(new Juggernaut(name));
			break;
		case "Barter":
			pickButtons[4].setVisible(false);
			maxReachedLabels[4].setVisible(true);
			memberSpots[curCrewSize].setIcon(Images.barterSquare);
			tempCrew.add(new Barter(name));
			break;
		case "Chef":
			pickButtons[5].setVisible(false);
			maxReachedLabels[5].setVisible(true);
			memberSpots[curCrewSize].setIcon(Images.chefSquare);
			tempCrew.add(new Chef(name));
			break;
		}

		// display this crew member's icon and name in memberspots
		memberSpotLabels[curCrewSize].setText(name);
		memberSpotLabels[curCrewSize].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		memberSpotLabels[curCrewSize].setForeground(new Color(255, 255, 255));

		curCrewSize++;

		if (curCrewSize == crewSize) // ready to start game
			btnReady.setEnabled(true);
	}

	/**
	 * disable pick buttons when enter name panel shows up. enable on close
	 * 
	 * @param tf True -> enable. False -> disable
	 */
	public void changeStateButtons(boolean tf) {
		for (JButton button : pickButtons) {
			button.setEnabled(tf);
		}
	}

	/**
	 * when pick button is clicked, show enter name panel and diable pick buttons
	 * 
	 * @param enterNamePanel Panel that allows user to give their new crew member a
	 *                       name
	 * @param type           Crew member type
	 * @return ActionListener for pick button
	 */
	private ActionListener pickButtonsActionListener(EnterNamePanel enterNamePanel, String type) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (curCrewSize == crewSize) {
					return;
				}
				changeStateButtons(false);
				enterNamePanel.showPanel(true, "Give your " + type + " a name");
				curType = type;
			}
		};
	}

	/**
	 * when remove button is clicked, remove the associated crew member from the
	 * temp list
	 * 
	 * @param start Index after index of associated crew member to start shuffling
	 *              down
	 * @return ActionListener for remove button
	 */
	private ActionListener removeButtonsActionListener(int start) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCrewMember(start);
			};
		};
	}

	/**
	 * show an initially empty spot for each potential member of the crew
	 */
	public void renderCrewSpots() {
		for (int i = 0; i < crewSize; i++) {

			// name
			JLabel lblMemberSpot = new JLabel("Empty");
			lblMemberSpot.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			lblMemberSpot.setForeground(new Color(255, 255, 255));
			memberSpotLabels[i] = lblMemberSpot;
			lblMemberSpot.setText("Empty");
			lblMemberSpot.setBounds(70 + i * 112, 56, 98, 17);
			frame.getContentPane().add(lblMemberSpot);

			// panel behind name
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(67 + i * 112, 56, 95, 17);
			panel_1.setBackground(new Color(40, 40, 40, 200));
			frame.getContentPane().add(panel_1);

			// empty label later filled with crew member's icon
			JLabel memberSpot = new JLabel("");
			memberSpot.setBounds(66 + i * 112, 48, 100, 110);
			memberSpots[i] = memberSpot;
			frame.getContentPane().add(memberSpot);

			// blank square to be covered with memberSpot when crew member gets added
			JLabel crewSpot = new JLabel("");
			crewSpot.setIcon(new ImageIcon(PickCrewMembersWindow.class.getResource("/images/image_box1.png")));
			crewSpot.setBounds(66 + i * 112, 48, 100, 110);
			frame.getContentPane().add(crewSpot);

			// remove button shown when crew member is visible in this spot
			JButton btnRemove = new JButton("Remove");
			removeSpots[i] = btnRemove;
			btnRemove.addActionListener(removeButtonsActionListener(i + 1));
			btnRemove.setBounds(82 + i * 112, 158, 71, 16);
			btnRemove.setVisible(false);
			frame.getContentPane().add(btnRemove);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// make frame
		frame = new JFrame();
		frame.setBounds(100, 100, 928, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Enter name panel

		EnterNamePanel enterNamePanel = new EnterNamePanel(this, frame, namesBlackList); // on top

		// render 'Create your crew' panel

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(700, 16, 209, 34);
		frame.getContentPane().add(panel);

		JLabel lblCreateYourCrew = new JLabel("Create your crew");
		panel.add(lblCreateYourCrew);
		lblCreateYourCrew.setFont(new Font("Krungthep", Font.PLAIN, 21));
		lblCreateYourCrew.setForeground(Color.WHITE);

		// title of crew member types

		JLabel lblSpotsRemaining = new JLabel("Choose wisely...");
		lblSpotsRemaining.setFont(new Font("Krungthep", Font.PLAIN, 21));
		lblSpotsRemaining.setForeground(new Color(204, 255, 255));
		lblSpotsRemaining.setBounds(358, 194, 200, 34);
		frame.getContentPane().add(lblSpotsRemaining);

		// title of current crew

		JLabel lblYourCrew = new JLabel(crew.getName() + "'s members:");
		lblYourCrew.setFont(new Font("Krungthep", Font.PLAIN, 13));
		lblYourCrew.setForeground(new Color(204, 255, 255));
		lblYourCrew.setBounds(66, 34, 300, 16);
		frame.getContentPane().add(lblYourCrew);

		// render empty temp crew spots

		renderCrewSpots();

		// 'pick' buttons

		ImageIcon pickButtonImage = new ImageIcon(PickCrewMembersWindow.class.getResource("/images/pick_button2.png"));

		for (int i = 0; i < 6; i++) {
			JButton pick = new JButton("");
			pickButtons[i] = pick;
			pick.addActionListener(pickButtonsActionListener(enterNamePanel, types[i]));
			pick.setIcon(pickButtonImage);
			pick.setBounds(114 + i * 118, 505, 90, 24);
			frame.getContentPane().add(pick);

			JLabel lblMaxReached = new JLabel("max reached");
			lblMaxReached.setVisible(false);
			lblMaxReached.setFont(new Font("Krungthep", Font.PLAIN, 12));
			maxReachedLabels[i] = lblMaxReached;
			lblMaxReached.setForeground(new Color(255, 0, 51));
			lblMaxReached.setBounds(114 + i * 118, 510, 90, 16);
			frame.getContentPane().add(lblMaxReached);

		}

		// button letting us know player's crew is ready

		btnReady = new JButton("Ready!");
		btnReady.setEnabled(false); // enabled when enough crew members are picked
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// check if crew has enough members
				if (curCrewSize < crewSize) {
					return;
				}

				// add all members from temp crew to the real crew
				for (CrewMember cm : tempCrew) {
					crew.addCrewMember(cm, cm.getAbilityIdentifier());
				}

				// done with this window
				finishedWindow();
			}
		});
		btnReady.setBounds(301 + (crewSize - 2) * 112, 93, 117, 29);
		frame.getContentPane().add(btnReady);

		// background image that is the member container
		JLabel memberContainer = new JLabel("");
		memberContainer.setIcon(
				new ImageIcon(PickCrewMembersWindow.class.getResource("/images_sp/member_container_800x367.png")));
		memberContainer.setBounds(54, 127, 855, 465);
		frame.getContentPane().add(memberContainer);

		// load everything else before this background image of space
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(DaysNameSizeWindow.class.getResource("/images/space1.png")));
		lblBackground.setBounds(0, 0, 928, 560);
		frame.setResizable(false);
		frame.getContentPane().add(lblBackground);
	}
}
