package com.gmail.myyujinn.dao;

import com.gmail.myyujinn.entity.MyFile;

import java.sql.Connection;

public interface StatisticDAOMyFile {
    void addFileStat(Connection connection, MyFile myFile);
}
