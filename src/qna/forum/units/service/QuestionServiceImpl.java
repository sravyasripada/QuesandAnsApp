package qna.forum.units.service;

import org.app.units.Question;
import org.app.units.User;

import java.util.ArrayList;

public class QuestionServiceImpl implements QuestionService {

    public static ArrayList<Question> questions=new ArrayList<>();

     public Question createQuestion(String questionBody, User user){

        Question question=new Question();
        question.autoGenerateId();
        question.setQuestionBody(questionBody);
        question.setUser(user);
        questions.add(question);
        //Confirmation message to user after a question is created
        System.out.println("Question : "+question.getQuestionBody()+" created .");
        return question;
    }

     public Question getQuestionById( int id){
        for (Question question : questions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    public void deleteQuestion(Question question) {
        questions.remove(question);
        //Gives a confirmation to the user that a question is deleted
        System.out.println("Question :" + question.getQuestionBody() + " deleted");
    }
}
