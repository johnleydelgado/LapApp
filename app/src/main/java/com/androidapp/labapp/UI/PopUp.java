package com.androidapp.labapp.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.androidapp.labapp.R;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class PopUp {

    public static void alertPopUpnormal(Context context, String title , String message){

        final PrettyDialog pDialog = new PrettyDialog(context);
                pDialog.setTitle(title)
                .setMessage(message)
                .addButton("OK",					// button text
                        R.color.color_white,		// button text color
                        R.color.colorPrimaryDark,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                pDialog.dismiss();
                            }
                        }
                )
                .show();
    }

    public static void alertPopUpOneButtonWithIntent(final Context context, String title , String message,final Class<? extends Activity> activity){

        new PrettyDialog(context)
                .setTitle(title)
                .setMessage(message)
                .addButton("OK",					// button text
                        R.color.color_white,		// button text color
                        R.color.colorPrimaryDark,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(context,activity);
                                context.startActivity(intent);
                                // Do what you gotta do
                            }
                        }
                )

                .show();
    }
    public static void alertPopUpTwoButtonsWithIntent(Context context, String title , String message, final Class<? extends Activity> activity){

         final PrettyDialog prettyDialog = new PrettyDialog(context);
         prettyDialog.setTitle(title)
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
                                prettyDialog.dismiss();
                            }
                        }
                )
                .show();
    }

}
