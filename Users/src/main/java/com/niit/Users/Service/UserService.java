package com.niit.Users.Service;

import com.niit.Users.Domain.User;
import com.niit.Users.Exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public User findByUserNameAndPassword(String userName,String password) throws UserNotFoundException;
    List<User>getAllUser();
    boolean deleteUser(int userId)throws UserNotFoundException;
}
