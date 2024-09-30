//Omer Zafrany 318877420
//Lusil Grozdanov 208493122
//Elad Samuelov 314752643
package il.ac.hit.quizzy;

import java.util.List;

public interface IQuiz {
    public abstract void start();
    public abstract void setName(String text);
    public abstract String getName();
    public abstract void addQuestion(IQuizQuestion question);
    public abstract List getQuestions();
    public  abstract  String getType();
}
