package il.ac.hit.quizzy;

public interface IQuizQuestion {
    void display();
    String[] getAnswers();
    String getCorrectAnswer();
    boolean[] getboolanswers();
    String getTitle();
    String getQuestion();
}

