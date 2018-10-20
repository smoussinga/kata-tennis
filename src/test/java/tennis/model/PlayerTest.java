package test.java.tennis.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.tennis.model.Player;

import static main.java.tennis.app.Constants.PLAYER_NAME;

/**
 * @author smoussinga
 *
 */
public class PlayerTest {

	Player player;

	@Before
	public void beforeTest() {
		player = new Player(PLAYER_NAME);
	}

	@Test
	public void startPlayerTest() {
		assertEquals(PLAYER_NAME, player.getName());
		assertEquals(0, player.getScore());
		assertEquals("0", player.displayScore());
	}

	@Test
	public void displayGameScoreTest() {
		assertEquals("0", player.displayScore());
		player.winOnePoint();
		assertEquals("15", player.displayScore());
		player.winOnePoint();
		assertEquals("30", player.displayScore());
		player.winOnePoint();
		assertEquals("40", player.displayScore());
	}

	@Test
	public void scoreGamePointsTest() {
		assertEquals(0, player.getScore());
		assertEquals("0", player.displayScore());
		for (int i = 0; i <= 2; i++) {
			player.winOnePoint();
		}
		assertEquals(3, player.getScore());
		assertEquals("40", player.displayScore());
	}

	@Test
	public void resetGamePointTest() {
		for (int i = 0; i <= 2; i++) {
			player.winOnePoint();
		}
		assertEquals(3, player.getScore());
		assertEquals("40", player.displayScore());
		player.resetGamePoint();
		assertEquals(0, player.getScore());
		assertEquals("0", player.displayScore());
	}

}
