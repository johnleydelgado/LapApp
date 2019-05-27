package com.androidapp.labapp.Authentication.SignUp.View;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidapp.labapp.AppMain.MainActivity;
import com.androidapp.labapp.Authentication.SignUp.Presenter.signup_presenter;
import com.androidapp.labapp.Authentication.SignUp.Listener.signup_presenter_interface;
import com.androidapp.labapp.R;
import com.androidapp.labapp.UI.PopUp;
import com.androidapp.labapp.Utils.Utils;
import com.androidnetworking.AndroidNetworking;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;
import okhttp3.internal.Util;


public class signup_fragment extends Fragment implements signup_presenter_interface {

    signup_presenter presenter = new signup_presenter(this);
    private EditText fullnameText;
    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;
    private Button signup;
    private ProgressBar progressBar;

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
        signup = view.findViewById(R.id.register_btn);
        progressBar = view.findViewById(R.id.progress_bar);


        fullnameText = view.findViewById(R.id.fullname_editText);
        usernameText = view.findViewById(R.id.username_editText);
        emailText = view.findViewById(R.id.email_editText);
        passwordText = view.findViewById(R.id.password_editText);

        // Dropdown
        Sprite chasingDots = new ChasingDots();
        progressBar.setIndeterminateDrawable(chasingDots);
        progressBar.setVisibility(View.GONE);
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
                CheckingFields();
              }
        });


        return view;
    }

    public void CheckingFields(){
        String fname = fullnameText.getText().toString().trim();
        String uname = usernameText.getText().toString().trim();
        String em = emailText.getText().toString().trim();
        String pass = passwordText.getText().toString();

        if(fname.isEmpty()){
            fullnameText.setError("Please fill up all the required fields");
            fullnameText.requestFocus();

        }else if(uname.isEmpty()){
            usernameText.setError("Please fill up all the required fields");
            usernameText.requestFocus();
        }
        else if(em.isEmpty()){
            emailText.setError("Please fill up all the required fields");
            emailText.requestFocus();
        }

        else if(pass.isEmpty()){
            passwordText.setError("Please fill up all the required fields");
            passwordText.requestFocus();
        }
        else if(!Utils.isValidEmailId(em)){
            emailText.setError("Your email is invalid");
            emailText.requestFocus();
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            presenter.SignUpForm(fullnameText.getText().toString(), usernameText.getText().toString(), emailText.getText().toString(), passwordText.getText().toString(),progressBar);

        }

    }


    @Override
    public void showPopUp(String data) {
        final PrettyDialog prettyDialog= new PrettyDialog(getContext());
        prettyDialog.setTitle("Sign Up")
                .setMessage(data)
                .addButton("OK",					// button text
                        R.color.color_white,		// button text color
                        R.color.colorPrimaryDark,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {

                                prettyDialog.dismiss();
                                MainActivity.getInstance().SignInForm();
                            }
                        }
                )
                .show();
    }

    @Override
    public void showPopUpError(String data) {

        PopUp.alertPopUpnormal(getContext(),"ERROR",data);
    }
}
