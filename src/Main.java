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

        QuizFactory factory2 = new QuizFactory();
        IQuiz quiz2 = factory2.createQuiz(QuizType.TERMINAL);
        quiz2.setName("My Quizz");
        //creating 1st question
        IQuizQuestionBuilder builder3 = new QuizQuestion.Builder();
        builder3.setTitle("We Love Canada");
        builder3.setQuestion("Canada starts with…?");
        builder3.addAnswer("A",false);
        builder3.addAnswer("B",false);
        builder3.addAnswer("C",true);
        builder3.addAnswer("D",false);
        builder3.addAnswer("E",false);
        IQuizQuestion question3 = builder3.create();
        //creating 2nd question
        IQuizQuestionBuilder builder4 = new QuizQuestion.Builder();
        builder4.setTitle("We Love Australia");
        builder4.setQuestion("Australia starts with…?");
        builder4.addAnswer("A",true);
        builder4.addAnswer("B",false);
        builder4.addAnswer("C",false);
        builder4.addAnswer("D",false);
        builder4.addAnswer("E",false);
        IQuizQuestion question4 = builder4.create();

        //adding questions to quiz
        quiz2.addQuestion(question3);
        quiz2.addQuestion(question4);
        //saving quiz to file and read it back
        IQuizFilesDAO dao2 = SimpleCSVQuizFilesDAO.getInstance();
        dao2.saveQuizToFile(quiz2,"quiz2.data");
        IQuiz loadedQuiz2 = dao2.loadQuizFromFile("quiz2.data");
        loadedQuiz2.start();
    }

}