package il.ac.hit.quizzy;

import org.junit.Before;
import org.junit.Test;
import il.ac.hit.quizzy.QuizQuestion;
import il.ac.hit.quizzy.TerminalQuiz;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class TerminalQuizTest {

    private TerminalQuiz terminalQuiz;
    private QuizQuestion q;

    @Before
    public void setUp() {
        // Initialize the TerminalQuiz instance
        terminalQuiz = new TerminalQuiz();
    }

    @Test
    public void testSetName() {
        terminalQuiz.setName("Sample Quiz");
        assertEquals("Sample Quiz", terminalQuiz.getName());
    }

    @Test
    public void testAddQuestion() {
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("We Love Canada");
        builder1.setQuestion("Canada starts with…?");
        builder1.addAnswer("Canada starts with the letter ‘A’.",false);
        builder1.addAnswer("Canada starts with the letter ‘B’.",false);
        builder1.addAnswer("Canada starts with the letter ‘C’.",true);
        builder1.addAnswer("Canada starts with the letter ‘D’.",false);
        builder1.addAnswer("Canada starts with the letter ‘E’.",false);
        IQuizQuestion question1 = builder1.create();
        terminalQuiz.addQuestion(question1);

        List <QuizQuestion> q = terminalQuiz.getQuestions();
        String whatisquestion = q.getFirst().getQuestion();
        String Whatistitle = q.getFirst().getTitle();


        assertEquals(1, terminalQuiz.getQuestions().size());
        assertEquals("Canada starts with…?", whatisquestion);
        assertEquals("We Love Canada", Whatistitle);
    }

    @Test
    public void testGetType() {
        assertEquals("Terminal", terminalQuiz.getType());
    }

}
