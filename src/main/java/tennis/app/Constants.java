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

	public static final String FIRST_PLAYER_NAME_STRING = "Enter the first player name : ";
	public static final String SECOND_PLAYER_NAME_STRING = "Enter the second player name : ";
	public static final String NEWLINE_STRING = "\n";

	public static final String GAME_SCORE_FORMATTER_PATTERN = "- Game score - Player 1 (%s) : %s - Player 2 (%s) : %s";

	public static final String SET_SCORE_FORMATTER_PATTERN = "-- Set score - Player 1 (%s) : %s - Player 2 (%s) : %s";

	public static final String TIE_BREAK_SCORE_FORMATTER_PATTERN = "-- Tie-Break score - Player 1 (%s) : %d - Player 2 (%s) : %d";

	public static final String GAME_START_FORMATTER_PATTERN = "--- Start the Tennis game & Set :" + NEWLINE_STRING
			+ GAME_SCORE_FORMATTER_PATTERN + NEWLINE_STRING + SET_SCORE_FORMATTER_PATTERN;

	public static final String POINT_SCORE_FORMATTER_PATTERN = "- Score (Player %d win 1 point)" + NEWLINE_STRING
			+ GAME_SCORE_FORMATTER_PATTERN;

	public static final String GAME_END_FORMATTER_PATTERN = "-- Score (Player %d win 1 Game)" + NEWLINE_STRING
			+ GAME_SCORE_FORMATTER_PATTERN + NEWLINE_STRING + SET_SCORE_FORMATTER_PATTERN;

	public static final String TIE_BREAK_END_FORMATTER_PATTERN = "-- Score (Player %d win 1 Tie-Break)" + NEWLINE_STRING
			+ GAME_SCORE_FORMATTER_PATTERN + NEWLINE_STRING + SET_SCORE_FORMATTER_PATTERN + NEWLINE_STRING
			+ TIE_BREAK_SCORE_FORMATTER_PATTERN;

	public static final String MATCH_END_SCORE_FORMATTER_PATTERN = "--- GAME OVER : Player %d (%s) win the set and the match ---";

	public static final String DEUCE = "DEUCE";
	public static final String ADVANTAGE = "ADV";

	public static final String PLAYER_ONE = "Player 1";
	public static final String PLAYER_TWO = "Player 2";
	public static final String PLAYER_NAME = "Player";

	public static final String EMPTY_STRING = "";
}
