package lastpencil;

import java.util.*;

/**
 * Project obtained from HyperSkill (jetBrains Academy).
 * The objective of the game is to avoid being taking
 * the last pencil. The game allows a user to play
 * against a bot, who always win depending on the number
 * of pencils remaining in the game.
 * @author PoohLove (SWH)
 */
public class Main {
    public static String player1 = "John";
    public static  String player2 = "Jack";
    public static int numberOfPencils = 0;
    public static void main(String[] args) {
        // Creating bot object
        PencilBot bot = new PencilBot(player2);
        String currentPlayer;
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("How many pencils would you like to use:");
            while (true) {
                String pencils = scanner.nextLine();
                boolean answer = getPencils(pencils);
                if (answer)
                    break;
            }
            System.out.println("Who will be the first (John, Jack):");
            while (true) {
                String player = scanner.nextLine();
                if (player.equals(player1) || player.equals(player2)) {
                    currentPlayer = player;
                    System.out.println(displayPencils());
                    break;
                } else {
                    System.out.println("Choose between 'John' and 'Jack'");
                }
            }
            
            // play the game
            while (true) {
                if (numberOfPencils <= 0 ) {
                    System.out.println(currentPlayer + " won");
                    break;
                } else {
                    if (currentPlayer.equals(player2)) {
                        System.out.println(player2 + "'s turn:");
                        bot.setMoves(numberOfPencils);
                        int botMove = bot.botPlays();
                        numberOfPencils -= botMove;
                        System.out.println(botMove);
                        if (numberOfPencils != 0) {
                            System.out.println(displayPencils());
                        }

                    } else if (currentPlayer.equals(player1)) {
                        System.out.println(currentPlayer + "'s turn!");
                        while (true) {
                            try {
                                int pick = scanner.nextInt();
                                boolean valid = monitorPlay(pick);
                                if (valid) {
                                    numberOfPencils -= pick;
                                    if (numberOfPencils != 0) {
                                        System.out.println(displayPencils());
                                    }
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Possible values: '1', '2' or'3'");
                                scanner.nextLine();
                            }
                        }
                    }
                    // switch players
                    currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
                }
            }
        }
    }

    /**
     * Displays a string representation of the number of pencils
     * throughout the game.
     * @return string of pencils
     */
    public static String displayPencils(){
        String pencils = "|".repeat(numberOfPencils);
        if (pencils.isEmpty()) {
            pencils = pencils.trim();
        }
        return pencils;
    }

    /**
     * Obtains the initial amount of pencils from the user. If the entered
     * amount is not positive or a number, an error message informing
     * the user of such would be displayed.
     * @param pen the string value representing the number of pencils
     * @return true or false.
     */
    public static boolean getPencils(String pen) {
        try {
            int firstPencils = Integer.parseInt(pen);

            if (firstPencils > 0) {
                numberOfPencils = firstPencils;
                return true;
            } else if (firstPencils == 0) {
                System.out.println("The number of pencils should be positive");
            } else if (firstPencils < 0) {
                System.out.println("The number of pencils should be numeric");
            }
        } catch (NumberFormatException n) {
            System.out.println("The number of pencils should be numeric");
        }
        return false;
    }

    /**
     * Ensures the user is entering values between 1 and 3.
     * @param number value entered by the user
     * @return true or false
     */
    public static boolean monitorPlay(int number) {
        if (number > 0 && number <= 3) {
            if (number <= numberOfPencils)
                return true;
            else {
                System.out.println("Too many pencils were taken");
            }
        } else {
            System.out.println("Possible values: '1', '2', or '3' ");
        }
        return false;
    }
}
