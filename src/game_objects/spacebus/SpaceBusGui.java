package game_objects.spacebus;

import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game_objects.StatusBar;
import game_objects.actions.Action;
import game_objects.actions.ActionSet;
import game_objects.crew_member.CrewMember;
import main.GUIGame.DayWindow;
import main.GUIGame.StoryLine;

public class SpaceBusGui {

	private PiecesLeft piecesGui;
	private JLabel pilotsReady;
	private JLabel busName;
	private JLabel actionLbl;
	private int numPilots = 0;
	private JLabel bus = new JLabel("");
	StatusBar shieldHealthBar = new StatusBar(new Color(216, 248, 255), new Color(0, 212, 255), 100, "Shield Health");

	public SpaceBusGui(int pieces, String name) {

		busName = new JLabel(name);
		busName.setFont(new Font("Krungthep", Font.PLAIN, 16));
		busName.setBounds(1035, 405, 200, 30);
		busName.setForeground(new Color(255, 238, 0));

		pilotsReady = new JLabel("Pilots ready!");
		pilotsReady.setForeground(new Color(255, 178, 0));
		pilotsReady.setBounds(1060, 440, 110, 15);
		pilotsReady.setVisible(false);
		actionLbl = new JLabel("Pilot bus (0/2)");
		actionLbl.setBounds(1050, 440, 110, 15);
		Action pilotShip = new Action(actionLbl) {

			@Override
			public void performAction(CrewMember cm) {
				super.performAction(cm);
				StoryLine.updateLabel(cm.getName() + " has been chosen to pilot the space-bus");
				actionLbl.setText("Pilot ship (" + ++numPilots + "/2)");
				DayWindow.pilots.add(cm);
				if (numPilots == 2) {
					pilotsReady.setVisible(true);
				}
			}

			@Override
			public boolean shouldShowAction(HashSet<Integer> blackList) {
				return !blackList.contains(0);
			}
		};
		ActionSet.getInstance().addAction(pilotShip);
		piecesGui = new PiecesLeft(pieces);
		bus.setIcon(new ImageIcon(SpaceBusGui.class.getResource("/images_sp/but_front_160x162.png")));
	}

	public void resetAction() {
		pilotsReady.setVisible(false);
		numPilots = 0;
		actionLbl.setText("Pilot bus (0/2)");
	}

	public void adjustShieldHealth(int newHealth) {
		shieldHealthBar.adjustValue(newHealth);
	}

	public void findPiece() {
		piecesGui.findPiece();
	}

	public void render(JFrame frame) {
		frame.getContentPane().add(busName);
		frame.getContentPane().add(pilotsReady);
		frame.getContentPane().add(actionLbl);
		bus.setBounds(frame.getWidth() - 180, frame.getHeight() - 340, 160, 162);
		shieldHealthBar.render(frame, frame.getWidth() - 150, frame.getHeight() - 170);
		frame.getContentPane().add(bus);
		piecesGui.render(frame);
	}

}
