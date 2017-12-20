package com.gmail.myyujinn.dao;

import com.gmail.myyujinn.entity.MyFile;
import java.sql.Connection;

public interface StatisticDAOMyLine {
    void addLineStat(Connection connection, MyFile myFile);
}
