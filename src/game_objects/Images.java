package game_objects;

import javax.swing.ImageIcon;

import main.GUIPregame.PickCrewMembersWindow;

/**
 * This class contains images of character boxes and full characters themselves
 */
public class Images {

	// square images

	public static final ImageIcon pioneerSquare = new ImageIcon(
			PickCrewMembersWindow.class.getResource("/images_sp/image_box_stan_95x95.png"));
	public static final ImageIcon medicSquare = new ImageIcon(
			PickCrewMembersWindow.class.getResource("/images_sp/image_box_dr_96x95.png"));
	public static final ImageIcon pilotSquare = new ImageIcon(
			PickCrewMembersWindow.class.getResource("/images_sp/image_box_wendy_97x97.png"));
	public static final ImageIcon juggernautSquare = new ImageIcon(
			PickCrewMembersWindow.class.getResource("/images_sp/image_box_cartman_100x93.png"));
	public static final ImageIcon chefSquare = new ImageIcon(
			PickCrewMembersWindow.class.getResource("/images_sp/image_box_chef_100x97.png"));
	public static final ImageIcon barterSquare = new ImageIcon(
			PickCrewMembersWindow.class.getResource("/images_sp/image_box_kyle_100x99.png"));

	// full character images

	public static final CharacterImage juggernautCharacter = new CharacterImage("/images_sp/cartman_90x82.png", 90, 82);

	public static final CharacterImage chefCharacter = new CharacterImage("/images_sp/chef_93x120.png", 93, 120);

	public static final CharacterImage pioneerCharacter = new CharacterImage("/images_sp/stan_64x100.png", 64, 100);

	public static final CharacterImage barterCharacter = new CharacterImage("/images_sp/kyle_84x100.png", 84, 100);

	public static final CharacterImage pilotCharacter = new CharacterImage("/images_sp/wendy_70x100.png", 70, 100);

	public static final CharacterImage medicCharacter = new CharacterImage("/images_sp/dr_55x120.png", 55, 120);

}
