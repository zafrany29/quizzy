package il.ac.hit.quizzy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleCSVQuizFilesDAOTest {

    private SimpleCSVQuizFilesDAO quizFilesDAO;
    private IQuiz quiz;
    private String testFileName = "testQuiz.csv";

    @Before
    public void setUp() {
        quizFilesDAO = (SimpleCSVQuizFilesDAO) SimpleCSVQuizFilesDAO.getInstance();

        // Creating a mock quiz for testing
        quiz = new TerminalQuiz();
        quiz.setName("Sample Quiz");

        IQuizQuestion question1 = new QuizQuestion.Builder()
                .setTitle("Math Question")
                .setQuestion("What is 2 + 2?")
                .addAnswer("3", false)
                .addAnswer("4", true)
                .addAnswer("5", false)
                .addAnswer("6", false)
                .addAnswer("7", false)
                .create();

        IQuizQuestion question2 = new QuizQuestion.Builder()
                .setTitle("Science Question")
                .setQuestion("What is H2O?")
                .addAnswer("Water", true)
                .addAnswer("Oxygen", false)
                .addAnswer("Hydrogen", false)
                .addAnswer("Helium", false)
                .addAnswer("Carbon", false)
                .create();

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
    }

    @After
    public void tearDown() {
        // Clean up test file after each test
        File file = new File(testFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveQuizToFile() {
        try {
            // Save the quiz to a CSV file
            quizFilesDAO.saveQuizToFile(quiz, testFileName);

            // Check if file is created
            File file = new File(testFileName);
            assertTrue("File should be created", file.exists());
        } catch (QuizException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testLoadQuizFromFile() {
        try {
            // Save the quiz to a CSV file first
            quizFilesDAO.saveQuizToFile(quiz, testFileName);

            // Load the quiz from the CSV file
            IQuiz loadedQuiz = quizFilesDAO.loadQuizFromFile(testFileName);

            // Validate that the loaded quiz is the same as the saved one
            assertEquals("Quiz name should match", quiz.getName(), loadedQuiz.getName());
            assertEquals("Quiz type should match", quiz.getType(), loadedQuiz.getType());
            assertEquals("Quiz should have the same number of questions", quiz.getQuestions().size(), loadedQuiz.getQuestions().size());

            List<IQuizQuestion> savedQuestions = quiz.getQuestions();
            List<IQuizQuestion> loadedQuestions = loadedQuiz.getQuestions();

            for (int i = 0; i < savedQuestions.size(); i++) {
                IQuizQuestion savedQuestion = savedQuestions.get(i);
                IQuizQuestion loadedQuestion = loadedQuestions.get(i);

                assertEquals("Question titles should match", savedQuestion.getTitle(), loadedQuestion.getTitle());
                assertEquals("Question texts should match", savedQuestion.getQuestion(), loadedQuestion.getQuestion());

                String[] savedAnswers = savedQuestion.getAnswers();
                String[] loadedAnswers = loadedQuestion.getAnswers();
                assertArrayEquals("Answers should match", savedAnswers, loadedAnswers);

                boolean[] savedCorrectAnswers = savedQuestion.getboolanswers();
                boolean[] loadedCorrectAnswers = loadedQuestion.getboolanswers();
                assertArrayEquals("Correct answers should match", savedCorrectAnswers, loadedCorrectAnswers);
            }
        } catch (QuizException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSingletonInstance() {
        // Ensure that the Singleton pattern is enforced
        IQuizFilesDAO anotherInstance = SimpleCSVQuizFilesDAO.getInstance();
        assertSame("Instances should be the same", quizFilesDAO, anotherInstance);
    }
}
