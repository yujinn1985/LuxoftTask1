package com.gmail.myyujinn.dao;

public interface Controller {
    void createTable();

    void addStatistic(String path);

    void closeConnection();
}
