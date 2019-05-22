package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Medic;
import crew_member_types.Pilot;
import game_objects.Crew;
import game_objects.actions.Action;
import game_objects.actions.ActionSet;
import game_objects.actions.SearchPlanet;
import game_objects.actions.Sleep;
import game_objects.actions.UseItem;
import game_objects.crew_member.CrewMember;
import main.GUIGame.DayWindow;

class ActionSetTest {

	Crew crew;
	ActionSet actionList;
	Action[] actions;

	@BeforeEach
	void setUp() throws Exception {

		crew = new Crew("CrewName", "ShipName", 6);

		actionList = ActionSet.getInstance();
		Action sleep = new Sleep();
		Action search = new SearchPlanet(crew);
		Action useItem = new UseItem(crew.getInventory(),
				crew.getAbilityIdentifiers().contains(Medic.abilityIdentifier));

		actions = new Action[] { sleep, search, useItem };

		actionList.addAction(sleep);
		actionList.addAction(search);
		actionList.addAction(useItem);
	}

	private int getInventorySize() {
		int ret = 0;
		for (int i : crew.getInventory().getFoodItems().values()) {
			ret += i;
		}
		for (int i : crew.getInventory().getMedicalItems().values()) {
			ret += i;
		}
		return ret;
	}

	@Test
	void testPerformer() {
		DayWindow.pilots = new HashSet<>();
		assertNull(actionList.getPerformer());
		CrewMember wendy = new Pilot("Wendy");
		actionList.setPerformer(wendy);
		assertNotNull(actionList.getPerformer());
		assertTrue(actionList.getPerformer() == wendy);
		actionList.removePerformer();
		assertNull(actionList.getPerformer());
		actionList.setPerformer(wendy);
		actions[0].performAction(wendy);
		assertNull(actionList.getPerformer());
		actionList.setPerformer(wendy);
		actions[1].performAction(wendy);
		assertNull(actionList.getPerformer());
	}

}
