package com.androidapp.labapp.Authentication.SignIn.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.labapp.AppMain.MainActivity;
import com.androidapp.labapp.Authentication.SignIn.Listener.signin_presenter_interface;
import com.androidapp.labapp.Authentication.SignIn.Presenter.signin_presenter;
import com.androidapp.labapp.R;

public class signin_fragment extends Fragment implements signin_presenter_interface {

    private signin_presenter presenter = new signin_presenter(this);

    public signin_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.signup_imageview);
        Button forgotpasswordBtn = (Button) view.findViewById(R.id.forgotpassword_btn);
        Button signinBtn = (Button) view.findViewById(R.id.login_btn);

        final EditText username = view.findViewById(R.id.username_editText);
        final EditText password = view.findViewById(R.id.password_editText);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().SignUpForm();
            }
        });

        forgotpasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().ForgotPasswordForm();
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.SignInForm(username.getText().toString(),password.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void showPopUp(String data) {
        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
    }
}
