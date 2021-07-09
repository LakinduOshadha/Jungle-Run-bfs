package bfs;

import java.util.Scanner;

/**
 * JungleRunDemo to demonstrate Jungle.java
 *
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class JungleRunDemo {
    /**
     * main
     *
     * @param args null
     */
    public static void main(String [] args) {
        // Getting user input
        UserInput newInput = new UserInput();
        char[][] inputArray = newInput.getUserInput();

        try {
            // Creating the jungle
            Jungle newJungle = new Jungle(inputArray,newInput.getN());
            int n = newJungle.noOfMovesToEnd(); // minimum number of moves from S to E
            System.out.println("\nJungle : ");
            newJungle.printJungle();

            // Prints the minimum number of moves from S to E
            System.out.println("\nThe minimum number of moves from S to E : " + n);

            // Prints the path to go
            System.out.println("\nThe path I should go : ");
            newJungle.printJungle();

        } catch (Exception e) {
            System.out.println("Invalid Input");
        }

    }
}

/**
 * Takes input Matrix & the length , from the user
 *
 */
class UserInput {
    private int N; // Length of the inputMatrix

    /**
     * Getter for length of the input Matrix (N)
     * @return N (Length of the input Matrix)
     */
    public int getN() {
        return N;
    }

    /**
     * Gives a brief introduction to user
     * Takes the Matrix and the length of the Matrix , from the user.
     *
     * @return inputArray
     */
    public char[][] getUserInput() {
        // Getting the length of the Matrix
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the size of the matrix (N x N) : ");
        N = sc.nextInt();
        // Getting the Matrix
        System.out.println("Enter the jungle below(Input Format is same as sample input)");
        char inputArray[][] = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                inputArray[i][j] = sc.next().charAt(0);
            }
        }
        return inputArray;
    }
}
