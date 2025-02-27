package com.example.demo.dto;

import com.example.demo.entity.User;
import com.example.demo.entity.UserSubject;

import java.util.List;

public class UserRes {

    private int count;
    private List<User> userList;
    private List<UserSubject> userSubjectList;

    public UserRes() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<UserSubject> getUserSubjectList() {
        return userSubjectList;
    }

    public void setUserSubjectList(List<UserSubject> userSubjectList) {
        this.userSubjectList = userSubjectList;
    }

    @Override
    public String toString() {
        return "UserRes{" +
                "count=" + count +
                ", userList=" + userList +
                ", userSubjectList=" + userSubjectList +
                '}';
    }
}
