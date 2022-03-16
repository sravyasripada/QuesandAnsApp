package qna.forum.units.service;

import org.app.units.Question;
import org.app.units.User;

public interface QuestionService {

    Question createQuestion(String questionBody, User user);

    Question getQuestionById( int id);

    void deleteQuestion(Question question);

}
