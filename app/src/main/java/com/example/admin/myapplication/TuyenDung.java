package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.admin.myapplication.Object.NhaTuyenDung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TuyenDung extends AppCompatActivity {
    private static final String TAG = TuyenDung.class.getSimpleName();
    private EditText edttenDoanhNghiep , edtEmail , edtMatKhau , edtDiaChi , edtSoDienThoai;
    private Button btnSubMit;
    private DatabaseReference mDatabase;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuyen_dung);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();*/
        /*final DatabaseReference myRef = database.getReference("NhaTuyenDung");*/
        edttenDoanhNghiep = (EditText) findViewById(R.id.edtHoTen);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) findViewById(R.id.edtPassword);
        edtDiaChi = (EditText) findViewById(R.id.edtDiachi);
        edtSoDienThoai = (EditText) findViewById(R.id.edtSDT);
        btnSubMit = (Button) findViewById(R.id.btnDangKy);
        btnSubMit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDoanhNghiep = edttenDoanhNghiep.getText().toString();
                String email = edtEmail.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                int soDienThoai = Integer.parseInt(edtSoDienThoai.getText().toString());
                String userId = mDatabase.push().getKey();
                NhaTuyenDung NTD = new NhaTuyenDung(tenDoanhNghiep,email,matKhau,diaChi,soDienThoai);
                mDatabase.child("nhatuyendung").child(userId).setValue(NTD);
            }
        });
        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
