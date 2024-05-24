/* Build a program that generates a random number and challenges the user to guess it. The program should prompt the user to input their guess,
compare it to the generated number,and provide feedback if the guess is too high or too low. It should continue until the user correctly guesses the number and 
then display the number of attempts it took to win the game.*/

import java.util.Random;
import java.util.Scanner;

public class RanNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("I have picked a number between 1 and 100. Try to guess it!");

        int guess;
        boolean correctGuess = false;

        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                correctGuess = true;
            }
        } while (!correctGuess);

        System.out.println("Congratulations! You've guessed the number " + numberToGuess + " correctly.");
        System.out.println("It took you " + attempts + " attempts.");

        scanner.close();
    }
}
