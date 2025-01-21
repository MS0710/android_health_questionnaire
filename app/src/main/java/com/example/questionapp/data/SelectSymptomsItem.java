package com.example.questionapp.data;

public class SelectSymptomsItem {
    private String title;
    private String subTitle;
    private int index;
    private boolean isChecked;


    public SelectSymptomsItem(String _title,String _subTitle,int _index,boolean _isChecked){
        this.title = _title;
        this.subTitle = _subTitle;
        this.index = _index;
        this.isChecked = _isChecked;
    }
    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getIndex() {
        return index;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean getChecked() {
        return isChecked;
    }



}
