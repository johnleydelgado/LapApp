package com.androidapp.labapp.Authentication.ForgotPassword.View;

import android.nfc.Tag;
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
import com.androidapp.labapp.Authentication.ForgotPassword.Listener.forgotp_presenter_interface;
import com.androidapp.labapp.Authentication.ForgotPassword.Presenter.forgotp_presenter;
import com.androidapp.labapp.R;
import com.androidapp.labapp.Utils.Utils;

public class forgotpassword_fragment extends Fragment implements forgotp_presenter_interface {

    private forgotp_presenter presenter = new forgotp_presenter(this);

    public forgotpassword_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgotpassword, container, false);
        Button RememberPasswordBtn = (Button) view.findViewById(R.id.rememberpassword_btn);
        Button forgotPasswordBtn = view.findViewById(R.id.send_btn);
        final EditText email = view.findViewById(R.id.email_editText);
        final EditText newpassword = view.findViewById(R.id.new_password);
        final EditText confirmNewPassword = view.findViewById(R.id.confirm_password);


        RememberPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().SignInForm();
            }
        });

        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isValidEmailId(email.getText().toString().trim())) {
                    if(newpassword.getText().toString() == confirmNewPassword.getText().toString()) {
                        presenter.forgotPasswordForm(email.getText().toString(), newpassword.getText().toString());
                    }else{
                        Toast.makeText(getContext(), "password are not match", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "email is not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void showPopUp(String data) {
        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
    }
}
