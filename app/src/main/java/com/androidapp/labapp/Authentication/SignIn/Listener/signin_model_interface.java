package com.androidapp.labapp.Authentication.SignIn.Listener;

public interface signin_model_interface {

    void didSignInSucceed(String message,int id);
    void didNotSignInSucceed(String message);
    void getRoleID(int id);
}
