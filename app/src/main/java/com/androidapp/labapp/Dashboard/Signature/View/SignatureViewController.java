package com.androidapp.labapp.Dashboard.Signature.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidapp.labapp.Dashboard.User.View.Activity.dashboard_user_activity;
import com.androidapp.labapp.R;
import com.androidapp.labapp.Utils.PaintView;

import java.io.ByteArrayOutputStream;


public class SignatureViewController extends AppCompatActivity {
    private PaintView mPaintView;
    private LinearLayout mLlCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        mLlCanvas = (LinearLayout) findViewById(R.id.llCanvas);
        mPaintView = new PaintView(this, null);
        mLlCanvas.addView(mPaintView, 0);
        mPaintView.requestFocus();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReset:
                mPaintView = new PaintView(this, null);
                mLlCanvas.addView(mPaintView, 0);
                mPaintView.requestFocus();
                break;
            case R.id.btnSave:
                saveBitmap();
                break;
            default:
                break;
        }
    }

    private void saveBitmap() {
        if (mPaintView.arl.size() == 0) {
            Toast.makeText(this,"Sign fail", Toast.LENGTH_LONG).show();
            return;
        }

        // View view = mLlCanvas.getRootView();
        View view =  (View)mLlCanvas.getChildAt(0);
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        Intent intent = new Intent();
        intent.putExtra(dashboard_user_activity.KEY_SIGN, encoded);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}