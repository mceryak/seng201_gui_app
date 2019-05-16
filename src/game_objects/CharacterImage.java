package game_objects;

import javax.swing.ImageIcon;

import main.GUIGame.DayWindow;

/**
 * This class is used to store characters with their dimensions
 */
public class CharacterImage {

	// image of character
	private ImageIcon icon;

	// width of character
	private int width;

	// height of character
	private int height;

	/**
	 * Constructor for CharacterImage
	 * 
	 * @param path String file location
	 * @param w    int width
	 * @param h    int height
	 */
	public CharacterImage(String path, int w, int h) {
		this.icon = new ImageIcon(DayWindow.class.getResource(path));
		this.width = w;
		this.height = h;
	}

	/**
	 * get image icon
	 * 
	 * @return ImageIcon associated with this character
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * get width
	 * 
	 * @return int width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * get height
	 * 
	 * @return int height
	 */
	public int getHeight() {
		return height;
	}

}
