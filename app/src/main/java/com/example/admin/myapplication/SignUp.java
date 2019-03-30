package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SignUp extends AppCompatActivity {

    RadioButton rdb;
    android.support.v4.app.FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fm = getSupportFragmentManager();

        rdb = (RadioButton) findViewById(R.id.rdbNguoidung);
        rdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction ft_add = fm.beginTransaction();
                ft_add.add(R.id.listFragment, new FragmentND());
                ft_add.commit();
            }
        });
    }
}
