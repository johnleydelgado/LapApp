package com.androidapp.labapp.Dashboard.UserList.Presenter;

import android.util.Log;
import android.widget.ProgressBar;

import com.androidapp.labapp.Dashboard.UserList.Listener.user_patientlist_model_interface;
import com.androidapp.labapp.Dashboard.UserList.Listener.user_patientlist_presenter_interface;
import com.androidapp.labapp.Dashboard.UserList.Model.PatientListData;
import com.androidapp.labapp.Dashboard.UserList.Model.User_PatientList;

import java.util.ArrayList;

public class user_patientlist_presenter implements user_patientlist_model_interface {

    private user_patientlist_presenter_interface listener;
    private User_PatientList model = new User_PatientList(this);

    public user_patientlist_presenter(user_patientlist_presenter_interface mlistener){
        this.listener = mlistener;
    }

    public void fetchPatient(ProgressBar progressBar,int u_id){
        model.getPatientList(progressBar,u_id);
    }

    @Override
    public void didPatientList(ArrayList<PatientListData> patientList) {
        listener.patientList(patientList);

    }

    @Override
    public void didPatientNotSucceed(String message) {

    }
}
