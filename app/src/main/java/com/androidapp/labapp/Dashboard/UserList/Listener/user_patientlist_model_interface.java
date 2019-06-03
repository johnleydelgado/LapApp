package com.androidapp.labapp.Dashboard.UserList.Listener;

import com.androidapp.labapp.Dashboard.UserList.Model.PatientListData;

import java.util.ArrayList;

public interface user_patientlist_model_interface {

    void didPatientList(ArrayList<PatientListData> patientList);
    void didPatientNotSucceed(String message);
}
