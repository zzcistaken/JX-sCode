package com.example.demo.entity;

import java.util.List;

public class User {

    private Long id;

    private String name;
    private String email;

    private List<Hbjh> hbjhList;


    public User() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Hbjh> getHbjhList() {
        return hbjhList;
    }

    public void setHbjhList(List<Hbjh> hbjhList) {
        this.hbjhList = hbjhList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hbjhList=" + hbjhList +
                '}';
    }
}