package main.java.tennis.business;

import java.util.Formatter;
import java.util.Random;
import main.java.tennis.model.Player;

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
 *         Manage the score of a tennis game
 *
 */
public class Game {

	private Player player1;
	private Player player2;
	private int pointWinner;
	private boolean isGameOver;
	private boolean isDeuceRule;
	private boolean isFirstGame;

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
		this.isDeuceRule = false;
		this.isFirstGame = true;
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

	public boolean isDeuce() {
		return isDeuceRule;
	}

	public void setDeuce(boolean isDeuce) {
		this.isDeuceRule = isDeuce;
	}

	public boolean isFirstGame() {
		return isFirstGame;
	}

	public void setFirstGame(boolean isFirstGame) {
		this.isFirstGame = isFirstGame;
	}

	/**
	 * Choose a random player to win one point
	 */
	public void designRandomPointWinner() {
		if (!isGameOver) {
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
	}

	/**
	 * Build the message to display
	 * 
	 * @return the message to display
	 */
	public String getScoreMessage() {
		int maxSet = Math.max(this.player1.getSet(), this.player2.getSet());
		int minSet = Math.min(this.player1.getSet(), this.player2.getSet());
		// a player win the set and the match
		if ((maxSet == 6 && minSet <= 4) || maxSet == 7) {
			this.isGameOver = true;
			return displayGameWinnerMessage();
		} else {
			return displayFullGameScoreMessage();
		}
	}

	/**
	 * Build the message with the winner of the game
	 * 
	 * @return the game winner message to display
	 */
	private String displayGameWinnerMessage() {
		// reset players score
		endGame();
		// formatting the message to display
		StringBuilder sb = new StringBuilder(EMPTY_STRING);
		if (!isGameOver) {
			if (this.pointWinner == 1) {
				this.player1.winOneSet();
				return formatGameWinnerMessage(sb);
			} else {
				this.player2.winOneSet();
				return formatGameWinnerMessage(sb);
			}
		} else {
			if (this.player1.getSet() > this.player2.getSet()) {
				return formatMatchWinnerMessage(sb, 1, this.player1);
			} else {
				return formatMatchWinnerMessage(sb, 2, this.player2);
			}
		}
	}

	/**
	 * Build the message with the full (points & sets) score of the game
	 * 
	 * @return the full game score message to display
	 */
	private String displayFullGameScoreMessage() {
		// the DEUCE rule is activated
		if (this.player1.getScore() >= 3 && this.player2.getScore() >= 3) {
			// the two players reach the score 40
			this.isDeuceRule = true;
			if (Math.abs(player1.getScore() - player2.getScore()) >= 2) {
				// a win with the DEUCE rule
				this.isFirstGame = false;
				return displayGameWinnerMessage();
			}
			// score with the DEUCE rule
			if (this.isFirstGame) {
				return displayScoreMessage();
			}
		}
		// a win with the simple rule
		if (this.player1.getScore() > 3 || this.player2.getScore() > 3) {
			this.isFirstGame = false;
			return displayGameWinnerMessage();
		}
		// score with the simple rule
		if (this.isFirstGame) {
			return displayScoreMessage();
		}
		// the first game ended, only the set score is displayed
		return EMPTY_STRING;
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
			// score with the DEUCE rule, either DEUCE or ADV
			if (isDeuceRule && (player1.getScore() == player2.getScore())) {
				return formatScoreWithDeuceMessage(sb);
			} else if (isDeuceRule && (player1.getScore() != player2.getScore())) {
				return formatScoreWithAdvantageMessage(sb);
			}
			// score with the simple rule
			return formatScoreMessage(sb);
		} else {
			// start game message
			return formatGameStartMessage(sb);
		}
	}

	/**
	 * Format the message with the winner of the set and the match
	 * 
	 * @param sb
	 *            the destination of the formatted message
	 * @param playerWinner
	 *            the winner of the set and the match
	 * @return the match winner message
	 */
	private String formatMatchWinnerMessage(StringBuilder sb, int matchWinner, Player playerWinner) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(MATCH_END_SCORE_FORMATTER_PATTERN, matchWinner, playerWinner.getName());
		}
		return sb.toString();
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
	private String formatGameWinnerMessage(StringBuilder sb) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(GAME_END_FORMATTER_PATTERN, this.pointWinner, this.player1.getName(),
					this.player1.displayScorePoints(), this.player2.getName(), this.player2.displayScorePoints(),
					this.player1.getName(), this.player1.displayScoreSets(), this.player2.getName(),
					this.player2.displayScoreSets());
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
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, this.pointWinner, this.player1.getName(),
					this.player1.displayScorePoints(), this.player2.getName(), this.player2.displayScorePoints());
		}
		return sb.toString();
	}

	/**
	 * Format the message with the DEUCE score
	 * 
	 * @param sb
	 *            the destination of the formatted message
	 * @return the game score message
	 */
	private String formatScoreWithDeuceMessage(StringBuilder sb) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			fmt.format(POINT_SCORE_FORMATTER_PATTERN, this.pointWinner, this.player1.getName(), DEUCE,
					this.player2.getName(), DEUCE);
		}
		return sb.toString();
	}

	/**
	 * Format the message with the ADVANTAGE score
	 * 
	 * @param sb
	 *            the destination of the formatted message
	 * @return the game score message
	 */
	private String formatScoreWithAdvantageMessage(StringBuilder sb) {
		// try-with-resources statement throws RuntimeException
		try (Formatter fmt = new Formatter(sb)) {
			String scoreFourty = Player.getPointsScoresInGame().get(3);
			if (this.pointWinner == 1) {
				fmt.format(POINT_SCORE_FORMATTER_PATTERN, this.pointWinner, this.player1.getName(), ADVANTAGE,
						this.player2.getName(), scoreFourty);
			} else if (this.pointWinner == 2) {
				fmt.format(POINT_SCORE_FORMATTER_PATTERN, this.pointWinner, this.player1.getName(), scoreFourty,
						this.player2.getName(), ADVANTAGE);
			}
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
			fmt.format(GAME_START_FORMATTER_PATTERN, this.player1.getName(), this.player1.displayScorePoints(),
					this.player2.getName(), this.player2.displayScorePoints(), this.player1.getName(),
					this.player1.displayScoreSets(), this.player2.getName(), this.player2.displayScoreSets());
		}
		return sb.toString();
	}

	/**
	 * Reset the players points
	 */
	private void endGame() {
		this.player1.resetGamePoint();
		this.player2.resetGamePoint();
		this.isDeuceRule = false;
		this.isFirstGame = false;
	}

}
