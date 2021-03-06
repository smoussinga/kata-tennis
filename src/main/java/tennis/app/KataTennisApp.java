package main.java.tennis.app;

import java.util.Random;
import java.util.Scanner;
import main.java.tennis.business.Game;
import main.java.tennis.model.Player;

import static main.java.tennis.app.Constants.FIRST_PLAYER_NAME_STRING;
import static main.java.tennis.app.Constants.SECOND_PLAYER_NAME_STRING;
import static main.java.tennis.app.Constants.EMPTY_STRING;
import static main.java.tennis.app.Constants.NEWLINE_STRING;

/**
 * @author smoussinga
 *
 *         Start the kata tennis program
 *
 */
public class KataTennisApp {

	public static void main(String[] args) {
		// first Player Name Entry
		System.out.println(FIRST_PLAYER_NAME_STRING);
		StringBuilder firstPlayerName = new StringBuilder(EMPTY_STRING);
		String first = new Scanner(System.in).nextLine();
		firstPlayerName.append(first);
		// second Player Name Entry
		System.out.println(SECOND_PLAYER_NAME_STRING);
		StringBuilder secondPlayerName = new StringBuilder(EMPTY_STRING);
		String second = new Scanner(System.in).nextLine();
		secondPlayerName.append(second);
		// Instantiate players
		Player player1 = new Player(firstPlayerName.toString());
		Player player2 = new Player(secondPlayerName.toString());
		// Start the game
		Game game = new Game(player1, player2);
		Random random = new Random();
		while (!game.isGameOver()) {
			String scoreDisplay = game.displayScoreMainMessage();
			if (!EMPTY_STRING.equals(scoreDisplay)) {
				System.out.println(scoreDisplay + NEWLINE_STRING);
			}
			game.chooseRandomPlayerToWinOnePoint(random);
		}
	}

}
