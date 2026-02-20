package game;

import java.util.ArrayList;
import java.util.Arrays;

import game.stdlib.StdIn;
import game.stdlib.StdOut;
import game.stdlib.StdRandom;
import game.values.Effects;
import game.values.Questions;
import game.values.RandomChances;

public class BoardGame {
 
    private static int NUM_QUESTIONS = 6; 

    private TileNode board;
    private ArrayList<Piece> pieces;
    private int turn = 0;

    private String netID;
    private int boardLength;
    private int numPieces;

    public BoardGame(String netID) {  
        this.netID = netID;
        StdRandom.setSeed(netID.toLowerCase().trim().hashCode() + 1); 
        this.board = createBoard(); 
        selectPieces();
    }

    public boolean nextTurn() { 
        turn++; 
        for (Piece piece : pieces) {   
            if (piece.getTileNode().getTile() == Tile.END) {
                continue;
            } 

            int diceRoll = StdRandom.uniformInt(RandomChances.MIN_DICE_ROLL, RandomChances.MAX_DICE_ROLL);
            movePiece(piece, diceRoll);

            activateTile(piece); 
 
            if (piece.getTileNode().getTile() == Tile.END && gameOver()) { 
                return false;
            }
        }
        return true;
    }

    private void movePiece(Piece piece, int numTiles) { 
        if ((numTiles <= 0) || (piece.getTileNode() == null) || (piece.getTileNode().getNext() == null) || (piece.getTileNode().getTile() == Tile.END)) {
            return;
        } 
        piece.setTileNode(piece.getTileNode().getNext());
        movePiece(piece, numTiles - 1);
    } 

    private boolean gameOver() {
        for (Piece piece : pieces) {
            if (piece.getTileNode().getTile() != Tile.END) { 
                return false;
            }
        }
        return true;
    }

    private void activateTile(Piece piece) {
        TileNode currentTile = piece.getTileNode();
        if (currentTile != null) {
            Tile tile = currentTile.getTile();
            Tile[] randoms = {
                Tile.CHANCE,
                Tile.BONUS,
                Tile.TAX,
                Tile.COIN
            };
            if (currentTile.getTile() == Tile.RANDOM) {
                int randomIndex = StdRandom.uniformInt(0, randoms.length);
                tile = randoms[randomIndex]; 
            }
            
            switch (tile) {
                case CHANCE:
                    int chanceIndex = StdRandom.uniformInt(0, Effects.chances.length);
                    String chanceValue = Effects.chances[chanceIndex].toString(); 
                    chanceTile(piece, chanceIndex);
                    return;
                case BONUS:
                    int bonusIndex = StdRandom.uniformInt(0, Effects.bonuses.length);
                    String bonusValue = Effects.bonuses[bonusIndex].toString(); 
                    bonusTile(piece, bonusIndex);
                    return;
                case TAX: 
                    int taxIndex = StdRandom.uniformInt(0, Effects.taxes.length);
                    String taxValue = Effects.taxes[taxIndex].toString(); 
                    taxTile(piece, taxIndex);
                    return;
                case COIN:
                    int coinIndex = StdRandom.uniformInt(0, Effects.coins.length);
                    String coinValue = Effects.coins[coinIndex].toString(); 
                    coinTile(piece, coinIndex);
                    return; 
                default:
                    break;
            }
        }
    }


/*******  GAME SETUP METHODS  **********************************************************************************************************************************************/

    public final Tile[] tiles = {  
        Tile.PATH,
        Tile.CHANCE,
        Tile.BONUS,
        Tile.COIN,
        Tile.TAX,
        Tile.RANDOM
    };

    private TileNode createBoard() {
        boardLength = StdRandom.uniformInt(RandomChances.MIN_BOARD_LENGTH, RandomChances.MAX_BOARD_LENGTH); 
        TileNode head = null;
        TileNode current = null;

        for (int i = 0; i < boardLength-1; i++) {  
            if (head == null) {
                head = new TileNode(Tile.END, boardLength);  
                head.setNext(null);
                current = head;
            } else {
                int tileIndex = StdRandom.uniformInt(0, tiles.length);
                Tile tile = tiles[tileIndex];
                TileNode newNode = new TileNode(tile, boardLength - i);

                head.setPrevious(newNode);
                newNode.setNext(head);
                newNode.setPrevious(null);
                head = newNode;
            }
        }

        TileNode startNode = new TileNode(Tile.START, 1);
        startNode.setNext(head);
        head.setPrevious(startNode); 
        return startNode;
    }

    private void selectPieces() {
        pieces = new ArrayList<>();
        numPieces = StdRandom.uniformInt(RandomChances.MIN_PIECES, RandomChances.MAX_PIECES); 
        int i = 0;
        while (i < numPieces) {
            int pieceIndex = StdRandom.uniformInt(0, RandomChances.pieceNames.length);
            String pieceName = RandomChances.pieceNames[pieceIndex];
            Piece piece = new Piece(pieceName, this.board);
            if (!pieces.contains(piece)) {
                pieces.add(piece);
                i++;
            }
        } 
    }

/*******  GETTER METHODS  *****************************************************************************************************************************************************/

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public TileNode getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public String getNetID() {
        return netID;
    }

/*******  TILE EFFECT METHODS  *****************************************************************************************************************************************************/

    public void chanceTile(Piece piece, int index) {
        switch (index) {
            case 0:
                boolean chance = 0 == StdRandom.uniformInt(RandomChances.CHANCE_1);
                if (chance) {
                    piece.addCoins(2);
                } else {
                    piece.removeCoins(1);
                } 
                break;
            case 1:
                boolean chance2 = 0 == StdRandom.uniformInt(RandomChances.CHANCE_2);
                if (chance2) {
                    piece.addCoins(10);
                } else {
                    piece.removeCoins(5);
                } 
                break;
            case 2:
                boolean chance3 = 0 == StdRandom.uniformInt(RandomChances.CHANCE_3);
                if (chance3) {
                    piece.addCoins(5);
                } else {
                    piece.removeCoins(5);
                } 
                break;  
            default:
                break;
        }
    }

    public void bonusTile(Piece piece, int index) {
        switch (index) {
            case 0:
                TileNode currentTile = piece.getTileNode();
                for (int i = 0; i < 3 && currentTile != null && currentTile.getPrevious() != null; i++) {
                    currentTile = currentTile.getPrevious();
                }
                piece.setTileNode(currentTile); 
                break;
            case 1:
                movePiece(piece, 2); 
                break;
            case 2: 
                break;
            case 3:
                int forward = StdRandom.uniformInt(1,4);
                movePiece(piece, forward); 
                break;
            case 4:
                int forward2 = StdRandom.uniformInt(2,5);
                movePiece(piece, forward2); 
                break;
            default:
                break;
        }
    }

    public void taxTile(Piece piece, int index) {
        switch (index) {
            case 0: 
                piece.removeCoins(1);
                break;
            case 1: 
                piece.removeCoins(2);
                break;
            case 2: 
                piece.removeCoins(3);
                break;
            case 3: 
                piece.removeCoins(5);
                break;
            case 4: 
                piece.removeCoins(5); 
                break;
            default:
                break;
        }
    }

    public void coinTile(Piece piece, int index) {
        switch (index) {
            case 0: 
                piece.addCoins(1);
                break;
            case 1: 
                piece.addCoins(3);
                break;
            case 2: 
                piece.addCoins(4);
                break;
            case 3: 
                piece.addCoins(5);
                break;
            case 4: 
                piece.addCoins(10);
                break;
            default:
                break;
        }
    }

    public Questions[] getQuestions() {
        Questions[] ques = {
            Questions.QUESTION1,
            Questions.QUESTION2,
            Questions.QUESTION3,
            Questions.QUESTION4,
            Questions.QUESTION5,
            Questions.QUESTION6,
            Questions.QUESTION7,
            Questions.QUESTION8,
            Questions.QUESTION9,
            Questions.QUESTION10
        };
        ArrayList<Questions> questionsList = new ArrayList<>(Arrays.asList(ques));
        Questions[] questions = new Questions[NUM_QUESTIONS]; 
        for (int i = 0; i < NUM_QUESTIONS; i++) {
            int questionIndex = StdRandom.uniformInt(0, questionsList.size());
            Questions question = questionsList.get(questionIndex);
            questionsList.remove(questionIndex);  
            questions[i] = question; 
        }
        return questions;
    }

    public String[] askQuestions() {
        Questions[] ques = getQuestions();  

        String[] answers = new String[ques.length + 2];
        answers[0] = netID;
        answers[1] = "Spring 2026 Board Game Simulation Answers";
        // Print questions to terminal, to collect answers
        for (int i = 0; i < ques.length; i++) {
            StdOut.println("Question " + (i+1) + ": " + ques[i].getQuestion());
            StdIn.resetFile();
            String answer = StdIn.readLine().trim();
            answers[i+2] = answer;
            StdOut.println();
        }  
        
        StdOut.setFile("questions.out");
        String warning = "Note: this file is simply provided so you can easily reference your questions. You will not submit this file.\nRun Driver.java, then answer the questions via the terminal and submit the generated answers.out file.\n";
        StdOut.println(netID);
        StdOut.println(warning); 
        // Reprint questions to questions.out, for easier reference
        for (int i = 0; i < ques.length; i++) { 
            StdOut.println("Question " + (i+1) + ": " + ques[i].getQuestion());
        }

        return answers;
    }

}