package com.example.admin.myapplication;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.myapplication.Object.HoSoCaNhan;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowPersonInformation extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ho_so_nguoi_dung_layout);
        final ImageView imgHSCN = (ImageView)findViewById(R.id.imgHSCN);
        final TextView edtHoTen = (TextView) findViewById(R.id.edtHoTen);
        final TextView edtDiaChi = (TextView) findViewById(R.id.edtDiachi);
        final TextView edtGioiTinh = (TextView) findViewById(R.id.edtGioiTinh);
        final TextView edtSdt = (TextView) findViewById(R.id.edtSDT);
        final TextView edtEmail = (TextView) findViewById(R.id.edtEmail);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        edtEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        mDatabase.child("USers").child(userId).child("HoSoCaNhan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HoSoCaNhan hscn = dataSnapshot.getValue(HoSoCaNhan.class);
                edtHoTen.setText(hscn.getHoTen());
                edtDiaChi.setText(hscn.getDiaChi());
                edtGioiTinh.setText(hscn.getGioiTinh());
                edtSdt.setText(hscn.getSoDienThoai());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
