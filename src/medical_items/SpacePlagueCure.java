package medical_items;

import javax.swing.ImageIcon;

import game_objects.MedicalItem;

/**
 * This class is for creation of SpacePlagueCure MedicalItem
 */
public class SpacePlagueCure extends MedicalItem {

	/**
	 * create new space plague cure
	 */
	public SpacePlagueCure() {
		super("Space Plague Cure", 15, 5);
	}

	public boolean curesSpacePlague() {
		return true;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(SpacePlagueCure.class.getResource("/images_sp/plaguecure_54x54.png"));
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 5;
	}

}
