//Omer Zafrany 318877420
//Lusil Grozdanov 208493122
//Elad Samuelov 314752643
package il.ac.hit.quizzy;

public interface IQuizQuestion {
    void display();
    String[] getAnswers();
    String getCorrectAnswer();
    boolean[] getboolanswers();
    String getTitle();
    String getQuestion();
}

