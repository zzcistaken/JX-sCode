package com.example.demo.entity;

import java.util.List;
import java.util.Map;

public class Demo1Res {

    private String groupLength;
    private List<Part> parts;
    private List<PartRes> res;

    public Demo1Res() {

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

    public List<PartRes> getRes() {
        return res;
    }

    public void setRes(List<PartRes> res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Demo1Res{" +
                "groupLength='" + groupLength + '\'' +
                ", parts=" + parts +
                ", res=" + res +
                '}';
    }
}
