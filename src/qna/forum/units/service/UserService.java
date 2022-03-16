package qna.forum.units.service;

import org.app.units.User;
import org.app.units.UserRole;

public interface UserService {

     User createUser(String userName, String password, UserRole userRole);

     User getUser(String userName, String password );

}
