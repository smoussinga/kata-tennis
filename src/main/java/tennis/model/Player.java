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
	private int set;
	private static final List<String> POINTS_SCORES_IN_GAME = Arrays.asList("0", "15", "30", "40");
	private static final List<String> SET_SCORES_IN_GAME = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7");

	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.set = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public int getSet() {
		return this.set;
	}

	public static List<String> getPointsScoresInGame() {
		return POINTS_SCORES_IN_GAME;
	}

	public static List<String> getSetScoresInGame() {
		return SET_SCORES_IN_GAME;
	}

	/**
	 * Increment the points of a player
	 */
	public void winOnePoint() {
		this.score++;
	}

	/**
	 * Increment the sets of a player
	 */
	public void winOneSet() {
		this.set++;
	}

	/**
	 * Display the points from the fixed list
	 * 
	 * @return the points to display
	 */
	public String displayScorePoints() {
		return POINTS_SCORES_IN_GAME.get(this.score);
	}

	/**
	 * Display the sets from the fixed list
	 * 
	 * @return the sets to display
	 */
	public String displayScoreSets() {
		return SET_SCORES_IN_GAME.get(this.set);
	}

	/**
	 * Reset the point score of the game
	 */
	public void resetGamePoint() {
		this.score = 0;
	}
}
