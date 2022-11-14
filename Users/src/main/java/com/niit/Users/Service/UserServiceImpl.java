package com.niit.Users.Service;

import com.niit.Users.Domain.User;
import com.niit.Users.Exception.UserNotFoundException;
import com.niit.Users.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        User user = userRepo.findByUserNameAndPassword(userName, password);
        if (user == null) {
            throw new UserNotFoundException();

        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException {
        if (userRepo.findById(userId).isEmpty()) {
            throw new UserNotFoundException();
        } else {
            userRepo.deleteById(userId);
            return true;

        }
    }
}
