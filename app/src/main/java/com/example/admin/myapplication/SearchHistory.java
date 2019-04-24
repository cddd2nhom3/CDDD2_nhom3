package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.admin.myapplication.Object.LichSu;

public class SearchHistory extends AppCompatActivity {
    private static final String TAG = LichSu.class.getSimpleName();

    private DatabaseReference mDatabase;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final AutoCompleteTextView edtTenViecLam = (AutoCompleteTextView) findViewById(R.id.edtNganhNghe);
        final AutoCompleteTextView edtChucDanh = (AutoCompleteTextView) findViewById(R.id.editChucDanh);
        final AutoCompleteTextView edtTenTP = (AutoCompleteTextView) findViewById(R.id.edtDiaDiem);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);




        // Read from the database

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_chucvu= edtChucDanh.getText().toString();
                String search_nganh = edtTenViecLam.getText().toString();
                String search_thanhpho = edtTenTP.getText().toString();
                LichSu LS = new LichSu(search_chucvu, search_nganh, search_thanhpho);
                //mDatabase.child("SearchHistory").push().setValue(LS);
            }
        });
    }
}
