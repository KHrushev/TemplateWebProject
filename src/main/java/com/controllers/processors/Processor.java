package com.controllers.processors;

import javax.servlet.http.HttpServletRequest;
import com.model.ProcessorResult;

public abstract class Processor {
    protected String action;

    public boolean canProcess(String actionToProcess) {
        return actionToProcess.equals(action);
    }

    public abstract ProcessorResult getResult(HttpServletRequest request);
}
