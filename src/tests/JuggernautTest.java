package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crew_member_types.Juggernaut;

class JuggernautTest {

	Juggernaut jug;

	@BeforeEach
	void setUp() throws Exception {
		jug = new Juggernaut("Jeff");
	}

	@Test
	void testConstructor() {
		assertEquals(120, jug.getHealth());
		assertEquals("Juggernaut", jug.getType());
	}

	@Test
	void testPlagued() {
		int before = jug.getHealth();
		assertFalse(jug.hasPlague());
		jug.plagued();
		assertTrue(jug.hasPlague());
		assertEquals(before - 8, jug.getHealth());
	}

	@Test
	void testNewday() {
		jug.callNewDay();
		assertEquals(115, jug.getHealth());
		assertEquals(15, jug.getHunger());
		assertEquals(15, jug.getTiredness());
		jug.callNewDay();
		assertEquals(105, jug.getHealth());
		assertEquals(30, jug.getHunger());
		assertEquals(30, jug.getTiredness());
		jug.callNewDay();
		assertEquals(90, jug.getHealth());
		assertEquals(45, jug.getHunger());
		assertEquals(45, jug.getTiredness());
		jug.callNewDay();
		jug.callNewDay();
		jug.callNewDay();
		jug.callNewDay();
		assertEquals(0, jug.getHealth());
		assertEquals(100, jug.getHunger());
		assertEquals(100, jug.getTiredness());
		assertFalse(jug.isAlive());
		jug.callNewDay();
		assertEquals(0, jug.getHealth());
		assertEquals(100, jug.getHunger());
		assertEquals(100, jug.getTiredness());
		assertFalse(jug.isAlive());
	}

}
