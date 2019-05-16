package game_objects.crew_member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_objects.CharacterImage;
import game_objects.actions.ActionSet;
import main.GUIGame.StoryLine;

/**
 * this class is for the character image visible on the planet. Includes image,
 * name of crew member, and actions left
 */
public class Character {

	// character image associated with crew member
	private JLabel lblCharacter;

	// crew member name
	private JLabel lblName;

	// number of actions crew member has
	private JLabel actions;

	// panel behind name and actions
	private JPanel pnlNameLabel;

	// crew member associated with the character
	private CrewMember cm;

	// width of character image
	private int w;

	// height of character image
	private int h;

	/**
	 * Constructor for Character
	 * 
	 * @param cm           Crew Member associated with this character
	 * @param characterImg image used for character label
	 */
	public Character(CrewMember cm, CharacterImage characterImg) {

		w = characterImg.getWidth();
		h = characterImg.getHeight();
		this.cm = cm;
		lblCharacter = new JLabel("");
		lblCharacter.addMouseListener(addMouseAdapter());
		actions = new JLabel("");
		lblCharacter.setIcon(characterImg.getIcon());
		lblName = new JLabel(cm.getName());
		pnlNameLabel = new JPanel();
	}

	/**
	 * @return height of character
	 */
	public int getHeight() {
		return h;
	}

	/**
	 * @return width of character
	 */
	public int getWidth() {
		return w;
	}

	/**
	 * reduce actions by 1
	 */
	public void performAction() {
		actions.setText("Actions: " + cm.getActionsLeft());
	}

	/**
	 * @return the label containing characer image
	 */
	public JLabel getLblCharacter() {
		return this.lblCharacter;
	}

	/**
	 * Render this character on the currently visible frame
	 * 
	 * @param frame Visible frame
	 * @param x     location on x-axis
	 * @param y     location on y-axis
	 */
	public void render(JFrame frame, int x, int y) {

		lblCharacter.setBounds(x, y, w, h);
		frame.getContentPane().add(lblCharacter);

		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(x, y + h, 98, 17);
		lblName.setVisible(false);
		frame.getContentPane().add(lblName);

		actions.setText("Actions: " + cm.getActionsLeft());
		actions.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		actions.setForeground(new Color(0, 255, 48));
		actions.setBounds(x, y + h + 18, 98, 17);
		actions.setVisible(false);
		frame.getContentPane().add(actions);

		pnlNameLabel.setBounds(x - 1, y + h, 99, 35);
		pnlNameLabel.setBackground(new Color(40, 40, 40, 200));
		pnlNameLabel.setVisible(false);
		frame.getContentPane().add(pnlNameLabel);
	}

	public MouseAdapter addMouseAdapter() {
		return new MouseAdapter() {
			/**
			 * show crew member name and actions left
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlNameLabel.setVisible(true);
				lblName.setVisible(true);
				actions.setVisible(true);
			}

			/**
			 * hide crew member name and actions left
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				pnlNameLabel.setVisible(false);
				lblName.setVisible(false);
				actions.setVisible(false);
			}

			/**
			 * display actions available for this character
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				ActionSet actionSet = ActionSet.getInstance();
				if (cm.getActionsLeft() > 0) { // can perform an action
					if (actionSet.getPerformer() == cm) { // this character was previously selected, so de-select
						StoryLine.updateLabel(cm.getName() + " has been de-selected.");
						actionSet.removePerformer();
					} else { // select this character
						StoryLine.updateLabel(cm.getName() + " has been selected.");
						actionSet.setPerformer(cm);
					}
				} else { // cannot perform action
					actionSet.removePerformer(); // if other character was selected, de-select them
				}
			}
		};
	}
}
