package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.myapplication.Object.ThuocTinh;

public class ChiTietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        ThuocTinh thuocTinh =(ThuocTinh) getIntent().getSerializableExtra("ThuocTinh");
    }
}
