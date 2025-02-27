package com.example.demo.service;

import com.example.demo.entity.UserSubject;
import com.example.demo.repository.UserSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSubjectService {

    @Autowired
    private UserSubjectRepository userRepository;

    public List<UserSubject> getAllUserSubjects() {
        return userRepository.findAll();
    }

    public List<UserSubject> getUserSubjectsByName(String name) {
        System.out.println("getUserSubjectsByName: " + name);
        return userRepository.findByName(name);
    }

    public int saveUserSubject(UserSubject user) {
        return userRepository.insert(user);
    }
}