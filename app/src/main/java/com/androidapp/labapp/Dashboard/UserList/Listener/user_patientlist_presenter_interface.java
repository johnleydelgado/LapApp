package com.androidapp.labapp.Dashboard.UserList.Listener;

import com.androidapp.labapp.Dashboard.UserList.Model.PatientListData;

import java.util.ArrayList;

public interface user_patientlist_presenter_interface {

    void patientList(ArrayList<PatientListData> patientList);
    void showPopUpError();
}
