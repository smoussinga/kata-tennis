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

	/**
	 * Test the displayed message of the player at the start of the game
	 */
	@Test
	public void startPlayerTest() {
		assertEquals(PLAYER_NAME, player.getName());
		assertEquals(0, player.getScore());
		assertEquals("0", player.displayScorePoints());
	}

	/**
	 * Test the simple rule points scores to display
	 */
	@Test
	public void displayGameScoreTest() {
		assertEquals("0", player.displayScorePoints());
		player.winOnePoint();
		assertEquals("15", player.displayScorePoints());
		player.winOnePoint();
		assertEquals("30", player.displayScorePoints());
		player.winOnePoint();
		assertEquals("40", player.displayScorePoints());
	}

	/**
	 * Test the game(set) scores to display
	 */
	@Test
	public void displayGameSetScoreTest() {
		assertEquals("0", player.displayScoreSets());
		player.winOneSet();
		assertEquals("1", player.displayScoreSets());
		player.winOneSet();
		assertEquals("2", player.displayScoreSets());
		player.winOneSet();
		assertEquals("3", player.displayScoreSets());
		player.winOneSet();
		assertEquals("4", player.displayScoreSets());
		player.winOneSet();
		assertEquals("5", player.displayScoreSets());
		player.winOneSet();
		assertEquals("6", player.displayScoreSets());
		player.winOneSet();
		assertEquals("7", player.displayScoreSets());
	}

	/**
	 * Test winOnePoint (win 1 Point) method
	 */
	@Test
	public void scoreGamePointsTest() {
		assertEquals(0, player.getScore());
		assertEquals("0", player.displayScorePoints());
		for (int i = 0; i <= 2; i++) {
			player.winOnePoint();
		}
		assertEquals(3, player.getScore());
		assertEquals("40", player.displayScorePoints());
	}

	/**
	 * Test the reset of points score
	 */
	@Test
	public void resetGamePointTest() {
		for (int i = 0; i <= 2; i++) {
			player.winOnePoint();
		}
		assertEquals(3, player.getScore());
		assertEquals("40", player.displayScorePoints());
		player.resetGamePoint();
		assertEquals(0, player.getScore());
		assertEquals("0", player.displayScorePoints());
	}

	/**
	 * Test winOneSet (win 1 Game) method
	 */
	@Test
	public void scoreGameSetsTest() {
		assertEquals(0, player.getSet());
		assertEquals("0", player.displayScoreSets());
		for (int i = 0; i <= 5; i++) {
			player.winOneSet();
		}
		assertEquals(6, player.getSet());
		assertEquals("6", player.displayScoreSets());
	}

	/**
	 * Test winOneTieBreak (win 1 Tie-Break) method
	 */
	@Test
	public void scoreGameTieBreaksTest() {
		assertEquals(0, player.getTieBreak());
		for (int i = 0; i <= 8; i++) {
			player.winOneTieBreak();
		}
		assertEquals(9, player.getTieBreak());
	}
}
