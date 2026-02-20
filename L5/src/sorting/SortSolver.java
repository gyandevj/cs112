package sorting;

public class SortSolver {
    public static final String INPUT_FILE = "input.in";

    private int lines;
    private String netID;
    
    public SortSolver(String netID) {
        this.netID = netID;
        StdRandom.setSeed(netID.hashCode() - 1);
        StdIn.setFile(INPUT_FILE);
        lines = 0; 
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            lines++;
            String[] e = line.split(",");
            int rand = StdRandom.uniformInt(4);
            switch (rand) {
                case 0: 
                    Sorts.sort1(e); 
                    break;
                case 1: 
                    Sorts.sort2(e); 
                    break;
                case 2: 
                    Sorts.sort3(e); 
                    break;
                case 3: 
                    Sorts.sort4(e); 
                    break;
            }
        } 
    }

    public String[] getQuestions() {
        String[] questions = new String[Questions.values().length];
        int curr = 0; 
       
        String[] sortNames = {"Insertion Sort", "Selection Sort", "Merge Sort", "Quick Sort"};
        String q1 = sortNames[StdRandom.uniformInt(sortNames.length)]; 
        questions[curr++] = Questions.QUESTION1.toString().replace("*", q1);

        int q2 = StdRandom.uniformInt(1,5);
        questions[curr++] = Questions.QUESTION2.toString().replace("%", ""+q2);
 
        questions[curr++] = Questions.QUESTION3.toString();
 
        questions[curr++] = Questions.QUESTION4.toString();

        questions[curr++] = Questions.QUESTION5.toString(); 
 
        questions[curr++] = Questions.QUESTION6.toString();
 
        questions[curr++] = Questions.QUESTION7.toString();

        int q8 = StdRandom.uniformInt(1, 16); 
        questions[curr++] = Questions.QUESTION8.toString().replace("#", ""+q8);

        return questions;
    } 
}
