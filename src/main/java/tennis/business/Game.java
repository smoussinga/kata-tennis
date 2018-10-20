package main.java.tennis.business;

import java.util.Formatter;
import java.util.Random;
import main.java.tennis.model.Player;

import static main.java.tennis.app.Constants.EMPTY_STRING;
import static main.java.tennis.app.Constants.GAME_START_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.GAME_SCORE_FORMATTER_PATTERN;
import static main.java.tennis.app.Constants.GAME_END_FORMATTER_PATTERN;

/**
 * @author smoussinga
 *
 *         Manage the score of a tennis game
 *
 */
public class Game {

	private Player player1;
	private Player player2;
	private int pointWinner;
	private boolean isGameOver;

	/**
	 * @param player1
	 *            the first player
	 * @param player2
	 *            the second player
	 */
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.pointWinner = 0;
		this.isGameOver = false;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getPointWinner() {
		return this.pointWinner;
	}

	public void setPointWinner(int pointWinner) {
		this.pointWinner = pointWinner;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * Choose a random player to win one point
	 */
	public void designRandomPointWinner() {
		Random r = new Random();
		this.pointWinner = r.nextInt(2) + 1;
		switch (this.pointWinner) {
		case 1:
			this.player1.winOnePoint();
			break;
		case 2:
			this.player2.winOnePoint();
			break;
		default:
			break;
		}
	}

	/**
	 * Build the message to display
	 * 
	 * @return the message to display
	 */
	public String getScoreMessage() {
		if (this.player1.getScore() > 3 || this.player2.getScore() > 3) {
			return displayGameWinnerMessage();
		}
		return displayScoreMessage();
	}

	/**
	 * Build the message with the winner of the game
	 * 
	 * @return the game winner message to display
	 */
	private String displayGameWinnerMessage() {
		// the last player to win a point
		Player playerWinner = (this.pointWinner == 1) ? this.player1 : this.player2;
		// reset players score
		endGame();
		// formatting the message to display
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		return formatGameWinnerMessage(sb, playerWinner);
	}

	/**
	 * Build the message with the score of the game
	 * 
	 * @return the game score message to display
	 */
	private String displayScoreMessage() {
		// formatting the message to display
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		if (this.pointWinner > 0) {
			return formatScoreMessage(sb);
		} else {
			return formatGameStartMessage(sb);
		}
	}

	/**
	 * Format the message with the winner of the game
	 * 
	 * @param sb
	 *            the destination of the formatted message
	 * @param playerWinner
	 *            the winner of the game
	 * @return the game winner message
	 */
	private String formatGameWinnerMessage(StringBuilder sb, Player playerWinner) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, this.pointWinner, this.pointWinner, playerWinner.getName());
			this.isGameOver = true;
		}
		return sb.toString();
	}

	/**
	 * Format the message with the score of the game
	 * 
	 * @param sb
	 *            the destination of the formatted message
	 * @return the game score message
	 */
	private String formatScoreMessage(StringBuilder sb) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_SCORE_FORMATTER_PATTERN, this.pointWinner, this.player1.getName(),
					this.player1.displayScore(), this.player2.getName(), this.player2.displayScore());
		}
		return sb.toString();
	}

	/**
	 * Format the message with the start info of the game
	 * 
	 * @param sb
	 *            the destination of the formatted message
	 * @return the start game message
	 */
	private String formatGameStartMessage(StringBuilder sb) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_START_FORMATTER_PATTERN, this.player1.getName(), this.player1.displayScore(),
					this.player2.getName(), this.player2.displayScore());
		}
		return sb.toString();
	}

	/**
	 * Reset the players points
	 */
	private void endGame() {
		this.player1.resetGamePoint();
		this.player2.resetGamePoint();
	}

}