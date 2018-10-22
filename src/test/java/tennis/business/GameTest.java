package test.java.tennis.business;

import static org.junit.Assert.*;

import java.util.Formatter;

import org.junit.Before;
import org.junit.Test;

import main.java.tennis.business.Game;
import main.java.tennis.model.Player;

import static main.java.tennis.app.Constants.PLAYER_ONE;
import static main.java.tennis.app.Constants.PLAYER_TWO;
import static main.java.tennis.app.Constants.EMPTY_STRING;
import static main.java.tennis.app.Constants.GAME_START_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.POINT_SCORE_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.GAME_END_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.DEUCE;
import static main.java.tennis.app.Constants.ADVANTAGE;
import static main.java.tennis.app.Constants.MATCH_END_SCORE_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.TIE_BREAK_END_FORMATTER_PATTERN;

/**
 * @author smoussinga
 *
 */
public class GameTest {
	Game game;
	Player player1, player2;

	@Before
	public void beforeTest() {
		player1 = new Player(PLAYER_ONE);
		player2 = new Player(PLAYER_TWO);
		game = new Game(player1, player2);
	}

	/**
	 * Test the displayed message at the start of the game
	 */
	@Test
	public void displayStartScoreMessageTest() {
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_START_FORMATTER_PATTERN, PLAYER_ONE, "0", PLAYER_TWO, "0", PLAYER_ONE, "0", PLAYER_TWO,
					"0");
		}
		assertEquals(sb.toString(), game.displayScoreMainMessage());
	}

	/**
	 * Test the displayed message of the points score at the first Game (set)
	 */
	@Test
	public void displayGameScoreMessageTest() {
		// simulate the first game scores
		for (int i = 0; i <= 1; i++) {
			player1.winOnePoint();
		}
		player2.winOnePoint();
		game.setPlayerWhoWinOnePoint(2);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "30", PLAYER_TWO,
					"15");
		}
		assertEquals(sb.toString(), game.displayScoreMainMessage());
	}

	/**
	 * Test the displayed message when a player win 1 Game (set)
	 */
	@Test
	public void displayGameWinnerMessageTest() {
		// simulate the first game scores
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		game.setPlayerWhoWinOnePoint(2);
		// End of the first game, Game scores Player 1 : 0 - Player 2 : 0
		// (Set scores Player 1 : 0 - Player 2 : 1)
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO, "0",
					PLAYER_ONE, "0", PLAYER_TWO, "1");
		}
		assertEquals(sb.toString(), game.displayScoreMainMessage());
	}

	/**
	 * Test the reset of the points score after a player win 1 Game (set)
	 */
	@Test
	public void resetGameAfterWinnerTest() {
		// simulate the first game scores
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		// End of the first game, Game scores Player 1 : 0 - Player 2 : 0
		// (Set scores Player 1 : 0 - Player 2 : 1)
		game.displayScoreMainMessage();
		// reset of the points score
		assertEquals(0, game.getPlayer1().getScore());
		assertEquals(0, game.getPlayer2().getScore());
	}

	/**
	 * Test the displayed message when DEUCE rule is activated (first Game
	 * displayed)
	 */
	@Test
	public void displayGameScoreWithDeuceMessageTest() {
		// simulate the first game scores
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
			player1.winOnePoint();
		}
		game.setPlayerWhoWinOnePoint(1);
		// Game scores Player 1 : DEUCE - Player 2 : DEUCE
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, DEUCE, PLAYER_TWO,
					DEUCE);
		}
		assertEquals(sb.toString(), game.displayScoreMainMessage());
	}

	/**
	 * Test the displayed message when DEUCE rule is activated and a player have the
	 * ADVANTAGE (first Game displayed)
	 */
	@Test
	public void displayGameScoreWithAdvantageMessageTest() {
		// simulate the first game scores
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
			player1.winOnePoint();
		}
		player2.winOnePoint();
		game.setPlayerWhoWinOnePoint(2);
		// Game scores Player 1 : 40 - Player 2 : ADV
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "40", PLAYER_TWO,
					ADVANTAGE);
		}
		assertEquals(sb.toString(), game.displayScoreMainMessage());
	}

	/**
	 * Test the displayed messages when a player win 1 Game (set) within DEUCE rule
	 * is activated (first Game displayed)
	 */
	@Test
	public void displayGameWinnerWithDeuceRuleMessageTest() {
		// simulate the first game scores
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
			player1.winOnePoint();
		}
		game.setPlayerWhoWinOnePoint(1);
		// Game scores Player 1 : DEUCE - Player 2 : DEUCE
		StringBuilder sbDeuce = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbDeuce)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, DEUCE, PLAYER_TWO,
					DEUCE);
		}
		assertEquals(sbDeuce.toString(), game.displayScoreMainMessage());
		player1.winOnePoint();
		// Game scores Player 1 : ADV - Player 2 : 40
		StringBuilder sbAdvantage = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbAdvantage)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, ADVANTAGE, PLAYER_TWO,
					"40");
		}
		assertEquals(sbAdvantage.toString(), game.displayScoreMainMessage());
		player1.winOnePoint();
		// End of the first game, Game scores Player 1 : 0 - Player 2 : 0
		// (Set scores Player 1 : 1 - Player 2 : 0)
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO, "0",
					PLAYER_ONE, "1", PLAYER_TWO, "0");
		}
		assertEquals(sb.toString(), game.displayScoreMainMessage());
	}

	/**
	 * Test the displayed message when a player win the match with 6 Games (set)
	 */
	@Test
	public void displayMatchWinnerWithSixGamesMessageTest() {
		// simulate the Set scores Player 1 : 5 - Player 2 : 4
		game.setFirstGame(false);
		for (int i = 0; i <= 3; i++) {
			player2.winOneSet();
			player1.winOneSet();
		}
		player1.winOneSet();
		// simulate the sixth game scores, Game scores Player 1 : 40 - Player 2 : 30
		// (Set scores Player 1 : 5 - Player 2 : 4)
		for (int i = 0; i <= 1; i++) {
			player2.winOnePoint();
			player1.winOnePoint();
		}
		player1.winOnePoint();
		player1.winOnePoint();
		game.setPlayerWhoWinOnePoint(1);
		// Player 1 win 1 Game (set)
		// (Set scores Player 1 : 6 - Player 2 : 4)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO, "0",
					PLAYER_ONE, "6", PLAYER_TWO, "4");
		}
		assertEquals(sbWinOneGame.toString(), game.displayScoreMainMessage());
		// Player 1 win the set and the match
		StringBuilder sbWinWinTheMatch = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinWinTheMatch)) {
			fmt.format(MATCH_END_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE);
		}
		assertEquals(sbWinWinTheMatch.toString(), game.displayScoreMainMessage());
		assertTrue(game.isGameOver());
	}

	/**
	 * Test the displayed message when a player win the match with 7 Games and two
	 * Games more than his opponent
	 */
	@Test
	public void displayMatchWinnerWithSevenGamesMessageTest() {
		// simulate the Set scores Player 1 : 5 - Player 2 : 6
		game.setFirstGame(false);
		for (int i = 0; i <= 4; i++) {
			player1.winOneSet();
			player2.winOneSet();
		}
		player2.winOneSet();
		// simulate the seventh game scores, Game scores Player 1 : 30 - Player 2 : 40
		// (Set scores Player 1 : 5 - Player 2 : 6)
		for (int i = 0; i <= 1; i++) {
			player1.winOnePoint();
			player2.winOnePoint();
		}
		player2.winOnePoint();
		player2.winOnePoint();
		game.setPlayerWhoWinOnePoint(2);
		// Player 2 win 1 Game (set)
		// (Set scores Player 1 : 5 - Player 2 : 7)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO, "0",
					PLAYER_ONE, "5", PLAYER_TWO, "7");
		}
		assertEquals(sbWinOneGame.toString(), game.displayScoreMainMessage());
		// Player 2 win the set and the match
		StringBuilder sbWinWinTheMatch = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinWinTheMatch)) {
			fmt.format(MATCH_END_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_TWO);
		}
		assertEquals(sbWinWinTheMatch.toString(), game.displayScoreMainMessage());
		assertTrue(game.isGameOver());
	}

	/**
	 * Test the displayed message when the 2 players reached the score of 6 Games
	 * and the Tie-Break rule is activated
	 */
	@Test
	public void displayMatchWithTieBreakRuleActivatedMessageTest() {
		// simulate the Set scores Player 1 : 6 - Player 2 : 6
		// Tie-break rule is activated
		game.setFirstGame(false);
		for (int i = 0; i <= 5; i++) {
			player1.winOneSet();
			player2.winOneSet();
		}
		// simulate the seventh game scores, Game scores Player 1 : 30 - Player 2 : 40
		// (Set scores Player 1 : 6 - Player 2 : 6)
		for (int i = 0; i <= 1; i++) {
			player1.winOnePoint();
			player2.winOnePoint();
		}
		player2.winOnePoint();
		player2.winOnePoint();
		game.setPlayerWhoWinOnePoint(2);
		// Player 2 win 1 Game (set)
		// (Set scores Player 1 : 6 - Player 2 : 6)
		// (Tie-break scores Player 1 : 0 - Player 2 : 1)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(TIE_BREAK_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO,
					"0", PLAYER_ONE, "6", PLAYER_TWO, "6", PLAYER_ONE, 0, PLAYER_TWO, 1);
		}
		assertEquals(sbWinOneGame.toString(), game.displayScoreMainMessage());
		assertTrue(game.isTieBreakRule());
		assertFalse(game.isGameOver());
	}

	/**
	 * Test the displayed message when the 2 players reached the score of 6 Games,
	 * the Tie-Break rule is activated, a player has two Games more than his
	 * opponent but less than 7 Games - the Tie-Break rule is still activated
	 */
	@Test
	public void displayMatchWithTieBreakRuleActivatedAndLessThanSevenGamesMessageTest() {
		// simulate the Set scores Player 1 : 6 - Player 2 : 6
		game.setFirstGame(false);
		game.setTieBreakRule(true);
		for (int i = 0; i <= 5; i++) {
			player1.winOneSet();
			player2.winOneSet();
		}
		// simulate the Tie-break scores Player 1 : 4 - Player 2 : 3
		player1.winOneTieBreak();
		for (int i = 0; i <= 2; i++) {
			player1.winOneTieBreak();
			player2.winOneTieBreak();
		}
		// simulate the seventh game scores, Game scores Player 1 : 30 - Player 2 : 40
		// (Set scores Player 1 : 6 - Player 2 : 6)
		// (Tie-break scores Player 1 : 4 - Player 2 : 3)
		for (int i = 0; i <= 1; i++) {
			player2.winOnePoint();
			player1.winOnePoint();
		}
		player1.winOnePoint();
		player1.winOnePoint();
		game.setPlayerWhoWinOnePoint(1);
		// Player 1 win 1 Game (Tie-break)
		// (Set scores Player 1 : 6 - Player 2 : 6)
		// (Tie-break scores Player 1 : 5 - Player 2 : 3)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(TIE_BREAK_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO,
					"0", PLAYER_ONE, "6", PLAYER_TWO, "6", PLAYER_ONE, 5, PLAYER_TWO, 3);
		}
		assertEquals(sbWinOneGame.toString(), game.displayScoreMainMessage());
		assertTrue(game.isTieBreakRule());
		assertFalse(game.isGameOver());
	}

	/**
	 * Test the displayed message when a player win the match with and the Tie-Break
	 * rule is activated
	 */
	@Test
	public void displayMatchWinnerWithTieBreakRuleActivatedMessageTest() {
		// simulate the Set scores Player 1 : 6 - Player 2 : 6
		game.setFirstGame(false);
		game.setTieBreakRule(true);
		for (int i = 0; i <= 4; i++) {
			player1.winOneSet();
			player2.winOneSet();

		}
		player1.winOneSet();
		player2.winOneSet();
		// simulate the Tie-break scores Player 1 : 9 - Player 2 : 8
		player1.winOneTieBreak();		
		for (int i = 0; i <= 7; i++) {
			player1.winOneTieBreak();
			player2.winOneTieBreak();
		}
		// simulate the seventh game scores, Game scores Player 1 : 30 - Player 2 : 40
		// (Set scores Player 1 : 6 - Player 2 : 6)
		// (Tie-break scores Player 1 : 9 - Player 2 : 8)
		for (int i = 0; i <= 1; i++) {
			player2.winOnePoint();
			player1.winOnePoint();
		}
		player1.winOnePoint();
		player1.winOnePoint();
		game.setPlayerWhoWinOnePoint(1);
		// Player 1 win 1 Game (Tie-break)
		// (Set scores Player 1 : 6 - Player 2 : 6)
		// (Tie-break scores Player 1 : 10 - Player 2 : 8)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(TIE_BREAK_END_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE, "0", PLAYER_TWO,
					"0", PLAYER_ONE, "6", PLAYER_TWO, "6", PLAYER_ONE, 10, PLAYER_TWO, 8);
		}
		assertEquals(sbWinOneGame.toString(), game.displayScoreMainMessage());
		// Player 1 win the set and the match
		StringBuilder sbWinWinTheMatch = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinWinTheMatch)) {
			fmt.format(MATCH_END_SCORE_FORMATTER_PATTERN, game.getPlayerWhoWinOnePoint(), PLAYER_ONE);
		}
		assertEquals(sbWinWinTheMatch.toString(), game.displayScoreMainMessage());
		assertTrue(game.isTieBreakRule());
		assertTrue(game.isGameOver());
	}

}
