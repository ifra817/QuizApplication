package Service;

import Database.DatabaseHelper;
import Models.Question;
import java.util.List;

public class StudentService {
    private final DatabaseHelper dbHelper = new DatabaseHelper();

    // Method for user validation
    public boolean validateUser(String username, String password) {
        return dbHelper.checkUserCredentials(username, password);
    }

    // Method for fetching quiz questions
    public List<Question> getQuestions() {
        return dbHelper.fetchQuestions();
    }

    // Method to save the user's score
    public void saveScore(String username, int score, String regNo) {
        dbHelper.saveScore(username, score, regNo);
    }

}
