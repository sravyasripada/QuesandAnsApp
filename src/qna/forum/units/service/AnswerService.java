package qna.forum.units.service;

import org.app.units.Answer;
import org.app.units.Question;
import org.app.units.User;
import java.util.ArrayList;

public interface AnswerService {

    Answer createAnswer(String answerBody, User user);

    Answer getAnswer(int id);

    ArrayList<Answer> getAnswers(Question question);

    void deleteAnswer(Answer answer);
}
