package game;

import game.values.RandomChances;
import game.values.Questions;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class Solver {
    public static void main(String[] args) throws Exception {
        String netID = "gj245";
        BoardGame game = new BoardGame(netID);

        // ---- Analyze board BEFORE running ----
        int boardLength = 0;
        int bonusCount = 0;
        TileNode cur = game.getBoard();
        while (cur != null) {
            boardLength++;
            if (cur.getTile() == Tile.BONUS)
                bonusCount++;
            cur = cur.getNext();
        }

        int numPieces = game.getPieces().size();

        // ---- Run the game (exact same as Driver) ----
        while (game.nextTurn())
            ;

        // ---- Analyze end state ----
        int turns = game.getTurn();

        int finishedCount = 0;
        String richPiece = "";
        int richAmt = -1;
        String poorPiece = "";
        int poorAmt = Integer.MAX_VALUE;

        for (Piece p : game.getPieces()) {
            if (p.getTileNode().getTile() == Tile.END)
                finishedCount++;
            int coins = p.getCoins();
            if (coins > richAmt) {
                richAmt = coins;
                richPiece = p.getName();
            }
            if (coins < poorAmt) {
                poorAmt = coins;
                poorPiece = p.getName();
            }
        }

        // ---- Print all answers for verification ----
        System.out.println("=== ALL ANSWERS FOR " + netID + " ===");
        System.out.println("Turns:            " + turns);
        System.out.println("Num Pieces:       " + numPieces);
        System.out.println("Max Pieces:       " + RandomChances.MAX_PIECES);
        System.out.println("Min Dice:         " + RandomChances.MIN_DICE_ROLL);
        System.out.println("Max Dice:         " + RandomChances.MAX_DICE_ROLL);
        System.out.println("Board Tiles:      " + boardLength);
        System.out.println("Bonus Tiles:      " + bonusCount);
        System.out.println("Finished Pieces:  " + finishedCount);
        System.out.println("Richest Piece:    " + richPiece);
        System.out.println("Poorest Piece:    " + poorPiece);
        System.out.println();

        // ---- Print all pieces and coins ----
        System.out.println("=== PIECE DETAILS ===");
        for (Piece p : game.getPieces()) {
            System.out
                    .println("  " + p.getName() + " -> coins=" + p.getCoins() + ", tile=" + p.getTileNode().getTile());
        }
        System.out.println();

        // ---- Get the SAME 6 random questions the Driver would ask ----
        // StdRandom state is identical to Driver at this point
        Questions[] ques = game.getQuestions();

        System.out.println("=== YOUR 6 QUESTIONS ===");
        for (int i = 0; i < ques.length; i++) {
            System.out.println("Q" + (i + 1) + ": " + ques[i].getQuestion());
        }
        System.out.println();

        // ---- Map each question to its answer ----
        String[] answers = new String[ques.length + 2];
        answers[0] = netID;
        answers[1] = "Spring 2026 Board Game Simulation Answers";

        for (int i = 0; i < ques.length; i++) {
            String q = ques[i].getQuestion();
            String a = "";
            if (q.contains("turns"))
                a = "" + turns;
            else if (q.contains("pieces were"))
                a = "" + numPieces;
            else if (q.contains("maximum number of pieces"))
                a = "" + RandomChances.MAX_PIECES;
            else if (q.contains("minimum dice"))
                a = "" + RandomChances.MIN_DICE_ROLL;
            else if (q.contains("maximum dice"))
                a = "" + RandomChances.MAX_DICE_ROLL;
            else if (q.contains("BONUS"))
                a = "" + bonusCount;
            else if (q.contains("tiles are"))
                a = "" + boardLength;
            else if (q.contains("finished"))
                a = "" + finishedCount;
            else if (q.contains("most coins"))
                a = richPiece;
            else if (q.contains("least coins"))
                a = poorPiece;

            answers[i + 2] = a;
            System.out.println("  -> Answer " + (i + 1) + ": " + a);
        }

        // ---- Write answers.out ----
        PrintWriter out = new PrintWriter(new FileOutputStream("answers.out"), true);
        for (String answer : answers) {
            out.println(answer);
        }
        out.close();

        System.out.println();
        System.out.println(">>> answers.out has been generated! <<<");
    }
}
