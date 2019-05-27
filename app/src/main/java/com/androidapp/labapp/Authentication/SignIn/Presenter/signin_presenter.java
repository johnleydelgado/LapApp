package com.androidapp.labapp.Authentication.SignIn.Presenter;

import android.widget.ProgressBar;

import com.androidapp.labapp.Authentication.SignIn.Listener.signin_model_interface;
import com.androidapp.labapp.Authentication.SignIn.Listener.signin_presenter_interface;
import com.androidapp.labapp.Authentication.SignIn.Model.SignIn;

public class signin_presenter implements signin_model_interface {

    private SignIn model = new SignIn(this);
    private signin_presenter_interface listener;
    public signin_presenter(signin_presenter_interface listener){
        this.listener = listener;
    }

    @Override
    public void didSignInSucceed(String message) {
        listener.showPopUp(message);

    }

    @Override
    public void didNotSignInSucceed(String message) {
        listener.showPopUpError(message);

    }
    public void SignInForm(String username , String password , ProgressBar progressBar){
        model.username = username;
        model.password = password;
        model.signinProcess(progressBar);
    }


}
