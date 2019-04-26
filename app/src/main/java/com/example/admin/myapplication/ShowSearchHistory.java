package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admin.myapplication.Adapter.LichSuTimKiem;
import com.example.admin.myapplication.Object.LichSu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowSearchHistory extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String userId;
    ArrayList <LichSu> arrSearchHistory;
    LichSuTimKiem adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsutimkiem_layout);
        ImageView imgBack = (ImageView) findViewById(R.id.btnBack);
        arrSearchHistory = new ArrayList<LichSu>();
        adapter = new LichSuTimKiem(ShowSearchHistory.this, R.layout.item_lichsu, arrSearchHistory);


        ListView lvSearchHistory = (ListView) findViewById(R.id.lvSearchHistory);
        lvSearchHistory.setAdapter(adapter);
        imgBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) { // TODO Auto-generated method stub
                Intent intent = new Intent(ShowSearchHistory.this,SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("USers").child(userId).child("SearchHistory").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LichSu ls = dataSnapshot.getValue(LichSu.class);
                arrSearchHistory.add(ls);
                adapter.notifyDataSetChanged();
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
        // Read from the database


    }
}
