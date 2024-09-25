package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TerminalQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    private String type = "Terminal";


    @Override
    public void start() {
        System.out.println("Starting terminal quiz: " + name);
        for (IQuizQuestion question : questions) {
            question.display();
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals(question.getCorrectAnswer())) {
                score++;
            }
        }
        System.out.println("Quiz ended! Your score: " + score + "/" + questions.size());
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
