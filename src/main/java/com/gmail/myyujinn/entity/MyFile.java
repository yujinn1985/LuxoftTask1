package com.gmail.myyujinn.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyFile {
    private static int file_num = 0;
    private int id;
    private String pathToFile;
    private List<MyLine> listOfLines;

    {
        id = ++file_num;
    }

    public MyFile(String pathToFile) {
        super();
        this.pathToFile = pathToFile;
        this.listOfLines = initializeListOfLines(this.pathToFile);
    }

    public MyFile() {
        super();
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MyLine> getListOfLines() {
        return Collections.unmodifiableList(listOfLines);
    }

    public Long getTotalWords() {
        return listOfLines.stream().flatMap(a -> Arrays.stream(a.getStringOfTextFromFile().split(" ")))
                .filter(b -> b.length() > 0)
                .count();
    }

    protected final List<MyLine> initializeListOfLines(String path) {
        List<MyLine> listOfLines = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(new File(path)))) {
            while (true) {
                String str = bfr.readLine();
                if (str == null) {
                    break;
                } else if (str.length() == 0 || !stringCheck(str)) {
                    continue;
                }
                MyLine stringOfText = new MyLine(str);
                listOfLines.add(stringOfText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
    }

    private boolean stringCheck(String string) {
        char[] charArray = new char[string.length()];
        string.getChars(0, string.length(), charArray, 0);
        for (char ch : charArray) {
            if (ch != ' ') {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "pathToFile='" + pathToFile + '\'' +
                ", listOfLines=" + listOfLines +
                '}';
    }
}
