package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SignUp extends AppCompatActivity {

    // Khai báo biến
    RadioButton rdbND;
    RadioButton rdbNTD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rdbND = (RadioButton) findViewById(R.id.rdbNguoidung);
        rdbND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, NguoiDung.class));
            }
        });

        rdbNTD = (RadioButton) findViewById(R.id.rdbNguoituyendung);
        rdbNTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, TuyenDung.class));
            }
        });
    }
}
