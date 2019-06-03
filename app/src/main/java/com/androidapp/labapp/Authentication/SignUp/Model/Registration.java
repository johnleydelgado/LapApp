package com.androidapp.labapp.Authentication.SignUp.Model;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.androidapp.labapp.Authentication.SignUp.Listener.signup_model_interface;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import org.json.JSONException;
import org.json.JSONObject;

import eu.amirs.JSON;

public class Registration {

    public String fullname;
    public String username;
    public String email;
    public String password;
    private signup_model_interface listener;

    //Construct
    public Registration(signup_model_interface listener) {

        this.listener = listener;
    }

    public void userRegistration(final Wave progressBar) {


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("u_fullname", fullname);
            jsonObject.put("u_username", username);
            jsonObject.put("u_emailaddress", email);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("http://192.168.0.34:82/users/registration")
                //.addBodyParameter(user)
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.stop();

                        JSON json = new JSON(response);
                        if(json.key("success").intValue() == 1){

                            Log.d("register","sucess");
                            listener.didSignupSucceed(json.key("message").stringValue());
                        }else{
                            Log.d("register3","sucess");
                            listener.didNotSignUpSucceed(json.key("message").stringValue());
                        }
                        //Log.d("tag1","id is : " + json.key("user_id").intValue());

                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        progressBar.stop();
                        Log.d("tag1",""+error);
                    }
                });

    }
}
