package com.androidapp.labapp.Authentication.SignIn.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidapp.labapp.Authentication.ForgotPassword.Listener.forgotp_model_interface;
import com.androidapp.labapp.Authentication.SignIn.Listener.signin_model_interface;
import com.androidapp.labapp.Authentication.SignIn.View.signin_fragment;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import eu.amirs.JSON;

public class SignIn {

    signin_model_interface listener;
    public String username,password;


    public SignIn (signin_model_interface listener){
        this.listener = listener;
    }

    public void signinProcess(final ProgressBar progressBar){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("http://192.168.100.15:82/login")
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                JSON json = new JSON(response);
                int uid = json.key("user_id").intValue();
                int roleid = json.key("roles").intValue();
                Log.d("try",username+ " "+password);
                if(json.key("success").intValue()== 1) {

                    listener.didSignInSucceed(json.key("message").stringValue(),uid);
                    listener.getRoleID(roleid);
                }else{
                    listener.didNotSignInSucceed(json.key("message").stringValue());
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.e("error",anError.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
