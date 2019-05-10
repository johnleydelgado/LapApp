package com.androidapp.labapp.Authentication.SignIn.Model;

import android.util.JsonReader;

import com.androidapp.labapp.Authentication.ForgotPassword.Listener.forgotp_model_interface;
import com.androidapp.labapp.Authentication.SignIn.Listener.signin_model_interface;
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

    public void signinProcess(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password", password);


        }catch (JSONException e){
            e.printStackTrace();
        }

        AndroidNetworking.post("http://192.168.100.6:81/login").addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                JSON json = new JSON(response);
                listener.didSignInSucceed(json.stringValue());
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

}
