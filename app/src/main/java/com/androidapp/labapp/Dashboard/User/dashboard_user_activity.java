package com.androidapp.labapp.Dashboard.User;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.androidapp.labapp.Dashboard.User.View.Fragment.user_transaction_fragment;
import com.androidapp.labapp.Dashboard.User.View.Fragment.user_profile_fragment;
import com.androidapp.labapp.R;

public class dashboard_user_activity extends AppCompatActivity {

    private String SELECTED_ITEM = "item";
    private int mSelectedItem;
    private MenuItem selectedItem;
    private BottomNavigationView navigationView;
    private static final int SIGN_REQUEST_CODE=10;
    public static final String KEY_SIGN = "signature";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);


        navigationView = findViewById(R.id.nav_user_dashboard);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectFragment(menuItem);
                return false;
            }
        });


        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = navigationView.getMenu().getItem(mSelectedItem);

        } else {
            selectedItem = navigationView.getMenu().getItem(0);

        }

        selectFragment(selectedItem);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    private void selectFragment(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {

            case R.id.info_user:

                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_view, new user_transaction_fragment()).commit();
                break;
            case R.id.profile_user:
                getSupportFragmentManager().
                        beginTransaction().replace(R.id.fragment_view, new user_profile_fragment()).commit();
                break;

            default:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_view, new user_transaction_fragment()).commit();
                break;

        }

        mSelectedItem = item.getItemId();



    }


}
