package GUI;

import Models.Question;
import Service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class QuizForm extends JFrame {
    private JLabel questionLabel, timerLabel, imageLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private int currentQuestion = 0;
    private String studentName;
    private String reg_num;
    private int score = 0;
    private int timeLeft = 10; // Time per question in seconds
    private Timer timer;
    private Question[] questions;
    private StudentService studentService; // Use service layer instead of directly interacting with DatabaseHelper

    public QuizForm(String studentName, String reg_num) {
        // Initialize StudentService
        this.studentName = studentName;
        this.reg_num = reg_num;
        studentService = new StudentService();

        setTitle("Quiz Application");
        setBounds(50, 50, 1200, 750); 
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        // Image at the top (JLabel with ImageIcon)
        imageLabel = new JLabel();
        ImageIcon quizImage = new ImageIcon(getClass().getResource("/icons/quiz.jpg"));
        imageLabel.setIcon(quizImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        // Timer Label (Top-right corner)
        timerLabel = new JLabel("10", JLabel.RIGHT);
        timerLabel.setFont(new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 20));
        timerLabel.setForeground(Color.RED);
        add(timerLabel, BorderLayout.EAST);

        // Question Panel
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BorderLayout());
        add(quizPanel, BorderLayout.CENTER);

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Centaur", Font.BOLD, 23));
        questionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        quizPanel.add(questionLabel, BorderLayout.NORTH);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);
        quizPanel.add(optionsPanel, BorderLayout.CENTER);

        // Next Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButtonClick();
            }
        });
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleNextButtonClick(); // Simulates the next button click
                }
            }
        });
        setFocusable(true); // Make sure the frame is focusable
        // Load questions from the database
        loadQuestions();
    }

    private void loadQuestions() {
        try {
            // Fetch data from the database using StudentService
            List<Question> questionList = studentService.getQuestions(); // Fetch list of questions
            questions = questionList.toArray(new Question[0]); // Convert List to Question[]

            if (questions != null && questions.length > 0) {
                loadQuestion(); // Load the first question
            } else {
                JOptionPane.showMessageDialog(this, "No questions found in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load questions from the database.");
        }
    }


    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            Question question = questions[currentQuestion];
            questionLabel.setText(question.getQuestionText());
            option1.setText(question.getOptions()[0]);
            option2.setText(question.getOptions()[1]);
            option3.setText(question.getOptions()[2]);
            option4.setText(question.getOptions()[3]);
            optionsGroup.clearSelection();

            // Reset the timer for the new question
            timeLeft = 10;
            timerLabel.setText("Time Left: " + timeLeft);

            // Start a new timer for countdown
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft--;
                    timerLabel.setText("Time Left: " + timeLeft + " seconds");
                    if (timeLeft <= 0) {
                        handleNextButtonClick(); // Move to next question when time runs out
                    }
                }
            });
            timer.start();
        } else {
            // Save the score using StudentService
            studentService.saveScore(studentName, score, reg_num);       

            showResultScreen();
        }
    }

    private void handleNextButtonClick() {
        Question current = questions[currentQuestion];
        int selectedOption = -1;
        if (option1.isSelected()) selectedOption = 0;
        else if (option2.isSelected()) selectedOption = 1;
        else if (option3.isSelected()) selectedOption = 2;
        else if (option4.isSelected()) selectedOption = 3;

        if (selectedOption != -1 && selectedOption == current.getCorrectAnswerIndex()) {
            score++;
        }
        currentQuestion++;
        loadQuestion(); // Load next question
    }

    private void showResultScreen() {
        ResultScreen resultScreen = new ResultScreen(studentName, score);

        // Set the student's name and score on the ResultScreen
        resultScreen.setNameAndScore(studentName, score);

        // Display the ResultScreen
        resultScreen.setVisible(true);

        // Close the current QuizForm
        dispose();
    }
}
