package com.androidapp.labapp.Dashboard.User.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidapp.labapp.R;
import com.androidapp.labapp.Utils.Utils;
import com.jaredrummler.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class user_profile_fragment extends Fragment {

    private MaterialSpinner dropdownGender;
    public user_profile_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile,container,false);
        LinearLayout linearLayout = view.findViewById(R.id.linear_layout_container);
        Utils.setupUI(linearLayout,getActivity());
        dropdownGender = view.findViewById(R.id.dropdown_gender);
        dropdownGender.setItems("Male","Female");
        dropdownGender.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            }
        });


        return view;
    }

}
