import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter first player's object (R for Rock, P for Paper, S for Scissors):");
        char player1 = scan.next().toUpperCase().charAt(0);

        System.out.println("Enter second player's object (R for Rock, P for Paper, S for Scissors):");
        char player2 = scan.next().toUpperCase().charAt(0);

        scan.close();

        // Validate inputs
        if (!isValidChoice(player1) || !isValidChoice(player2)) {
            System.out.println("Invalid input! Please use only R, P, or S.");
            return;
        }

        // Determine the winner
        if (player1 == player2) {
            System.out.println("Game ends with a tie.");
        } else if ((player1 == 'R' && player2 == 'S') || 
                   (player1 == 'P' && player2 == 'R') || 
                   (player1 == 'S' && player2 == 'P')) {
            System.out.println("Player 1 wins.");
        } else {
            System.out.println("Player 2 wins.");
        }
    }

    // Helper method to validate choices
    private static boolean isValidChoice(char choice) {
        return choice == 'R' || choice == 'P' || choice == 'S';
    }
}
