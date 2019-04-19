package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.example.admin.myapplication.Object.LichSu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowSearchHistory extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ArrayList <String> arrSearchHistory;
    ArrayAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsutimkiem_layout);

        arrSearchHistory = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrSearchHistory);


        ListView lvSearchHistory = (ListView) findViewById(R.id.lvSearchHistory);
        lvSearchHistory.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        LichSu LS = new LichSu("Nhan Vien", "CNTT", "Ha Noi");
        mDatabase.child("SearchHistory").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LichSu ls = dataSnapshot.getValue(LichSu.class);
                arrSearchHistory.add(ls.search_chucvu + " - " + ls.search_nganh + " - " + ls.search_thanhpho);
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
