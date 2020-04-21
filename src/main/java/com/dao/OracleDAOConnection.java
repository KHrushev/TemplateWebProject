package com.dao;

import com.model.Question;
import oracle.jdbc.driver.OracleDriver;

import com.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleDAOConnection implements DAOConnection {

    private static OracleDAOConnection oracleDAOConnection;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Driver driver;

    private OracleDAOConnection () {
        super();
    }

    public static OracleDAOConnection getInstance() {
        if (oracleDAOConnection != null) {
            return oracleDAOConnection;
        } else {
            oracleDAOConnection = new OracleDAOConnection();
            return oracleDAOConnection;
        }
    }


    @Override
    public void connect() {
        try {
            driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "hellfire");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disconnect() {
        try {
            if (connection != null){
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getUserNames() {
        connect();
        ArrayList<String> userNameList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM USER_MODULE");
            while (resultSet.next()) {
                userNameList.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userNameList;
    }

    @Override
    public void createUser(String name) {
        connect();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM USER_MODULE");

            int userCount = 0;
            while (resultSet.next()) {
                userCount = resultSet.getInt(1);
            }
            statement = connection.prepareStatement("INSERT INTO USER_MODULE VALUES (?, ?, 0)");
            ((PreparedStatement) statement).setInt(1, userCount+1);
            ((PreparedStatement) statement).setString(2, name);

            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question getRandomQuestion() {
        connect();
        try {
            Question question = new Question();

            int index = (int) (Math.random()*11);

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT question FROM QUESTIONS");

            while (resultSet.next()) {
                if (resultSet.getInt(1) == index) {
                    question.setId(resultSet.getInt("id"));
                    question.setQuestion(resultSet.getString("question"));
                    question.setAnswer(resultSet.getString("answer"));
                    question.setPoints(resultSet.getInt("points"));
                }
            }

            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Question getQuestionById(int id) {
        connect();
        try {
            Question question = new Question();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM QUESTIONS WHERE id = " + id);

            while (resultSet.next()) {
                question.setId(resultSet.getInt("id"));
                question.setAnswer(resultSet.getString("answer"));
                question.setQuestion(resultSet.getString("question"));
                question.setPoints(resultSet.getInt("points"));
            }
            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
