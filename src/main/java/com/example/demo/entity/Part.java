package com.example.demo.entity;

public class Part {

    private String singleLength;
    private String singleCount;

    public Part() {

    }

    public String getSingleLength() {
        return singleLength;
    }

    public void setSingleLength(String singleLength) {
        this.singleLength = singleLength;
    }

    public String getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(String singleCount) {
        this.singleCount = singleCount;
    }

    @Override
    public String toString() {
        return "Part{" +
                "singleLength='" + singleLength + '\'' +
                ", singleCount='" + singleCount + '\'' +
                '}';
    }
}
