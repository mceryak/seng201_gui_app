package medical_items;

import javax.swing.ImageIcon;

import game_objects.MedicalItem;

/**
 * This class is for creation of MedPack_Small MedicalItem
 */
public class MedPack_Small extends MedicalItem {

	/**
	 * create new small medpack
	 */
	public MedPack_Small() {
		super("Small MedPack", 10, 15);
	}

	public boolean curesSpacePlague() {
		return false;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(MedPack_Small.class.getResource("/images_sp/smallmedpack_54x54.png"));
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 15;
	}

}
