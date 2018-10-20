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
import static main.java.tennis.app.Constants.GAME_SCORE_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.GAME_END_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.DEUCE;
import static main.java.tennis.app.Constants.ADVANTAGE;

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

	@Test
	public void displayStartScoreMessageTest() {
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_START_FORMATTER_PATTERN, PLAYER_ONE, "0", PLAYER_TWO, "0");
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	@Test
	public void displayGameScoreMessageTest() {
		for (int i = 0; i <= 1; i++) {
			player1.winOnePoint();
		}
		player2.winOnePoint();
		game.setPointWinner(2);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "30", PLAYER_TWO, "15");
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	@Test
	public void displayGameWinnerMessageTest() {
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		game.setPointWinner(2);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPointWinner(), game.getPointWinner(), PLAYER_TWO);
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	@Test
	public void resetGameAfterWinnerTest() {
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		player2.winOnePoint();
		player1.winOnePoint();
		player2.winOnePoint();
		game.getScoreMessage();
		assertEquals(0, game.getPlayer1().getScore());
		assertEquals(0, game.getPlayer2().getScore());
	}

	@Test
	public void displayGameScoreWithDeuceMessageTest() {
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
		}
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		game.setPointWinner(1);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, DEUCE, PLAYER_TWO, DEUCE);
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	@Test
	public void displayGameScoreWithAdvantageMessageTest() {
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
		}
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		player2.winOnePoint();
		game.setPointWinner(2);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_SCORE_FORMATTER_PATTERN, game.getPointWinner(), PLAYER_ONE, "40", PLAYER_TWO, ADVANTAGE);
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}

	@Test
	public void displayGameWinnerWithDeuceRuleMessageTest() {
		for (int i = 0; i <= 2; i++) {
			player2.winOnePoint();
		}
		for (int i = 0; i <= 2; i++) {
			player1.winOnePoint();
		}
		player1.winOnePoint();
		player1.winOnePoint();
		game.setPointWinner(1);
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, game.getPointWinner(), game.getPointWinner(), PLAYER_ONE);
		}
		assertEquals(sb.toString(), game.getScoreMessage());
	}
}
