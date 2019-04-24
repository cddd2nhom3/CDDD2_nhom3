package com.example.admin.myapplication.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.myapplication.Object.ThuocTinh;
import com.example.admin.myapplication.Object.ThuocTinhHot;
import com.example.admin.myapplication.R;

import java.util.ArrayList;
public class MyAdapterHot extends ArrayAdapter<ThuocTinhHot> {
    Activity context = null;
    int itemlayout;
    ArrayList<ThuocTinhHot> question = null;

    public MyAdapterHot(Activity context, int resource, ArrayList<ThuocTinhHot>objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.itemlayout = resource;
        this.question = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        convertView = inflater.inflate(itemlayout, null);

        TextView txtTenCV = (TextView) convertView
                .findViewById(R.id.txtTieuDe);
        TextView txtTenCTy = (TextView) convertView
                .findViewById(R.id.txtCongTy);
        ThuocTinhHot jobs = question.get(position);

        if (jobs != null) {

//            Log.d("Phat", jobs.getTieude().toString());

            txtTenCV.setText(jobs.getTieude());
            txtTenCTy.setText(jobs.getCongty());
        }
        return convertView;
    }

    public ThuocTinh get(int position) {
        // TODO Auto-generated method stub
        return null;
    }
}
