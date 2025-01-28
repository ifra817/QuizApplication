package Database;

import Models.Question;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {

    private Connection connection;

    // Constructor: Connect to database
    public DatabaseHelper() {
        try {
            String url = "jdbc:mysql://localhost:3306/quiz_db";
            String user = "root";
            String password = "help!!";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully to: " + url);

            connection.setAutoCommit(true); // Ensure changes are committed
            createTables();
        } catch (SQLException e) {
            System.out.println("Database connection or table creation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Check if user credentials are valid
    public boolean checkUserCredentials(String username, String password) {
        String query = "SELECT * FROM student WHERE student_name = ? AND reg_num = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Return true if user exists
        } catch (SQLException e) {
            System.out.println("Error validating user credentials: " + e.getMessage());
            return false;
        }
    }

    // Create tables if they don't exist
    private void createTables() {
        String createQuestionsTable = """
                CREATE TABLE IF NOT EXISTS questions (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    question TEXT NOT NULL,
                    option_a VARCHAR(255) NOT NULL,
                    option_b VARCHAR(255) NOT NULL,
                    option_c VARCHAR(255) NOT NULL,
                    option_d VARCHAR(255) NOT NULL,
                    correct_option CHAR(1) NOT NULL
                );
            """;

        String createStudentTable = """
                CREATE TABLE IF NOT EXISTS student (
                    student_id INT AUTO_INCREMENT PRIMARY KEY,
                    student_name VARCHAR(100) NOT NULL,
                    reg_num VARCHAR(50) UNIQUE NOT NULL
                );
            """;

        String createQuizMarksTable = """
                CREATE TABLE IF NOT EXISTS quiz_marks (
                    student_id INT,
                    student_name VARCHAR(100) NOT NULL,
                    marks INT NOT NULL,
                    PRIMARY KEY (student_id),
                    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE
                );
            """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createQuestionsTable);
            stmt.execute(createStudentTable);
            stmt.execute(createQuizMarksTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    // Add a student to the database
    public void addStudent(String name, String regNo) {
        String query = "INSERT INTO student (student_name, reg_num) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, regNo);
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Save or update score for a student
    public void saveScore(String username, int score, String regNo) {
        int studentId = getStudentIdByRegNo(regNo);
        if (studentId != -1) {
            String checkQuery = "SELECT COUNT(*) FROM quiz_marks WHERE student_id = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, studentId);
                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // If student already has a score in quiz_marks, update it
                    updateStudentScore(studentId, score);
                } else {
                    // If no score exists, insert a new record into quiz_marks
                    String insertQuery = "INSERT INTO quiz_marks (student_id, student_name, marks) VALUES (?, ?, ?)";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, studentId);
                        insertStmt.setString(2, username);
                        insertStmt.setInt(3, score);
                        insertStmt.executeUpdate();
                        System.out.println("Score saved successfully for user: " + username);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error saving/updating score: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Method to update a student's score
    public void updateStudentScore(int studentId, int score) {
        String query = "UPDATE quiz_marks SET marks = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, score);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
            System.out.println("Score updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating score: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Fetch all questions from the database
    public ArrayList<Question> fetchQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String questionText = rs.getString("question");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                String optionD = rs.getString("option_d");
                char correctOption = rs.getString("correct_option").charAt(0);

                questions.add(new Question(questionText, optionA, optionB, optionC, optionD, correctOption));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching questions: " + e.getMessage());
        }

        return questions;
    }

    // Method to fetch student_id based on reg_num
    public int getStudentIdByRegNo(String regNo) {
        String query = "SELECT student_id FROM student WHERE reg_num = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, regNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("student_id");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student_id: " + e.getMessage());
        }
        return -1; // Return -1 if no student is found
    }

    // Execute any query
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
