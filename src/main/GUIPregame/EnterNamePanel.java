package main.GUIPregame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 * This class is called when player just clicked to pick a crew member
 */
public class EnterNamePanel {

	// pick crew members window panel is visible on
	private PickCrewMembersWindow pcmw;

	// visible frame
	private JFrame frame;

	// back opaque panel covering whole screen
	private JPanel NameBackPanel = new JPanel();

	// enter name text label
	private JLabel lblEnterAName = new JLabel("");

	/**
	 * Constructor for EnterNamePanel
	 * 
	 * @param pickCrewMembersWindow Pick crew members window this panel is visible
	 *                              on
	 * @param useThisFrame          Visible frame
	 * @param namesBlackList        Set of names that have already been used
	 */
	public EnterNamePanel(PickCrewMembersWindow pickCrewMembersWindow, JFrame useThisFrame,
			HashSet<String> namesBlackList) {
		pcmw = pickCrewMembersWindow;
		frame = useThisFrame;
		createPanel(namesBlackList);
	}

	/**
	 * Show/hide this EnterName panel
	 * 
	 * @param tf   True: show. False: hide
	 * @param text Set enter name text according to crew member type
	 */
	public void showPanel(boolean tf, String text) {
		lblEnterAName.setText(text); // type given in text
		NameBackPanel.setVisible(tf);
	}

	/**
	 * create the panel
	 * 
	 * @param namesBlackList Set of names not to be used
	 */
	private void createPanel(HashSet<String> namesBlackList) {

		// back opaque panel taking up whole screen
		NameBackPanel.setVisible(false);
		NameBackPanel.setBackground(new Color(0, 153, 153, 150));
		NameBackPanel.setBounds(0, 0, 928, 560);
		NameBackPanel.setLayout(null);
		frame.getContentPane().add(NameBackPanel);

		// border of name panel
		JPanel NameBorderPanel = new JPanel();
		NameBorderPanel.setBounds(335, 211, 230, 135);
		NameBackPanel.add(NameBorderPanel);
		NameBorderPanel.setBackground(new Color(0, 0, 51));
		NameBorderPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 51, 51)));
		NameBorderPanel.setLayout(null);

		// name container panel
		JPanel NameContainerPanel = new JPanel();
		NameContainerPanel.setBounds(6, 6, 218, 122);
		NameBorderPanel.add(NameContainerPanel);
		NameContainerPanel.setBackground(new Color(0, 102, 102));

		// enter a name text
		lblEnterAName.setFont(new Font("Krungthep", Font.PLAIN, 13));
		NameContainerPanel.add(lblEnterAName);

		// text field for player to enter a name
		JTextField EnterNameTextField = new JTextField();
		NameContainerPanel.add(EnterNameTextField);
		EnterNameTextField.setColumns(15);

		// error label if user does not enter name or enters an already used name
		JLabel lblNameError = new JLabel("                                                  "); // 50 spaces
		lblNameError.setForeground(new Color(204, 0, 51));
		lblNameError.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		NameContainerPanel.add(lblNameError);

		// back button
		JButton btnBackName = new JButton("BACK");
		btnBackName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcmw.changeStateButtons(true);
				NameBackPanel.setVisible(false);
				lblNameError.setText("                                                  ");
				EnterNameTextField.setText("");
			}
		});
		NameContainerPanel.add(btnBackName);

		// Add crew member button
		JButton btnAddName = new JButton("ADD");
		btnAddName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = EnterNameTextField.getText();

				if (name.length() == 0) { // don't add, set error label
					lblNameError.setText("How will we know what to call them?");
					return;
				}
				if (namesBlackList.contains(name)) { // don't add, set error label
					lblNameError.setText("You already used this name");
					return;
				}

				// enable pick buttons again
				pcmw.changeStateButtons(true);

				// reset text and error values
				EnterNameTextField.setText("");
				lblNameError.setText("                                                  ");

				// add the crew member to the temp list
				pcmw.addCrewMember(name);

				// hide this enter name panel
				NameBackPanel.setVisible(false);
			}
		});
		NameContainerPanel.add(btnAddName);
	}
}
