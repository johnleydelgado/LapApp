package com.androidapp.labapp.UI;

import android.content.Context;

import com.androidapp.labapp.R;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class PopUp {

    public void alertPopUpnormal(Context context, String title , String message){

        new PrettyDialog(context)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    public void alertPopUpTwoButtons(Context context, String title , String message){

        new PrettyDialog(context)
                .setTitle(title)
                .setMessage(message)
                .addButton("OK",					// button text
                        R.color.color_white,		// button text color
                        R.color.colorPrimaryDark,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                            }
                        }
                )
                .addButton("Cancel",					// button text
                        R.color.color_white,		// button text color
                        R.color.colorAccent,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                            }
                        }
                )
                .show();
    }

}
