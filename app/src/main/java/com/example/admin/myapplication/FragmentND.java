package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FragmentND extends Fragment {

    EditText edtHoten , edtEmail , edtMatKhau , edtDiaChi , edtSoDienThoai;
    Button btnSubMit;
    RadioButton rdbNam, rdbNu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_fragment_nd, container, false);
        edtHoten = (EditText) view.findViewById(R.id.edtHoTen);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) view.findViewById(R.id.edtPassword);
        edtDiaChi = (EditText) view.findViewById(R.id.edtDiachi);
        edtSoDienThoai= (EditText) view.findViewById(R.id.edtSDT);
        btnSubMit = (Button) view.findViewById(R.id.btnDangKy);
        rdbNam = (RadioButton) view.findViewById(R.id.rdbNam);
        rdbNu = (RadioButton) view.findViewById(R.id.rdbNu);
        return view;
    }
}
