package com.controllers.servlets;

import com.controllers.processors.Processor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.controllers.processors.TestProcessor;
import com.model.ProcessorResult;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends javax.servlet.http.HttpServlet {

    private List<Processor> processors = new ArrayList<>();

    @Override
    public void init() throws ServletException{
        super.init();
        processors.add(new TestProcessor());
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        for (Processor tempProcessor : processors) {
            if (tempProcessor.canProcess(action)){
                ProcessorResult processorResult = tempProcessor.getResult(request);
                if (processorResult.isForward()) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(processorResult.getUrl());
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect(processorResult.getUrl());
                }
            }
        }
    }
}
