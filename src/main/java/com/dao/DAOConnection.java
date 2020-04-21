package com.dao;

import com.model.Question;

import java.util.ArrayList;

public interface DAOConnection {
    void connect();
    void disconnect();

    ArrayList<String> getUserNames();
    void createUser(String name);
    Question getRandomQuestion();
    Question getQuestionById(int id);
}
