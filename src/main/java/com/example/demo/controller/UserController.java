package com.example.demo.controller;

import com.example.demo.dto.Result;
import com.example.demo.dto.UserRes;
import com.example.demo.entity.User;
import com.example.demo.entity.UserSubject;
import com.example.demo.service.UserService;
import com.example.demo.service.UserSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSubjectService userSubjectService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/addUser")
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PostMapping(value = "/queryUserSubjectByName")
    public Result queryUserSubjectByName(@RequestBody User user) {
        System.out.println("queryUserSubjectByName user : " + user);
        List<UserSubject> list = userSubjectService.getUserSubjectsByName(user.getName());
        UserRes userRes = new UserRes();
        userRes.setCount(list.size());
        userRes.setUserSubjectList(list);
        return Result.ok(userRes);
    }

    @GetMapping(value = "/deleteUser")
    public Result deleteUser(Integer userId){
        System.out.println("delete userId : " + userId);
        return Result.ok();
    }

    @GetMapping("/users")
    public Result getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);

        List<User> userList = userService.getAllUsers();
        List<User> userListLimit = new ArrayList<>();
        int start = (page - 1) * limit;
        int count = 0;
        for(int i=0; i<userList.size();i++) {
            //start
            if(i >= start) {
                count++;
                userListLimit.add(userList.get(i));
            }
            if(count >= limit) {
                break;
            }

        }
        UserRes userRes = new UserRes();
        userRes.setCount(userList.size());
        userRes.setUserList(userListLimit);
        System.out.println(userRes.toString());
        return Result.ok(userRes);
    }
}