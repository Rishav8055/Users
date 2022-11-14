package com.niit.Users.Controller;

import com.niit.Users.Domain.User;
import com.niit.Users.Exception.UserNotFoundException;
import com.niit.Users.Service.SecurityTokenGenerator;
import com.niit.Users.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private ResponseEntity responseEntity;
    private SecurityTokenGenerator securityTokenGenerator;
    private UserService userService;

    public UserController(SecurityTokenGenerator securityTokenGenerator, UserService userService) {
        this.securityTokenGenerator = securityTokenGenerator;
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
    User createdUser=userService.saveUser(user);
    return responseEntity=new ResponseEntity<>("user created", HttpStatus.CREATED);
    }
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException{
        Map<String,String> map=null;
        try {
            User user1=userService.findByUserNameAndPassword(user.getUserName(),user.getPassword());
            if (user1.getUserName().equals(user.getUserName())){
            map=securityTokenGenerator.generateToken(user);
            }
            responseEntity=new ResponseEntity(map,HttpStatus.OK);

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>("Try after sometime !!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/api/v1/userservice/users")
    public ResponseEntity<?> getAllUsers() throws UserNotFoundException{
        List<User> list =userService.getAllUser();
        responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("/api/v1/userservice/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") int userId) throws UserNotFoundException {
        ResponseEntity responseEntity =null;
        try {
            userService.deleteUser(userId);
            responseEntity = new ResponseEntity("Successfully deleted the 1 record",HttpStatus.OK);
        }
        catch (UserNotFoundException cnfe){
            throw  new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
