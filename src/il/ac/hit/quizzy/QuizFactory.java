package il.ac.hit.quizzy;

public class QuizFactory {
    public IQuiz createQuiz(QuizType type) {
        switch (type) {
            case TERMINAL:
                return new TerminalQuiz();
            case GUI:
                return new GUIQuiz();
            default:
                throw new IllegalArgumentException("Unknown quiz type: " + type);
        }
    }
}
