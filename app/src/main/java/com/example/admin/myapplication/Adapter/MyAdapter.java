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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.myapplication.Object.ThuocTinh;
import com.example.admin.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class MyAdapter extends ArrayAdapter<ThuocTinh> {
    private Activity context = null;
    private int layoutID;
    private ArrayList<ThuocTinh> listDS = null/*question = null*/;

    public MyAdapter(@NonNull Activity context, @LayoutRes int layoutID, @NonNull ArrayList<ThuocTinh> objects) {
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


        TextView txtTenCV = (TextView) convertView.findViewById(R.id.txtTieuDe);
        TextView txtTenCTy = (TextView) convertView.findViewById(R.id.txtCongTy);
        TextView txtTenTP = (TextView) convertView.findViewById(R.id.txtDiaDiem);
        TextView txtLuong = (TextView) convertView.findViewById(R.id.txtLuong);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtThoiGian);

        ThuocTinh jobs = listDS.get(position);
        if (jobs != null) {

            Log.d("Phát", jobs.getTieude());

            txtTenCV.setText(jobs.getTieude());
            txtTenCTy.setText(jobs.getCongty());
            txtTenTP.setText(jobs.getDiadiem());
            txtLuong.setText(jobs.getLuong());
            txtDate.setText(jobs.getNgaydang());
        }
        return convertView;
    }

}
