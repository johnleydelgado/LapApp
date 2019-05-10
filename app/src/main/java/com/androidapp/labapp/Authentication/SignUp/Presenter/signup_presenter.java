package com.androidapp.labapp.Authentication.SignUp.Presenter;

import android.util.Log;

import com.androidapp.labapp.Authentication.SignUp.Model.Registration;
import com.androidapp.labapp.Authentication.SignUp.Listener.signup_presenter_interface;
import com.androidapp.labapp.Authentication.SignUp.Listener.signup_model_interface;

public class signup_presenter implements signup_model_interface {
    public static signup_presenter instance;

    private Registration model = new Registration(this);
    private signup_presenter_interface listener;

    //Construct
    public signup_presenter(signup_presenter_interface listener) {

        this.listener = listener;
    }

    public void SignUpForm(String fullname, String username, String email, String password) {
        Log.d("tag1","signup foorm");
        model.fullname = fullname;
        model.username = username;
        model.email = email;
        model.password = password;
        model.userRegistration();
    }


    @Override
    public void didSignupSucceed(String message) {
        listener.showPopUp(message);
    }
}
