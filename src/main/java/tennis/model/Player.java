package main.java.tennis.model;

import java.util.List;
import java.util.Arrays;

/**
 * @author smoussinga
 * 
 *         Model of a player of the tennis game
 *
 */
public class Player {

	private String name;
	private int score;
	private static final List<String> POINTS_SCORES_IN_GAME = Arrays.asList("0", "15", "30", "40");

	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public static List<String> getPointsScoresInGame() {
		return POINTS_SCORES_IN_GAME;
	}

	/**
	 * Increment the score of a player
	 */
	public void winOnePoint() {
		this.score++;
	}

	/**
	 * Display the score from the fixed list
	 * 
	 * @return the score to display
	 */
	public String displayScore() {
		return POINTS_SCORES_IN_GAME.get(this.score);
	}

	/**
	 * Reset the point score of the game
	 */
	public void resetGamePoint() {
		this.score = 0;
	}
}
