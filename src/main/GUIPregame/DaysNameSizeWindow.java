package main.GUIPregame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.GUIManager;

/**
 * This class is called right after start window. Lets player pick crew size and
 * days in space
 */
public class DaysNameSizeWindow {

	// visible frame
	private JFrame frame;

	// gui controller
	private GUIManager man;

	// player gives crew name
	private JTextField crewNameTextField;

	// player gives spacebus name
	private JTextField spacebusNameTextField;

	// number of days player decides to play for
	private int numDays;

	// crew size player decides on
	private int crewSize;

	// name of crew from text field
	private String crewName;

	// name of spacebus from text field
	private String spacebusName;

	/**
	 * Constructor for DaysNameSizeWindow
	 * 
	 * @param manager gui controller
	 */
	public DaysNameSizeWindow(GUIManager manager) {
		man = manager;
		initialize();
		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return number of days player decides on
	 */
	public int getDays() {
		return numDays;
	}

	/**
	 * @return crew size player decides on
	 */
	public int getCrewSize() {
		return crewSize;
	}

	/**
	 * @return spacebus name player decides on
	 */
	public String getSpacebusName() {
		return spacebusName;
	}

	/**
	 * @return crew name player decides on
	 */
	public String getCrewName() {
		return crewName;
	}

	/**
	 * dispose frame
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * close this window and load the next
	 */
	public void finishedWindow() {
		man.closeDaysNameSizeWindow(this);
	}

	private void setRadioButtons(ButtonGroup numDaysSelector, int start, int end, int x, int y) {
		for (int i = start; i <= end; i++) {
			JRadioButton radioButton = new JRadioButton("" + i);
			radioButton.setForeground(Color.WHITE);
			radioButton.setBounds(x, y, 141, 16);
			numDaysSelector.add(radioButton);
			frame.getContentPane().add(radioButton);
			y += 22;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 548, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// enter crew name

		JLabel lblCrewName = new JLabel("Crew name...");
		lblCrewName.setFont(new Font("Krungthep", Font.PLAIN, 13));
		lblCrewName.setForeground(Color.WHITE);
		lblCrewName.setBounds(203, 101, 128, 16);
		frame.getContentPane().add(lblCrewName);

		crewNameTextField = new JTextField();
		crewNameTextField.setBounds(201, 118, 130, 26);
		frame.getContentPane().add(crewNameTextField);
		crewNameTextField.setColumns(10);

		// choose days lost in space

		JLabel lblDaysLostIn = new JLabel("Days lost in space...");
		lblDaysLostIn.setFont(new Font("Krungthep", Font.PLAIN, 13));
		lblDaysLostIn.setForeground(Color.WHITE);
		lblDaysLostIn.setBounds(34, 101, 146, 16);
		frame.getContentPane().add(lblDaysLostIn);

		ButtonGroup numDaysSelector = new ButtonGroup();
		setRadioButtons(numDaysSelector, 3, 10, 34, 129);

		// create your crew panel/label

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(323, 17, 209, 34);
		frame.getContentPane().add(panel);

		JLabel lblCreateYourCrew = new JLabel("Create your crew");
		panel.add(lblCreateYourCrew);
		lblCreateYourCrew.setFont(new Font("Krungthep", Font.PLAIN, 21));
		lblCreateYourCrew.setForeground(Color.WHITE);

		// choose crew size

		JLabel lblCrewSize = new JLabel("Crew size...");
		lblCrewSize.setFont(new Font("Krungthep", Font.PLAIN, 13));
		lblCrewSize.setForeground(Color.WHITE);
		lblCrewSize.setBounds(203, 215, 101, 16);
		frame.getContentPane().add(lblCrewSize);

		ButtonGroup crewSizeSelector = new ButtonGroup();
		setRadioButtons(crewSizeSelector, 2, 4, 203, 237);

		// choose spacebus name

		JLabel label_1 = new JLabel("Spacebus name...");
		label_1.setFont(new Font("Krungthep", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(203, 157, 128, 16);
		frame.getContentPane().add(label_1);

		spacebusNameTextField = new JTextField();
		spacebusNameTextField.setColumns(10);
		spacebusNameTextField.setBounds(201, 173, 130, 26);
		frame.getContentPane().add(spacebusNameTextField);

		// error labels if something is not chosen

		JLabel lblCrewNameError = new JLabel("");
		lblCrewNameError.setFont(new Font("Krungthep", Font.PLAIN, 9));
		lblCrewNameError.setForeground(Color.RED);
		lblCrewNameError.setBounds(202, 138, 190, 16);
		frame.getContentPane().add(lblCrewNameError);

		JLabel lblShipNameError = new JLabel("");
		lblShipNameError.setFont(new Font("Krungthep", Font.PLAIN, 9));
		lblShipNameError.setForeground(Color.RED);
		lblShipNameError.setBounds(203, 193, 189, 16);
		frame.getContentPane().add(lblShipNameError);

		JLabel lblDaysError = new JLabel("");
		lblDaysError.setForeground(Color.RED);
		lblDaysError.setFont(new Font("Krungthep", Font.PLAIN, 9));
		lblDaysError.setBounds(34, 293, 155, 16);
		frame.getContentPane().add(lblDaysError);

		JLabel lblCrewSizeError = new JLabel("");
		lblCrewSizeError.setForeground(Color.RED);
		lblCrewSizeError.setFont(new Font("Krungthep", Font.PLAIN, 9));
		lblCrewSizeError.setBounds(203, 297, 165, 16);
		frame.getContentPane().add(lblCrewSizeError);

		// next button

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spacebusName = spacebusNameTextField.getText();
				if (spacebusName.length() == 0) { // didn't enter spacebus name
					lblShipNameError.setText("Think of something... please");
				} else { // no error here
					lblShipNameError.setText("");
				}
				crewName = crewNameTextField.getText();
				if (crewName.length() == 0) { // didn't enter crew name
					lblCrewNameError.setText("Think of something... please");
				} else { // no error here
					lblCrewNameError.setText("");
				}

				// get number of days and crew size from radio button groups

				Enumeration<AbstractButton> daysButtons = numDaysSelector.getElements();
				Enumeration<AbstractButton> crewSizeButtons = crewSizeSelector.getElements();
				while (daysButtons.hasMoreElements()) {
					AbstractButton button = daysButtons.nextElement();
					if (button.isSelected()) {
						numDays = Integer.parseInt(button.getText());
					}
				}
				if (numDays == 0) { // didn't choose number of days
					lblDaysError.setText("You forgot something");
				} else { // no error here
					lblDaysError.setText("");
				}
				while (crewSizeButtons.hasMoreElements()) {
					AbstractButton button = crewSizeButtons.nextElement();
					if (button.isSelected()) {
						crewSize = Integer.parseInt(button.getText());
					}
				}
				if (crewSize == 0) { // didn't choose crew size
					lblCrewSizeError.setText("You forgot something");
				} else { // no error here
					lblCrewSizeError.setText("");
				}

				if (numDays == 0 || crewSize == 0 || spacebusName.length() == 0 || crewName.length() == 0) {
					// something wasn't filled in
					return;
				}

				// done with this window
				finishedWindow();
			}
		});

		btnNext.setForeground(Color.BLACK);
		btnNext.setBounds(402, 272, 117, 29);
		frame.getContentPane().add(btnNext);

		// load everything else before this background image
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(DaysNameSizeWindow.class.getResource("/images/space2.jpg")));
		label.setBounds(0, 0, 548, 348);
		frame.getContentPane().add(label);
	}
}
