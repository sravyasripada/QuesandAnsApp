package qna.forum.activity;

import org.app.main.QnAapp;
import org.app.units.Answer;
import org.app.units.Question;
import org.app.units.User;
import org.app.units.UserRole;
import org.app.util.Utility;
import qna.forum.units.service.*;

import java.io.IOException;
import java.util.ArrayList;

//UserActivity divided into categories and creating variables of the respective interfaces type as defined
public class UserActivity {

    public UserService userService;
    public QuestionService questionService;
    public AnswerService answerService;

    //Constructor of userActivity to instantiate to respective objects of the classes which implemented the interfaces
    public UserActivity() {
        userService = new UserServiceImpl();
        questionService = new QuestionServiceImpl();
        answerService = new AnswerServiceImpl();
    }

    public User loginActivity() {
        System.out.println("Welcome to Q & A app login !! ");
        System.out.println("Enter your username : ");
        String userName = Utility.inputFromUser();
        System.out.println("Enter your password : ");
        String password = Utility.inputFromUser();

        User user = userService.getUser(userName, password);
        if (user != null) {
            return user;
        }
        System.out.println("You do not have the account. Please contact admin. ");
        return null;
    }

    public void createNewUser() {
        System.out.println("Enter username : ");
        String username = Utility.inputFromUser();
        System.out.println("Enter password : ");
        String password = Utility.inputFromUser();
        System.out.println("What role : ");
        UserRole role = QnAapp.roleFromMenu();
        userService.createUser(username, password, role);
    }

    public void postNewQuestion(User user) {
        System.out.println("Enter question body : ");
        String questionBody = Utility.inputFromUser();
        questionService.createQuestion(questionBody, user);
    }

    public void seeAllQuestions(UserActivity userActivity, User user) throws NumberFormatException, IOException
    {

        ArrayList<Question> questions= QuestionServiceImpl.questions;
        if (questions.size() == 0) {
            System.out.println("No Questions posted yet");
        } else {
            for (Question question : questions) {
                System.out.println(question.getId() + ".Question - " + question.getQuestionBody());
            }
            QnAapp.questionMenu(userActivity, user);//sending the control to reply menu
        }
    }

    public void replyToQuestion(User user) {
        System.out.println("Enter question number you want to reply to : ");
        Question question = getQuestion();
        //Displaying question details so that user can answer
        System.out.println("Question details :" + question.getQuestionBody());

        System.out.println("Enter your answer");
        answerService.createAnswer(Utility.inputFromUser(), user);
    }

    public void deleteQuestion(UserActivity userActivity, User user) throws NumberFormatException, IOException {
        System.out.println("Enter Question Which number you want to delete : ");
        Question question = getQuestion();
//Allows only authorised persons to delete the replies
        if (user.getUserRole() == UserRole.ADMIN) {
            questionService.deleteQuestion(question);
        }  else {
            if (question.getUser() == user) {
                questionService.deleteQuestion(question);
            } else {
                System.out.println("You are not authorised to delete this Question");
            }
        }
        if (answerService.getAnswers(   question).size() == 0)
            QnAapp.questionMenu(userActivity, user);//sending control to question menu


    }

    private Question getQuestion() {
        Question question;
        while (true) {
            question = questionService.getQuestionById(Integer.parseInt(Utility.inputFromUser()));
            if (question != null)
                break;
            System.out.println("Enter correct question from displayed questions");
        }
        return question;
    }

    public void seeAllReplies(UserActivity userActivity, User user) throws NumberFormatException, IOException {
        System.out.println("For which question number you want to see replies : ");
        Question question = getQuestion();
        ArrayList<Answer> answers = answerService.getAnswers(question);
        if (answers.size() == 0) {
            System.out.println("No answer posted yet");
        } else {
            for (Answer answer : answers) {
                System.out.println(answer.getId() + ". Answer - " + answer.getAnswerBody());
            }
            QnAapp.replyMenu(userActivity, user, question);//sending the control to reply menu
        }
    }

    public void deleteReply(Question question, UserActivity userActivity, User user) throws NumberFormatException, IOException {
        System.out.println("Enter answer number you want to delete : ");
        Answer answer = getAnswer();
//Allows only authorised persons to delete the replies
        if (user.getUserRole() == UserRole.ADMIN) {
            answerService.deleteAnswer(answer);
        }  else {
            if (answer.getUser() == user) {
                answerService.deleteAnswer(answer);
            } else {
                System.out.println("You are not authorised to delete this answer");
            }
        }
        if (answerService.getAnswers(   question).size() == 0)
            QnAapp.questionMenu(userActivity, user);//sending control to question menu

    }

    private Answer getAnswer() throws NumberFormatException {
        Answer answer;
        while (true) {
            answer = answerService.getAnswer(Integer.parseInt(Utility.inputFromUser()));
            if (answer != null)
                break;
            System.out.println("Enter correct answer from displayed answers");
        }
        return answer;
    }


}
