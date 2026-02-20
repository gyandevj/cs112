package game;

import game.stdlib.StdIn;
import game.stdlib.StdOut;

public class Driver {

    private static final boolean SKIP_INTRO = true;
    
    /*
     * Sleep method
     * 
     * @param int milliseconds to sleep
     */
    private static void wait(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000.0));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Clears terminal
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String intro(boolean skip) {
        System.out.println("Please enter your NetID to start the game:");
        String netID = StdIn.readString().toLowerCase().trim();
        if (skip) return netID;

        wait(2.0);
        clearScreen();

        System.out.println("Welcome, " + netID + "!");
        wait(3.0);

        System.out.println("This is board game debugger.");
        wait(1.5);
        System.out.println("A board game simulation, which you DON'T control.");
        wait(2.5);

        System.out.println();
        System.out.println("Instead, you will use the debugger to observe the game at specific points,\nand then answer questions about the game's events.");
        wait(1.0);
        System.out.println("Good luck!");
        System.out.println("(to disable this intro, set SKIP_INTRO to true on line 8 of Driver.java)");
        wait(4.0);
        clearScreen();

        return netID;
    }
    

    public static void main(String[] args) {
        String netID = intro(SKIP_INTRO);
        BoardGame game = new BoardGame(netID);
        while (game.nextTurn());
        String[] answers = game.askQuestions(); 
        StdOut.setFile("answers.out");
        for (String answer : answers) {
            StdOut.println(answer);
        } 
    }
}
