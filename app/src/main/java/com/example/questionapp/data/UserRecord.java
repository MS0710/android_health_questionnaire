package com.example.questionapp.data;

public class UserRecord {
    private String item;
    private int Score;
    private String date;
    public UserRecord(String _item,int _Score,String _date){
        this.item = _item;
        this.Score = _Score;
        this.date = _date;
    }

    public String getItem() {
        return item;
    }

    public int getScore() {
        return Score;
    }

    public String getDate() {
        return date;
    }
}
