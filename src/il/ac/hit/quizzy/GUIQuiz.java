//Omer Zafrany 318877420
//Lusil Grozdanov 208493122
//Elad Samuelov 314752643
package il.ac.hit.quizzy;

import il.ac.hit.quizzy.IQuiz;
import il.ac.hit.quizzy.IQuizQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    private String type = "GUI";
    private int currentQuestionIndex = 0;

    @Override
    public void start() {
        // Set up the frame (window)
        JFrame frame = new JFrame("Quiz: " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel for displaying questions and answers
        JPanel panel = new JPanel(new GridLayout(6, 1));
        JLabel questionLabel = new JLabel();
        panel.add(questionLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton[] answerButtons = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {
            answerButtons[i] = new JRadioButton();
            buttonGroup.add(answerButtons[i]);
            panel.add(answerButtons[i]);
        }

        JButton nextButton = new JButton("Next");
        panel.add(nextButton);

        frame.add(panel);
        frame.setVisible(true);

        // Display the first question
        showQuestion(questionLabel, answerButtons, currentQuestionIndex);

        // Action listener for the "Next" button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestionIndex < questions.size()) {
                    // Check if the selected answer is correct
                    if (answerButtons[getCorrectAnswerIndex(currentQuestionIndex)].isSelected()) {
                        score++;
                    }
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        showQuestion(questionLabel, answerButtons, currentQuestionIndex);
                    } else {
                        // End of quiz, show score
                        JOptionPane.showMessageDialog(frame, "Quiz ended! Your score: " + score + "/" + questions.size());
                        frame.dispose(); // Close the frame
                    }
                }
            }
        });
    }

    private void showQuestion(JLabel questionLabel, JRadioButton[] answerButtons, int questionIndex) {
        IQuizQuestion question = questions.get(questionIndex);
        questionLabel.setText(question.getQuestion());
        String[] answers = question.getAnswers();
        for (int i = 0; i < 5; i++) {
            answerButtons[i].setText(answers[i]);
        }
    }

    private int getCorrectAnswerIndex(int questionIndex) {
        boolean[] correctAnswers = questions.get(questionIndex).getboolanswers();
        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i]) {
                return i;
            }
        }
        return -1; // should never happen since there's always one correct answer
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
