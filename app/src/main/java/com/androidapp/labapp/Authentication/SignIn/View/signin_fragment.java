package com.androidapp.labapp.Authentication.SignIn.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.androidapp.labapp.AppMain.MainActivity;
import com.androidapp.labapp.Authentication.SignIn.Listener.signin_presenter_interface;
import com.androidapp.labapp.Authentication.SignIn.Presenter.signin_presenter;
import com.androidapp.labapp.Dashboard.User.dashboard_user_activity;
import com.androidapp.labapp.R;
import com.androidapp.labapp.UI.PopUp;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;

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
        final ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        Sprite chasingDots = new ChasingDots();
        progressBar.setIndeterminateDrawable(chasingDots);
        progressBar.setVisibility(View.GONE);

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
                progressBar.setVisibility(View.VISIBLE);
                Log.d("text",username.getText().toString()+ password.getText().toString());
                presenter.SignInForm(username.getText().toString(),password.getText().toString(),progressBar);
            }
        });

        return view;
    }

    @Override
    public void showPopUp(String data) {
       PopUp.alertPopUpOneButtonWithIntent(this.getContext(),"Sign in",data,dashboard_user_activity.class);
      // getActivity().finish();
    }

    @Override
    public void showPopUpError(String data) {
        PopUp.alertPopUpnormal(this.getContext(),"ERROR",data);
    }
}
