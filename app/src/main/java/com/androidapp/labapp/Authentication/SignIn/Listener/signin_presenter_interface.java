package com.androidapp.labapp.Authentication.SignIn.Listener;

public interface signin_presenter_interface {

    void showPopUp(String data,int id);
    void showPopUpError(String data);
    void saveprefRoleID(int id);
}
