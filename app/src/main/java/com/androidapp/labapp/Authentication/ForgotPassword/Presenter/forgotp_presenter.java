package com.androidapp.labapp.Authentication.ForgotPassword.Presenter;

import com.androidapp.labapp.Authentication.ForgotPassword.Listener.forgotp_model_interface;
import com.androidapp.labapp.Authentication.ForgotPassword.Listener.forgotp_presenter_interface;
import com.androidapp.labapp.Authentication.ForgotPassword.Model.Forgotpassword;

public class forgotp_presenter implements forgotp_model_interface {

    private Forgotpassword model = new Forgotpassword(this);
    private forgotp_presenter_interface listener;

    public forgotp_presenter(forgotp_presenter_interface listener){
        this.listener = listener;
    }


    public void forgotPasswordForm(String email , String password){
        model.email = email;
        model.newPassword = password;
        model.changePassword();
    }

    @Override
    public void DidForgotPasswordSucceed(String message) {
        listener.showPopUp(message);
    }
}
