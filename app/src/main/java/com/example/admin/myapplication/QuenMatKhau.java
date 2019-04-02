package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myapplication.utils.Utils;

public class QuenMatKhau extends AppCompatActivity {

    Button btnGui;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        btnGui = (Button) findViewById(R.id.btnSend);


        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuenMatKhau.this, "Chúng tôi đã gửi mật khẩu vào địa chỉ email của bạn. Vui lòng kiểm tra email và cập nhật mật khẩu.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
