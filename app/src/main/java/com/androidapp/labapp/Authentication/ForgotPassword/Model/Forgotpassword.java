package com.androidapp.labapp.Authentication.ForgotPassword.Model;

import android.util.JsonReader;

import com.androidapp.labapp.Authentication.ForgotPassword.Listener.forgotp_model_interface;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import eu.amirs.JSON;


public class Forgotpassword {

    private forgotp_model_interface listener;
    public String email;
    public String newPassword;

    public Forgotpassword (forgotp_model_interface listener){
        this.listener = listener;
    }

    public void changePassword(){

        AndroidNetworking.get("http://192.168.100.6:81/changepPassword").setPriority(Priority.MEDIUM)
                .addPathParameter("email",email)
                .addPathParameter("newpassword",newPassword)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                JSON json = new JSON(response);
                listener.DidForgotPasswordSucceed(json.stringValue());
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }

}
