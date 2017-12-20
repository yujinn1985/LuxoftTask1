package com.gmail.myyujinn.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MyLine {
    private String stringOfTextFromFile;

    public MyLine() {
        super();
    }

    public MyLine(String stringOfTextFromFile) {
        super();
        this.stringOfTextFromFile = stringOfTextFromFile;
    }

    public String getStringOfTextFromFile() {
        return stringOfTextFromFile;
    }

    public void setStringOfTextFromFile(String stringOfTextFromFile) {
        this.stringOfTextFromFile = stringOfTextFromFile;
    }

    public String getLongestWord() {
        List<String> list = Arrays.stream(this.stringOfTextFromFile.split(" "))
                .filter(a -> a.length() > 0)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toCollection(ArrayList::new));
        return list.get(list.size() - 1);
    }

    public String getShortestWord() {
        return Arrays.stream(this.stringOfTextFromFile.split(" "))
                .filter(a -> a.length() > 0)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toCollection(ArrayList::new))
                .get(0);
    }

    public double getAverageWordLength() {
        return Arrays.stream(this.stringOfTextFromFile.split(" "))
                .collect(Collectors.averagingInt(String::length));
    }

    public int getLineLength() {
        return this.stringOfTextFromFile.length();
    }

    @Override
    public String toString() {
        return "MyLine{" +
                "stringOfTextFromFile='" + stringOfTextFromFile + '\'' +
                '}';
    }
}
