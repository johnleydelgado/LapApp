package com.androidapp.labapp.Dashboard.UserList.Model;

public class PatientListData {

    private String name,date;

    public PatientListData(String name , String date){
        this.name = name;
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
