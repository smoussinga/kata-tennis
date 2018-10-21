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
		assertEquals(sb.toString(), game.getScoreMessage());
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
		game.setPointWinner(2);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "30", PLAYER_TWO, "15");
		}
		assertEquals(sb.toString(), game.getScoreMessage());
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
		game.setPointWinner(2);
		// End of the first game, Game scores Player 1 : 0 - Player 2 : 0
		// (Set scores Player 1 : 0 - Player 2 : 1)
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "0", PLAYER_TWO, "0", PLAYER_ONE,
					"0", PLAYER_TWO, "1");
		}
		assertEquals(sb.toString(), game.getScoreMessage());
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
		game.getScoreMessage();
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
		}
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		game.setPointWinner(1);
		// Game scores Player 1 : DEUCE - Player 2 : DEUCE
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, DEUCE, PLAYER_TWO, DEUCE);
		}
		assertEquals(sb.toString(), game.getScoreMessage());
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
		}
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		player2.winOnePoint();
		game.setPointWinner(2);
		// Game scores Player 1 : 40 - Player 2 : ADV
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "40", PLAYER_TWO, ADVANTAGE);
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	/**
	 * Test the displayed message when a player win 1 Game (set) within DEUCE rule
	 * is activated (first Game displayed)
	 */
	@Test
	public void displayGameWinnerWithDeuceRuleMessageTest() {
		// simulate the first game scores
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
		}
		game.setPointWinner(2);
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		game.setPointWinner(1);
		// Game scores Player 1 : DEUCE - Player 2 : DEUCE
		StringBuilder sbDeuce = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbDeuce)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, DEUCE, PLAYER_TWO, DEUCE);
		}
		assertEquals(sbDeuce.toString(), game.getScoreMessage());
		player1.winOnePoint();
		// Game scores Player 1 : ADV - Player 2 : 40
		StringBuilder sbAdvantage = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbAdvantage)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, ADVANTAGE, PLAYER_TWO, "40");
		}
		assertEquals(sbAdvantage.toString(), game.getScoreMessage());
		player1.winOnePoint();
		// End of the first game, Game scores Player 1 : 0 - Player 2 : 0
		// (Set scores Player 1 : 1 - Player 2 : 0)
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "0", PLAYER_TWO, "0", PLAYER_ONE,
					"1", PLAYER_TWO, "0");
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	@Test
	public void displayMatchWinnerWithSixGamesMessageTest() {
		// simulate the Set scores Player 1 : 5 - Player 2 : 4
		game.setFirstGame(false);
		for (int i = 0; i <= 3; i++) {
			player2.winOneSet();
		}
		game.setPointWinner(2);
		for (int i = 0; i <= 4; i++) {
			player1.winOneSet();
		}
		game.setPointWinner(1);
		// simulate the sixth game scores, Game scores Player 1 : 40 - Player 2 : 30
		// (Set scores Player 1 : 5 - Player 2 : 4)
		for (int i = 0; i <= 1; i++) {
			player2.winOnePoint();
		}
		game.setPointWinner(2);
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		game.setPointWinner(1);
		player1.winOnePoint();
		// Player 1 win 1 Game (set)
		// (Set scores Player 1 : 6 - Player 2 : 4)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "0", PLAYER_TWO, "0", PLAYER_ONE,
					"6", PLAYER_TWO, "4");
		}
		assertEquals(sbWinOneGame.toString(), game.getScoreMessage());
		// Player 1 win the set and the match
		StringBuilder sbWinWinTheMatch = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinWinTheMatch)) {
			fmt.format(MATCH_END_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE);
		}
		assertEquals(sbWinWinTheMatch.toString(), game.getScoreMessage());
	}

	@Test
	public void displayMatchWinnerWithSevenGamesMessageTest() {
		// simulate the Set scores Player 1 : 6 - Player 2 : 6
		game.setFirstGame(false);
		for (int i = 0; i <= 5; i++) {
			player1.winOneSet();
		}
		game.setPointWinner(1);
		for (int i = 0; i <= 5; i++) {
			player2.winOneSet();
		}
		game.setPointWinner(2);
		// simulate the seventh game scores, Game scores Player 1 : 30 - Player 2 : 40
		// (Set scores Player 1 : 6 - Player 2 : 6)
		for (int i = 0; i <= 1; i++) {
			player1.winOnePoint();
		}
		game.setPointWinner(1);
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
		}
		game.setPointWinner(2);
		player2.winOnePoint();
		// Player 2 win 1 Game (set)
		// (Set scores Player 1 : 6 - Player 2 : 7)
		StringBuilder sbWinOneGame = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinOneGame)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "0", PLAYER_TWO, "0", PLAYER_ONE,
					"6", PLAYER_TWO, "7");
		}
		assertEquals(sbWinOneGame.toString(), game.getScoreMessage());
		// Player 2 win the set and the match
		StringBuilder sbWinWinTheMatch = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sbWinWinTheMatch)) {
			fmt.format(MATCH_END_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_TWO);
		}
		assertEquals(sbWinWinTheMatch.toString(), game.getScoreMessage());
	}

}
