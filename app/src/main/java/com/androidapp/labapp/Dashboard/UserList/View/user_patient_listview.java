package com.androidapp.labapp.Dashboard.UserList.View;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidapp.labapp.Dashboard.UserList.Adapter.user_adapter_listview;
import com.androidapp.labapp.Dashboard.UserList.Listener.user_patientlist_presenter_interface;
import com.androidapp.labapp.Dashboard.UserList.Model.PatientListData;
import com.androidapp.labapp.Dashboard.UserList.Presenter.user_patientlist_presenter;
import com.androidapp.labapp.R;
import com.androidnetworking.AndroidNetworking;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;

import java.util.ArrayList;

import static com.androidapp.labapp.Authentication.SignIn.View.signin_fragment.PatientPref;


public class user_patient_listview extends Fragment implements user_patientlist_presenter_interface {

    private user_patientlist_presenter presenter = new user_patientlist_presenter(this);
    private SwipeMenuListView swipeMenuListView;
    public user_patient_listview(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_patient_listview,container,false);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        swipeMenuListView = view.findViewById(R.id.listView);

        // progressbar
        Sprite chasingDots = new ChasingDots();
        progressBar.setIndeterminateDrawable(chasingDots);
        progressBar.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("patient_pref", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt(PatientPref,0);
        presenter.fetchPatient(progressBar,id);

        return view;
    }

    @Override
    public void patientList(ArrayList<PatientListData> patientList) {

        //ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,patientList);
        user_adapter_listview adapter = new user_adapter_listview(patientList,getContext());

        swipeMenuListView.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                openItem.setWidth(130);
                openItem.setTitle("Edit");
                openItem.setIcon(R.drawable.ic_edit_deep_purple_700_24dp);
                openItem.setTitleSize(10);
                openItem.setTitleColor(Color.BLACK);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                deleteItem.setWidth(130);
                deleteItem.setTitle("Delete");
                deleteItem.setIcon(R.drawable.ic_delete_red_700_24dp);
                deleteItem.setTitleSize(10);
                deleteItem.setTitleColor(Color.BLACK);
                menu.addMenuItem(deleteItem);
            }
        };
        swipeMenuListView.setMenuCreator(creator);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    @Override
    public void showPopUpError() {

    }
}
