//Omer Zafrany 318877420
//Lusil Grozdanov 208493122
//Elad Samuelov 314752643
package il.ac.hit.quizzy;

public interface IQuizFilesDAO {
    public abstract void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException;
    public abstract IQuiz loadQuizFromFile(String fileName) throws QuizException;
}
