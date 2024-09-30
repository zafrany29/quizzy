//Omer Zafrany 318877420
//Lusil Grozdanov 208493122
//Elad Samuelov 314752643

import il.ac.hit.quizzy.*;

public class Main {

    public static void main(String argos[]) throws QuizException {
        //creating question
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.GUI);
        quiz.setName("Quiz Demo");
        //creating 1st question
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("We Love Canada");
        builder1.setQuestion("Canada starts with…?");
        builder1.addAnswer("Canada starts with the letter ‘A’.",false);
        builder1.addAnswer("Canada starts with the letter ‘B’.",false);
        builder1.addAnswer("Canada starts with the letter ‘C’.",true);
        builder1.addAnswer("Canada starts with the letter ‘D’.",false);
        builder1.addAnswer("Canada starts with the letter ‘E’.",false);
        IQuizQuestion question1 = builder1.create();
        //creating 2nd question
        IQuizQuestionBuilder builder2 = new QuizQuestion.Builder();
        builder2.setTitle("We Love Australia");
        builder2.setQuestion("Australia starts with…?");
        builder2.addAnswer("Australia starts with the letter ‘A’.",true);
        builder2.addAnswer("Australia starts with the letter ‘B’.",false);
        builder2.addAnswer("Australia starts with the letter ‘C’.",false);
        builder2.addAnswer("Australia starts with the letter ‘D’.",false);
        builder2.addAnswer("Australia starts with the letter ‘E’.",false);
        IQuizQuestion question2 = builder2.create();

        //adding questions to quiz
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        //saving quiz to file and read it back
        IQuizFilesDAO dao = SimpleCSVQuizFilesDAO.getInstance();
        dao.saveQuizToFile(quiz,"quiz1.data");
        IQuiz loadedQuiz = dao.loadQuizFromFile("quiz1.data");
        loadedQuiz.start();
    }

}