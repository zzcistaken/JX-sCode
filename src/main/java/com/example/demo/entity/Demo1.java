package com.example.demo.entity;

import java.util.List;

public class Demo1 {

    private String groupLength;
    private List<Part> parts;

    public Demo1 () {

    }

    public String getGroupLength() {
        return groupLength;
    }

    public void setGroupLength(String groupLength) {
        this.groupLength = groupLength;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "Demo1{" +
                "groupLength='" + groupLength + '\'' +
                ", parts=" + parts +
                '}';
    }
}
