package org.app.main;

import org.app.units.Question;
import org.app.units.User;
import org.app.units.UserRole;
import org.app.util.Utility;
import qna.forum.activity.UserActivity;

import java.io.IOException;

public class QnAapp {

    public static void main(String args[]) {
        User user;
        UserActivity userActivity = new UserActivity();
        userActivity.userService.createUser("admin", "admin", UserRole.ADMIN);
        while (true) {
            user = userActivity.loginActivity();
            if (user == null)
                continue;
            System.out.println("Welcome " + user.getUserName());
            menu(user, userActivity);
        }
    }

    //try-catch block included to catch the expected exceptions
    public static void menu(User user, UserActivity userActivity) {
        while (true) {
            int menuIndex = 0;
            if (user.getUserRole() == UserRole.ADMIN) {
                System.out.println(++menuIndex + " Create new user");
            }
            System.out.println(++menuIndex + " Ask a question");
            System.out.println(++menuIndex + " See all questions");
            System.out.println(++menuIndex + " Log Out");
            System.out.println("\n Enter your choice");
            try {
                if (!classifyMenuChoice(Integer.parseInt(Utility.inputFromUser()), userActivity, user))
                    break;
            }catch(NumberFormatException e1){
                System.out.println("Please enter a valid integer as choice");
            }catch(IOException e2){
                System.out.println("IO Exception thrown.Please try again");
            }

        }
    }
    //Entered choice by the user in menu calls the respective methods
    public static boolean classifyMenuChoice(int choice, UserActivity userActivity, User user) throws IOException {
        if (UserRole.ADMIN != user.getUserRole()) {
            choice++;
        }
        while (true) {
            switch (choice) {
                case 1:
                    userActivity.createNewUser();
                    return true;
                case 2:
                    userActivity.postNewQuestion(user);
                    return true;
                case 3:
                    userActivity.seeAllQuestions(userActivity, user);
                    return true;
                case 4:
                    return false;
                default:
                    System.out.println("Wrong choice. Try again");
            }
            System.out.println("\n Enter your choice");
            choice = Integer.parseInt(Utility.inputFromUser());
            if (UserRole.ADMIN != user.getUserRole()) {
                choice++;
            }
        }
    }
    public static UserRole roleFromMenu() throws NumberFormatException {
        int menuIndex = 0;
        System.out.println(++menuIndex + UserRole.ADMIN.toString());
        System.out.println(++menuIndex + UserRole.END_USER.toString());
        while (true) {
            System.out.println("\n Enter your choice");
            int choice = Integer.parseInt(Utility.inputFromUser());
            switch (choice) {
                case 1:
                    return UserRole.ADMIN;
                case 2:
                    return UserRole.END_USER;
                default:
                    System.out.println("Wrong choice. Try again");
            }
        }
    }

    public static void questionMenu(UserActivity userActivity, User user) throws NumberFormatException, IOException {
        while (true) {
            int menuIndex = 0;
            System.out.println(++menuIndex + " Reply to a question");
            System.out.println(++menuIndex + " See replies for a question");
            System.out.println(++menuIndex + " Delete a question");
            System.out.println(++menuIndex + " Return to main menu");
            System.out.println("\n Enter your choice");
            if (!processQuestionChoice(Integer.parseInt(Utility.inputFromUser()), userActivity, user)) {
                break;
            }
        }
    }
    //calls respective methods according to user choice in question menu
    public static boolean processQuestionChoice(int choice, UserActivity userActivity, User user) throws NumberFormatException, IOException {
        while (true) {
            switch (choice) {
                case 1:
                    userActivity.replyToQuestion(user);
                    return true;
                case 2:
                    userActivity.seeAllReplies(userActivity, user);
                    return true;
                case 3:
                    userActivity.deleteQuestion(userActivity, user);
                    return true;
                case 4:
                    return false;
                default:
                    System.out.println("Wrong choice. Try again");
            }
            System.out.println("Enter your choice");
            choice = Integer.parseInt(Utility.inputFromUser());
        }
    }

    public static void replyMenu(UserActivity userActivity, User user, Question question) throws NumberFormatException, IOException {
        while (true) {
            int menuIndex = 0;
            System.out.println(++menuIndex + " Delete a reply");
            System.out.println(++menuIndex + " Return to question menu");
            System.out.println("\n Enter your choice");
            if (!processReplyChoice(Integer.parseInt(Utility.inputFromUser()), userActivity, user, question)) {
                break;
            }
        }
    }
    //calls respective methods according to user choice in reply menu
    public static boolean processReplyChoice(int choice, UserActivity userActivity, User user, Question question) throws NumberFormatException, IOException {
        while (true) {
            switch (choice) {
                case 1:
                    userActivity.deleteReply(question, userActivity, user);
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("Wrong choice. Try again");
            }
        }
    }


}
