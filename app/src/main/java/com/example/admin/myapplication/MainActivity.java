package com.example.admin.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Khai báo biến
    private Button btnDangKy;
    private Button btnDangNhap;
    private Button btnMatPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        Intent intent = getIntent();
    }

    public void setControl(){
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnMatPassword = (Button) findViewById(R.id.btnForget);

    }
    public void setEvent(){
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công.", Toast.LENGTH_SHORT).show();
            }
        });

        btnMatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuenMatKhau.class);
                startActivity(intent);

            }
        });
    }

}
