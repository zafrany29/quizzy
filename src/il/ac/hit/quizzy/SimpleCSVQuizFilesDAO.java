package il.ac.hit.quizzy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SimpleCSVQuizFilesDAO  implements IQuizFilesDAO {
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
            writer.println(quiz.getName()); // Write the quiz name
            writer.println(quiz.getType());

            // Serialize each question
            List<IQuizQuestion> questions = quiz.getQuestions();
            for (int j = 0; j < quiz.getQuestions().size(); j++) {         //QuizQuestion question : quiz.getQuestions().size()) {
                writer.println(questions.get(j).getTitle()); // Write question title
                writer.println(questions.get(j).getQuestion()); // Write question text

                // Serialize each answer
                String[] currq = questions.get(j).getAnswers();
                boolean[] currect = questions.get(j).getboolanswers();
                for (int i = 0; i < 5; i++) {
                    writer.println(currq[i] + "," + currect[i]); // Write answer text and correctness
                }
                writer.println(); // Separate questions with a blank line
            }
        } catch (IOException e) {
            //throw new QuizException("Error saving quiz to file: " + e.getMessage(), e);
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String quizName = reader.readLine();
            String quizType = reader.readLine();
            IQuiz quiz;
            if (quizType == "GUI") {
                quiz = new GUIQuiz();
            } else {
                quiz = new TerminalQuiz();
            }

            quiz.setName(quizName);

            String line;
            IQuizQuestionBuilder builder = new QuizQuestion.Builder();
            List<IQuizQuestion> questions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    // End of current question
                    questions.add(builder.create());
                    builder = new QuizQuestion.Builder();
                } else {
                    String[] parts = line.split(",", 2);
                    if (parts.length == 2) {
                        // Handle answer line
                        builder.addAnswer(parts[0], Boolean.parseBoolean(parts[1]));
                    } else if (builder.getTitle() == null) {
                        builder.setTitle(parts[0]);
                    } else {
                        builder.setQuestion(parts[0]);
                    }
                }
            }
            // Add the last question if there is any
            if (builder.getTitle() != null) {
                questions.add(builder.create());
            }

            // Add all questions to the quiz
            for (IQuizQuestion question : questions) {
                quiz.addQuestion(question);
            }

            return quiz;
        } catch (IOException e) {
            return null;
            //throw new QuizException("Error loading quiz from file: " + e.getMessage(), e);
        }
    }
}
