package Models;

public class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctOption;

    public Question(String questionText, String optionA, String optionB, String optionC, String optionD, char correctOption) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }

    // Getters
    public String getQuestionText() { return questionText; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getOptionD() { return optionD; }
    public char getCorrectOption() { return correctOption; }
    
    public String[] getOptions() {
        return new String[] { optionA, optionB, optionC, optionD };
    }

    public int getCorrectAnswerIndex() {
        switch(correctOption) {
            case 'A': return 0;
            case 'B': return 1;
            case 'C': return 2;
            case 'D': return 3;
            default: return -1;
        }
    }

    public String getQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
