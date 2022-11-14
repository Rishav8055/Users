package com.niit.Users.Repository;

import com.niit.Users.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

 public User findByUserNameAndPassword(String userName,String password);
}
