package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game_objects.spacebus.SpaceBus;

class SpaceBusTest {
	
	private SpaceBus spacebus;

	@BeforeEach
	void setUp() throws Exception {
		spacebus = new SpaceBus("The Bus", 3);
		
	}

	@Test
	void getterstest() {
		assertEquals(spacebus.getPiecesToFind(), 3);
		assertEquals(spacebus.getShieldHealth(), 100);
		assertEquals(spacebus.getName(), "The Bus");
	}
	
	@Test
	void damageTest() {
		assertEquals(spacebus.getShieldHealth(), 100);
		assertEquals(spacebus.takeDamage(50), 50);
		assertEquals(spacebus.getShieldHealth(), 50);
		spacebus.repairShield();
		assertEquals(spacebus.getShieldHealth(), 80);
		spacebus.repairShield();
		assertEquals(spacebus.getShieldHealth(), 100);
		assertEquals(spacebus.takeDamage(1000), 100);
		assertEquals(spacebus.getShieldHealth(), 0);
		assertEquals(spacebus.takeDamage(1000), 0);
		assertEquals(spacebus.getShieldHealth(), 0);
	}
	
	@Test
	void pieceTest() {
		assertEquals(spacebus.getPiecesToFind(), 3);
		spacebus.findPiece();
		assertEquals(spacebus.getPiecesToFind(), 2);
	}

}
