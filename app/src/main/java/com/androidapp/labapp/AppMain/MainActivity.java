package com.androidapp.labapp.AppMain;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.androidapp.labapp.Authentication.ForgotPassword.View.forgotpassword_fragment;
import com.androidapp.labapp.Authentication.SignIn.View.signin_fragment;
import com.androidapp.labapp.Authentication.SignUp.View.signup_fragment;
import com.androidapp.labapp.R;
import com.androidapp.labapp.Utils.Utils;

public class MainActivity extends AppCompatActivity {


    public static MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.relativelayout_main);
        setupUI(rel);
    }

    public static MainActivity getInstance(){
        return instance;
    }

    public void SignInForm(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.signin_fragment,new signin_fragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void SignUpForm(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.signin_fragment,new signup_fragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void ForgotPasswordForm(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.signin_fragment,new forgotpassword_fragment());
        ft.addToBackStack(null);
        ft.commit();
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Utils.hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}
