package com.model;

public class ProcessorResult {
    private String url;
    private String includedPage;
    private boolean isForward;

    public ProcessorResult(String url, String includedPage, boolean isForward) {
        this.url = "/TemplateWebProject_war_exploded"+url;
        this.includedPage = includedPage;
        this.isForward = isForward;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIncludedPage() {
        return includedPage;
    }

    public void setIncludedPage(String includedPage) {
        this.includedPage = includedPage;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }
}
