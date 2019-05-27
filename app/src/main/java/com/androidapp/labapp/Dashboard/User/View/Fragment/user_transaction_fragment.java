package com.androidapp.labapp.Dashboard.User.View.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidapp.labapp.Dashboard.Signature.SignatureViewController;
import com.androidapp.labapp.R;
import com.androidapp.labapp.UI.PopUp;
import com.androidapp.labapp.Utils.Utils;
import com.blikoon.qrcodescanner.QrCodeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class user_transaction_fragment extends Fragment {
    private String imgBase64="";
    private EditText barcodeText;
    private ImageView signatureImg;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int SIGN_REQUEST_CODE=10;
    public user_transaction_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_transaction,container,false);
        LinearLayout linearLayout = view.findViewById(R.id.linear_layout_container);
        Button btnQR = view.findViewById(R.id.qr_btn);
        Button btnSignature = view.findViewById(R.id.signature_btn);
        barcodeText = view.findViewById(R.id.barcode_editText);
        signatureImg = view.findViewById(R.id.signature_imageView);
        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),QrCodeActivity.class);
                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
            }
        });

        btnSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBase64="";
                Intent mIntent = new Intent(getContext(), SignatureViewController.class);
                startActivityForResult(mIntent, 10);
            }
        });
        Utils.setupUI(linearLayout,getActivity());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            if(data==null)
                return;
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                PopUp.alertPopUpnormal(getContext(),"Scan Error","QR Code could not be scanned");

            }
            return;
        }


        if (requestCode == SIGN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imgBase64 = data.getStringExtra("signature");

            // Print log to check base 64 string
            Log.i("Sign Base64","Sign Base64 : "+imgBase64);
          signatureImg.setImageBitmap(getBitMap(imgBase64));
        }

        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            PopUp.alertPopUpnormal(getContext(),"Scan Successfully","See the result");
            barcodeText.setText(result);


        }
    }

    public Bitmap getBitMap(String encodedString){
        try{
            byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

}
