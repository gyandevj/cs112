package game.values;

public enum Questions {
    QUESTION1("How many turns did the game last?"),
    QUESTION2("How many pieces were on your board?"),
    QUESTION3("What is the maximum number of pieces possible for a game?"),
    QUESTION4("What is the minimum dice roll possible?"),
    QUESTION5("What is the maximum dice roll possible?"),
    QUESTION6("How many tiles are on your board?"),
    QUESTION7("How many BONUS tiles are on your board?"),
    QUESTION8("How many pieces finished the game?"),
    QUESTION9("Which piece had the most coins at the end of the game?"),
    QUESTION10("Which piece had the least coins at the end of the game?");

    private final String question;

    private Questions(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
