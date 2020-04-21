package com.controllers.processors;

import com.dao.DAOConnection;
import com.dao.OracleDAOConnection;
import com.model.ProcessorResult;
import com.model.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class TestProcessor extends Processor {
    public TestProcessor() {
        this.action = "test";
    }

    @Override
    public ProcessorResult getResult(HttpServletRequest request) {
        DAOConnection connection = OracleDAOConnection.getInstance();
        String username = request.getParameter("username");

        if (request.getParameter("questionId") != null) {
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            if (checkAnswer(questionId, request.getParameter("answer"))) {
                if (request.getSession().getAttribute("userPoints") == null) {
                    request.getSession().setAttribute("userPoints", connection.getQuestionById(questionId).getPoints());
                } else {
                    int userPoints = (int) request.getSession().getAttribute("userPoints");
                    request.getSession().setAttribute("userPoints", userPoints+connection.getQuestionById(questionId).getPoints());
                }
            }

            if (request.getSession().getAttribute("questionCount") == null) {
                request.getSession().setAttribute("questionCount", 1);
            } else {
                int questionCount = (int) request.getSession().getAttribute("questionCount");
                request.getSession().setAttribute("questionCount", questionCount+1);
            }

            if ((int) request.getSession().getAttribute("questionCount") == 5) {
                return new ProcessorResult("/finished", "", false); //Тут закончилось время, не смог закончить
            }
        }

        ArrayList<String> userNameList = connection.getUserNames();
        if (!userNameList.contains(username)) {
            connection.createUser(username);
        }

        return new ProcessorResult("/test?username="+username, "", false);
    }

    private boolean checkAnswer(int id, String answer) {
        DAOConnection connection = OracleDAOConnection.getInstance();
        Question question = connection.getQuestionById(id);

        return question.getAnswer().toLowerCase().equals(answer.toLowerCase());
    }
}
