# QuizApplication

## **Overview**
QuizApplication is a Java-based desktop application designed to facilitate the creation, management, and completion of quizzes. Built using Object-Oriented Programming (OOP) principles, it provides a user-friendly Graphical User Interface (GUI) and integrates with a MySQL database to ensure secure data storage and retrieval.

## **Key Features**
- User authentication and registration.
- Dynamic quiz creation with multiple-choice questions.
- Tracks and saves students' quiz marks in the database.
- Seamless integration with MySQL for data management.
- GUI designed using Java Swing with drag-and-drop functionality for easy frame creation.
- Modular architecture with four distinct packages.

## **Project Structure**
The project is organized into the following packages:

### **GUI**
Handles all graphical user interface components of the application.  
**Classes:**
- `Main.java`: Entry point of the application.
- `LoginForm.java`: Manages user login functionality.
- `StartQuiz.java`: Provides the interface for selecting and starting a quiz.
- `QuizForm.java`: Manages the interface for taking quizzes, including question navigation and option selection.
- `ResultScreen.java`: Displays the quiz results to the user.

### **Database**
Manages database connectivity and operations.  
**Classes:**
- `DatabaseHelper`: Connects to the MySQL database and includes methods for creating tables (student, questions, and quiz_marks) and performing CRUD operations. The required SQL queries to create the tables are embedded in this class.

### **Models**
Contains classes representing the application's core entities.  
**Classes:**
- `Question`: Represents a quiz question, including the text, options, and correct answer.

### **Services**
Provides helper functions to support the main functionalities.  
**Classes:**
- `StudentService`: Interacts with the DatabaseHelper class to validate student credentials, save scores, and fetch questions from the database.

### **Resources**
Contains resources such as images used in the GUI.  
**Files:**
- Images for the GUI are stored in the `src/main/resources/icons` directory.

## **Setup Instructions**

### **Prerequisites**
- Install Java Development Kit (JDK) (Version 8 or later).
- Install NetBeans IDE or any preferred Java IDE.
- Install MySQL Community Server and MySQL Workbench for database management.

### **Steps to Set Up the Project**
1. Clone or download the project repository to your local machine.
2. Open the project in NetBeans IDE or your preferred Java IDE.
3. **Set up the database:**
   - Open MySQL Workbench and create a new schema named `quiz_db`.
   - The SQL commands to create the required tables (student, questions, and quiz_marks) are already included in the `DatabaseHelper` class. The tables will be created automatically when the application is run.
   - Populate the questions and student tables directly using MySQL Workbench.
4. **Configure the database connection:**
   - Open the `DatabaseHelper` class in the `Database` package.
   - Update the following lines with your MySQL database credentials:
     ```java
     String url = "jdbc:mysql://localhost:3306/quiz_db";
     String user = "your_username";
     String password = "your_password";
     ```
5. **Verify the inclusion of the MySQL Connector/J JAR file:**
   - The required JAR file is already included in the project folder. Ensure that it is correctly linked to the project in your IDE.
   - If you encounter any issues, follow these steps to re-add the JAR file:
     - Right-click on the project in NetBeans, go to Properties, and under Libraries, click **Add JAR/Folder**.
     - Navigate to the folder containing the JAR file, select it, and click **Open**.
6. **Build and run the project:**
   - Right-click on the project in NetBeans and select **Clean and Build**.
   - Run the main class (e.g., `Main.java`) to launch the application.

## **Usage**

### **Adding a Student**
This project currently does not include an option to add a student through the GUI. Students must be added directly to the student table in the database using MySQL Workbench or other database management tools. Future updates may include this feature for improved usability.

### **Taking a Quiz**
1. Log in using a valid registration number.
2. Select the desired quiz from the menu to start.
3. Answer the questions by selecting the correct options.
4. Submit the quiz to view the results.

### **Viewing Results**
After submitting the quiz, results are displayed on the `ResultScreen`.  
Marks are also stored in the `quiz_marks` table in the database.

## **Technologies Used**
- **Java**: Programming language for the application logic and GUI.
- **MySQL**: Database for storing application data.
- **Apache NetBeans**: IDE for project development.
- **JDBC**: Java Database Connectivity for MySQL communication.
- **Java Swing**: Used for creating the GUI with drag-and-drop functionality in NetBeans.

## **Future Enhancements**
- Adding user roles (e.g., admin, student).
- Expanding the variety of questions.
- Introducing a "Previous" button for quiz navigation.
- Enhancing security with hashed passwords for authentication.
- Implementing a leaderboard for top-performing students.
- Adding functionality to export results to PDF or CSV.
- **Adding a GUI for student management**: This feature is currently planned for future releases. Users will be able to manage student records through the application interface.

## **Contact**
For any queries or contributions, feel free to contact:  
**Name**: Ifra Ahmed  
**Email**: ifraahmed817@gmail.com
