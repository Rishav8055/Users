package com.niit.Users.Service;

import com.niit.Users.Domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
