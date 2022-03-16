package org.app.units;

public class Answer extends AbstractEntity {

    private String answerBody;
    private Question question;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody)
    {
        this.answerBody = answerBody;

    }


     int id=0;
    @Override
    public void autoGenerateId()
    {
        id++;
        super.setId(id);
    }
}
