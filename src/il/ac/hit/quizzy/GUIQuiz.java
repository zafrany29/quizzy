package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class GUIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    private String type = "GUI";

    @Override
    public void start() {
        System.out.println("Starting GUI quiz: " + name);
        // Implement GUI-based quiz logic
        // The final score will be shown on the GUI instead of the terminal
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    @Override
    public List getQuestions() {
        return questions;
    }

    @Override
    public String getType() {
        return type;
    }
}
