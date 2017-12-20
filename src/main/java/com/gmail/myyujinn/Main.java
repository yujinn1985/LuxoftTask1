package com.gmail.myyujinn;

import com.gmail.myyujinn.dao.Controller;
import com.gmail.myyujinn.daoclass.StatisticController;

public class Main {
    public static void main(String[] args) {
        Controller sc = new StatisticController();
        sc.createTable();
        sc.addStatistic("src/main/resources/New Folder");
        sc.closeConnection();
    }
}
