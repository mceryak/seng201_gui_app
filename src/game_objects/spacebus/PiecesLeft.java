package game_objects.spacebus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PiecesLeft {

	private JLabel[] missingPieces;
	private JLabel label;
	private int piecesLeft;

	public PiecesLeft(int pieces) {
		piecesLeft = pieces;
		label = new JLabel("Missing Pieces");
		label.setFont(new Font("Krungthep", Font.PLAIN, 16));
		label.setForeground(new Color(255, 255, 255));
		missingPieces = new JLabel[pieces];
		for (int i = 0; i < pieces; i++) {
			missingPieces[i] = new JLabel("");
		}
	}

	public void findPiece() {

		missingPieces[0].setIcon(null);
		piecesLeft--;
	}

	public void render(JFrame frame) {
		System.out.println("pieces left: " + piecesLeft);
		int startX = frame.getWidth() - 180 - 45 * piecesLeft;
		for (int i = 0; i < piecesLeft; i++) {
			JLabel piece = missingPieces[i];
			piece.setIcon(new ImageIcon(PiecesLeft.class.getResource("/images_sp/missingpiece_35x32.png")));
			piece.setBounds(startX + i * 45, frame.getHeight() - 85, 35, 32);
			frame.getContentPane().add(piece);
		}
		label.setBounds(frame.getWidth() - 180 - 45 * 3, frame.getHeight() - 120, 180, 30);
		frame.getContentPane().add(label);
	}

}
