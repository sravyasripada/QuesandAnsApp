package qna.forum.units.service;

import org.app.units.Answer;
import org.app.units.Question;
import org.app.units.User;

import java.util.ArrayList;

public class AnswerServiceImpl implements AnswerService{

    public static ArrayList<Answer> answers=new ArrayList<>();

    public Answer createAnswer(String answerBody, User user){
        Answer answer=new Answer();
        answer.setAnswerBody(answerBody);
        answer.setUser(user);
        answer.autoGenerateId();
        answers.add(answer);
        //Confirmation message that answer created
        System.out.println(" Answer :"+answer.getAnswerBody()+ "created .");
        return answer;
    }

    public Answer getAnswer(int id){
        for (Answer answer : answers) {
            if (answer.getId() == id) {
                return answer;
            }
        }
        return null;
    }

    public ArrayList<Answer> getAnswers(Question question){
        ArrayList<Answer> repliesToQuestion = new ArrayList<>();
        for (Answer answer : answers) {
            if (answer.getQuestion() == question) {
                repliesToQuestion.add(answer);
            }
        }
        return repliesToQuestion;
    }

    public void deleteAnswer(Answer answer) {
        answers.remove(answer);
        //Gives a confirmation to the user that a answer has been deleted
        System.out.println("Answer :"+ answer.getAnswerBody()+" deleted");
    }
}
