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
    private Activity context = null;/* = null;*/
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


        //get view from layout item
        /*TextView lblTitle = (TextView) view.findViewById(R.id.txtTieuDe);
        ImageView imgCongTy = (ImageView) view.findViewById(R.id.imgCongTy);
        TextView lblCongTy = (TextView) view.findViewById(R.id.txtCongTy);
        ImageView imgDiaDiem = (ImageView) view.findViewById(R.id.imgDiaDiem);
        TextView lblDiaDiem = (TextView) view.findViewById(R.id.txtDiaDiem);
        ImageView imgLuong = (ImageView) view.findViewById(R.id.imgLuong);
        TextView lblLuong = (TextView) view.findViewById(R.id.txtLuong);
        ImageView imgThoiGian = (ImageView) view.findViewById(R.id.imgThoiGian);
        TextView lblThoiGian = (TextView) view.findViewById(R.id.txtThoiGian);

        ThuocTinh jobs = listDS.get(position);
        if (jobs != null) {
            //Tiêu đề
            lblTitle.setText(jobs.getTieude());
            //Công ty
            imgCongTy.setBackground(context.getResources().getDrawable(R.drawable.company));
            lblCongTy.setText(jobs.getCongty());
            //Địa điểm
            imgDiaDiem.setBackground(context.getResources().getDrawable(R.drawable.mapsearch));
            lblDiaDiem.setText(jobs.getDiadiem());
            //Lương
            imgLuong.setBackground(context.getResources().getDrawable(R.drawable.dolla));
            lblLuong.setText(jobs.getLuong());
            //Thời gian
            imgThoiGian.setBackground(context.getResources().getDrawable(R.drawable.time));
            lblThoiGian.setText(jobs.getNgaydang());
        }
        return view;
    }*/

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
