package com.androidapp.labapp.AppMain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
       // Fragment frag = findViewById(R.id.signin_fragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, new signin_fragment()).commit();
        FrameLayout frameLayout =  findViewById(R.id.frame_layout);
        Utils.setupUI(frameLayout,MainActivity.this);
    }

    public static MainActivity getInstance(){
        return instance;
    }

    public void SignInForm(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout,new signin_fragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void SignUpForm(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout,new signup_fragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void ForgotPasswordForm(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout,new forgotpassword_fragment());
        ft.addToBackStack(null);
        ft.commit();
    }

}
