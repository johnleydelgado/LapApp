package com.androidapp.labapp.Authentication.SignUp.View;

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
import com.androidapp.labapp.Authentication.SignUp.Presenter.signup_presenter;
import com.androidapp.labapp.Authentication.SignUp.Listener.signup_presenter_interface;
import com.androidapp.labapp.R;
import com.androidnetworking.AndroidNetworking;


public class signup_fragment extends Fragment implements signup_presenter_interface {

    signup_presenter presenter = new signup_presenter(this);

    public signup_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        AndroidNetworking.initialize(getContext());
        ImageView img = (ImageView) view.findViewById(R.id.signin_imageview);
        Button forgotpasswordBtn = (Button) view.findViewById(R.id.forgotpassword_btn);
        Button signup = view.findViewById(R.id.register_btn);

        final EditText fullnameText = view.findViewById(R.id.fullname_editText);
        final EditText usernameText = view.findViewById(R.id.username_editText);
        final EditText emailText = view.findViewById(R.id.email_editText);
        final EditText passwordText = view.findViewById(R.id.password_editText);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().SignInForm();
            }
        });

        forgotpasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().ForgotPasswordForm();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.SignUpForm(fullnameText.getText().toString(), usernameText.getText().toString(), emailText.getText().toString(), passwordText.getText().toString());
            }
        });

        return view;
    }


    @Override
    public void showPopUp(String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }
}
