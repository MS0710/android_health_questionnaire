package com.example.questionapp.data;

public class DataList {
    private String questionTitle;
    private String question;
    private int ans;
    private String titleOrContent;

    public DataList(String _questionTitle,String _question,int _ans,String _titleOrContent){
        this.questionTitle = _questionTitle;
        this.question = _question;
        this.ans = _ans;
        this.titleOrContent = _titleOrContent;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestion() {
        return question;
    }
    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public String getTitleOrContent() {
        return titleOrContent;
    }
}
