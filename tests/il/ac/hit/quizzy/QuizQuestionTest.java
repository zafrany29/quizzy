package il.ac.hit.quizzy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuizQuestionTest {

    private IQuizQuestionBuilder builder;

    @Before
    public void setUp() {
        // Initialize the builder before each test
        builder = new QuizQuestion.Builder();
    }

    @Test
    public void testQuizQuestionCreation() {
        // Build the question with the builder
        builder.setTitle("We Love Australia");
        builder.setQuestion("Australia starts with…?");
        builder.addAnswer("Australia starts with the letter ‘A’.", true);
        builder.addAnswer("Australia starts with the letter ‘B’.", false);
        builder.addAnswer("Australia starts with the letter ‘C’.", false);
        builder.addAnswer("Australia starts with the letter ‘D’.", false);
        builder.addAnswer("Australia starts with the letter ‘E’.", false);

        IQuizQuestion question = builder.create();

        // Validate title, question text, and answers
        assertEquals("We Love Australia", question.getTitle());
        assertEquals("Australia starts with…?", question.getQuestion());

        String[] expectedAnswers = {
                "Australia starts with the letter ‘A’.",
                "Australia starts with the letter ‘B’.",
                "Australia starts with the letter ‘C’.",
                "Australia starts with the letter ‘D’.",
                "Australia starts with the letter ‘E’.",
        };
        assertArrayEquals(expectedAnswers, question.getAnswers());

        // Validate the correct answer
        assertEquals("Australia starts with the letter ‘A’.", question.getCorrectAnswer());

        // Validate the boolean array for correct answers
        boolean[] expectedCorrectAnswers = { true, false, false, false, false };
        assertArrayEquals(expectedCorrectAnswers, question.getboolanswers());
    }

    @Test
    public void testSettingAndGettingTitle() {
        // Directly test the builder for setting and getting the title
        builder.setTitle("What is the best city in the world?");
        assertEquals("What is the best city in the world?", builder.getTitle());

        // Ensure title is correctly set when creating the question
        IQuizQuestion question = builder.create();
        assertEquals("What is the best city in the world?", question.getTitle());
    }

    @Test
    public void testSettingAndGettingQuestionText() {
        // Directly test the builder for setting and getting the question text
        builder.setQuestion("What is the largest planet?");
        assertEquals("What is the largest planet?", builder.create().getQuestion());

        IQuizQuestion question = builder.create();
        assertEquals("What is the largest planet?", question.getQuestion());
    }
}
