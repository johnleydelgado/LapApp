package com.androidapp.labapp.Dashboard.UserList.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidapp.labapp.Dashboard.UserList.Model.PatientListData;
import com.androidapp.labapp.R;

import java.util.ArrayList;

public class user_adapter_listview extends ArrayAdapter<PatientListData> {

    private ArrayList<PatientListData> dataSet;
    private Context context;

    public user_adapter_listview(ArrayList<PatientListData> data ,Context context) {
        super(context, R.layout.listview_layout_patientlist, data);
        this.dataSet = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        PatientListData dataModel = getItem(position);
        if (r == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            r = layoutInflater.inflate(R.layout.listview_layout_patientlist, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.name.setText(dataModel.getName());
        viewHolder.date.setText(dataModel.getDate());

        return r;
    }

    class ViewHolder {

        TextView name;
        TextView date;

        ViewHolder(View v) {
            name = v.findViewById(R.id.lbl_patientname);
            date = v.findViewById(R.id.lbl_date);
        }

    }
}
