// Omer Zafrany 318877420
// Lusil Grozdanov 208493122
// Elad Samuelov 314752643

package il.ac.hit.quizzy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {
    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO() {
        // private constructor for Singleton
    }

    public static IQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(quiz.getName());
            writer.println(quiz.getType().toUpperCase());

            List<IQuizQuestion> questions = quiz.getQuestions();
            for (IQuizQuestion question : questions) {
                writer.println(question.getTitle()); // Write question title
                writer.println(question.getQuestion()); // Write question text

                // Serialize each answer
                String[] answers = question.getAnswers();
                boolean[] correct = question.getboolanswers();
                for (int i = 0; i < answers.length; i++) {
                    writer.println(answers[i] + "," + correct[i]);
                }
                writer.println(); // Separate questions with a blank line
            }
        } catch (IOException e) {
            throw new QuizException("Error saving quiz to file: " + e.getMessage());
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read quiz name and type
            String quizName = reader.readLine();
            String quizType = reader.readLine().toUpperCase();

            IQuiz quiz;
            if (quizType.equals("GUI")) {
                quiz = new GUIQuiz();
            } else if (quizType.equals("TERMINAL")) {
                quiz = new TerminalQuiz();
            } else {
                throw new QuizException("Unknown quiz type: " + quizType);
            }

            quiz.setName(quizName);

            // Parse the questions
            String line;
            IQuizQuestionBuilder builder = new QuizQuestion.Builder();
            List<IQuizQuestion> questions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    // End of current question
                    questions.add(builder.create());
                    builder = new QuizQuestion.Builder(); // Reset the builder for the next question
                } else {
                    String[] parts = line.split(",", 2);
                    if (parts.length == 2) {
                        // It's an answer line
                        builder.addAnswer(parts[0], Boolean.parseBoolean(parts[1]));
                    } else if (builder.getTitle() == null) {
                        // It's the title of the question
                        builder.setTitle(line);
                    } else {
                        // It's the question text
                        builder.setQuestion(line);
                    }
                }
            }
            if (builder.getTitle() != null) {
                questions.add(builder.create());
            }

            // Add all questions to the quiz
            for (IQuizQuestion question : questions) {
                quiz.addQuestion(question);
            }

            return quiz;
        } catch (IOException e) {
            throw new QuizException("Error loading quiz from file: " + e.getMessage());
        }
    }
}
