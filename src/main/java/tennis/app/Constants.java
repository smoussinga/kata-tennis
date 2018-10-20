package main.java.tennis.app;

/**
 * @author smoussinga
 * 
 *         CLass utility for constants
 *
 */
public final class Constants {

	private Constants() {
	}

	public static final String GAME_START_FORMATTER_PATTERN = "Start the Tennis Game :\n Player 1 (%s) : %s\n Player 2 (%s) : %s";
	public static final String GAME_SCORE_FORMATTER_PATTERN = "Game score (Player %d win 1 point)\n Player 1 (%s) : %s\n Player 2 (%s) : %s";
	public static final String GAME_END_FORMATTER_PATTERN = "Game score (Player %d win 1 point)\n GAME OVER : Player %d (%s) win the game";
	public static final String DEUCE = "DEUCE";
	public static final String ADVANTAGE = "ADV";

	public static final String FIRST_PLAYER_NAME_STRING = "Enter the first player name : ";
	public static final String SECOND_PLAYER_NAME_STRING = "Enter the second player name : ";
	public static final String NEWLINE_STRING = "\n";

	public static final String PLAYER_ONE = "Player 1";
	public static final String PLAYER_TWO = "Player 2";
	public static final String PLAYER_NAME = "Player";

	public static final String EMPTY_STRING = "";
}
