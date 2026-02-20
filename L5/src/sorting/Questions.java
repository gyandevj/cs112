package sorting;

public enum Questions {

    // * is a sort name (insertion sort, selection sort, merge sort, quick sort)
    // % is a sort number (1-4)
    // # is a line number of the file (1-15) 
    QUESTION1("Which sort number is *?"),
    QUESTION2("How many helper methods does sort% have?"),
    QUESTION3("How many times was sort1 called?"),
    QUESTION4("How many times was sort2 called?"),
    QUESTION5("How many times was sort3 called?"),
    QUESTION6("How many times was sort4 called?"), 
    QUESTION7("What was the first sort number called?"),   
    QUESTION8("Which sort was called for line number #?");

    private Questions(String question) {        
        this.question = question;
    }

    private String question;

    @Override
    public String toString() {
        return question;
    }
  
}
