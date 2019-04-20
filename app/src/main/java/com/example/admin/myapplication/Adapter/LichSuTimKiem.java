package com.example.admin.myapplication.Adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.myapplication.Object.LichSu;
import com.example.admin.myapplication.R;

import java.util.ArrayList;

public class LichSuTimKiem extends ArrayAdapter<LichSu> {
    private Activity context = null;
    private int layoutID;
    private ArrayList<LichSu> listDS = null/*question = null*/;

    public LichSuTimKiem(@NonNull Activity context, @LayoutRes int layoutID, @NonNull ArrayList<LichSu> objects) {
        super(context, layoutID, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.layoutID = layoutID;
        this.listDS = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //View view = null;
        LayoutInflater inflater = context.getLayoutInflater();
        //view = inflater.inflate(layoutID, parent, false);
        convertView = inflater.inflate(layoutID, null);


        TextView txtTenCV = (TextView) convertView.findViewById(R.id.txtChucVu);
        TextView txtTenCTy = (TextView) convertView.findViewById(R.id.txtNganh);
        TextView txtTenTP = (TextView) convertView.findViewById(R.id.txtDiaChi);


        LichSu jobs = listDS.get(position);
        if (jobs != null) {


            txtTenCV.setText(jobs.getSearch_chucvu());
            txtTenCTy.setText(jobs.getSearch_nganh());
            txtTenTP.setText(jobs.getSearch_thanhpho());

        }
        return convertView;
    }
}
