package com.androidapp.labapp.Dashboard.UserList.Model;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.androidapp.labapp.Dashboard.UserList.Listener.user_patientlist_model_interface;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import eu.amirs.JSON;

public class User_PatientList {

    private String patientname;

    public user_patientlist_model_interface listener;

    public User_PatientList(user_patientlist_model_interface mlistener){

        this.listener = mlistener;
    }


    public void getPatientList(final ProgressBar progressBar,int u_id){
        final ArrayList<PatientListData> data = new ArrayList<PatientListData>();
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id",u_id);

        } catch (JSONException e){
            e.printStackTrace();
        }

        AndroidNetworking.post("http://192.168.100.15:82/getTransaction").addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {


            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                JSON json = new JSON(response);
                JSON patient_id = json.key("transaction");
                Log.d("patient is,",""+patient_id.count());
                for(int i = 0; i < patient_id.count(); i++){

                    String p_name = json.key("transaction").index(i).key("patient_name").stringValue();
                    //String p_name = json.key("transaction").index(i).key("patient_name").stringValue();
                    data.add(new PatientListData(p_name,"June 16,2019"));
                }
                listener.didPatientList(data);
            }

            @Override
            public void onError(ANError anError) {
                Log.d("wew", "onError errorCode : " +
                        anError.getResponse());
                Log.d("wew", "onError errorBody : " +
                        anError.getErrorBody());
                Log.d("wew", "onError errorDetail : " +
                        anError.getErrorDetail());
            }
        });
    }

}
