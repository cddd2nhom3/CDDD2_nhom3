package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentNTD extends Fragment {

    EditText edtHoten , edtEmail , edtMatKhau , edtDiaChi , edtSoDienThoai;
    Button btnSubMit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_fragment_ntd, container, false);
        edtHoten = (EditText) view.findViewById(R.id.edtHoTen);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) view.findViewById(R.id.edtPassword);
        edtDiaChi = (EditText) view.findViewById(R.id.edtDiachi);
        edtSoDienThoai= (EditText) view.findViewById(R.id.edtSDT);
        btnSubMit = (Button) view.findViewById(R.id.btnDangKy);
        return view;
    }
}