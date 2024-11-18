package com.lcwd.user.service.UserService.service;

import com.lcwd.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //Create
    User saveUser(User user);

    //Get All User
    List<User> getAllUser();


    //Get User By Id
    User getUserById(String userId);

    //delete user by Id
    User deleteByUserId(String userId);

    //update user
    List<User> updateUser(List<User> user);
}
