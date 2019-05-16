package game_objects.crew_member;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game_objects.Crew;

public class CharacterPane {

	/**
	 * Constructor for CharacterPane
	 * 
	 * @param crew  Crew chosen by player
	 * @param frame Currently visible frame
	 * @param x     point on x-axis to render each character box
	 */
	public CharacterPane(Crew crew, JFrame frame, int x) {

		// crew name
		JLabel crewLabel = new JLabel(crew.getName());
		crewLabel.setFont(new Font("Krungthep", Font.PLAIN, 16));
		crewLabel.setBounds(4, 0, 200, 30);
		crewLabel.setForeground(new Color(255, 238, 0));
		frame.getContentPane().add(crewLabel);

		// render each character same distance apart
		int crewSize = crew.getCrewMembers().size();
		int space = (frame.getHeight() - 110 * crewSize) / (crewSize + 1);

		Iterator<CrewMember> iter = crew.getCrewMembers().keySet().iterator();

		for (int count = 0; iter.hasNext();) { // render each character box
			iter.next().getGuiObject().render(frame, x, space + count++ * (110 + space) - 15);
		}

		// panel behind characters
		JPanel panelCharacterPane = new JPanel();
		panelCharacterPane.setBackground(new Color(160, 96, 255, 100));
		panelCharacterPane.setBounds(0, 0, 134, frame.getHeight());
		frame.getContentPane().add(panelCharacterPane);
	}
}
