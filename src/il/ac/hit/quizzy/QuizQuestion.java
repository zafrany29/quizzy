//Omer Zafrany 318877420
//Lusil Grozdanov 208493122
//Elad Samuelov 314752643
package il.ac.hit.quizzy;

public class QuizQuestion implements IQuizQuestion {

    private String title;
    private String question;
    private String[] answers = new String[5];
    private boolean[] correctAnswers = new boolean[5];
    private String correctAnswer;

    protected QuizQuestion(Builder builder) {
        this.title = builder.title;
        this.question = builder.question;
        this.answers = builder.answers;
        this.correctAnswers = builder.correctAnswers;
    }

    @Override
    public void display() {
        System.out.println(question);
    }

    @Override
    public String[] getAnswers() {
        return answers;
    }

    @Override
    public String getCorrectAnswer() {
        for (int i = 0; i<correctAnswers.length; i++) {
            if (correctAnswers[i])
            {
                correctAnswer=answers[i];
            }
        }
        return correctAnswer;
    }

    @Override
    public boolean[] getboolanswers() {
        return correctAnswers;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    public static class Builder implements IQuizQuestionBuilder {
        private String title;
        private String question;
        private String[] answers = new String[5];
        private boolean[] correctAnswers = new boolean[5];
        private int answerIndex = 0;

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            this.title = text;
            return this;
        }

        @Override
        public String getTitle() {
            return title;
        }


        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            this.question = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            if (answerIndex < 5) {
                this.answers[answerIndex] = text;
                this.correctAnswers[answerIndex] = correct;
                answerIndex++;
            }
            return this;
        }

        @Override
        public IQuizQuestion create() {
            return new QuizQuestion(this);
        }
    }
}
