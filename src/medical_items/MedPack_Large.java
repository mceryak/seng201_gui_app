package medical_items;

import javax.swing.ImageIcon;

import game_objects.MedicalItem;

/**
 * This class is for creation of MedPack_Large MedicalItem
 */
public class MedPack_Large extends MedicalItem {

	/**
	 * create new large medpack
	 */
	public MedPack_Large() {
		super("Large MedPack", 20, 40);
	}

	@Override
	public boolean curesSpacePlague() {
		return false;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(MedPack_Large.class.getResource("/images_sp/largemedpack_54x54.png"));
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 40;
	}

}
