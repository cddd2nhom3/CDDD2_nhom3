package com.example.admin.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.admin.myapplication.Object.HoSoCaNhan;
import com.example.admin.myapplication.base.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class NguoiDung extends BaseActivity {
    private DatabaseReference mDatabase;
   private Button btnUpload,btnChoose;
   private ImageView imgHS;
   private EditText edtHoTen , edtDiaChi , edtSDT;
   private RadioButton radNam , radNu;
   private Uri filePath;
   private String userId;
   //firebase
    FirebaseStorage storage;
    StorageReference storageReference;
   private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        btnUpload = (Button)findViewById(R.id.btnUpload);
        imgHS = (ImageView)findViewById(R.id.imgHSCN);
        btnChoose  = (Button)findViewById(R.id.btnChoose);
        edtHoTen = (EditText)findViewById(R.id.edtHoTen);
        edtSDT = (EditText)findViewById(R.id.edtSDT);
        edtDiaChi = (EditText)findViewById(R.id.edtDiachi);
        radNam = (RadioButton)findViewById(R.id.rdbNam);
        radNu = (RadioButton)findViewById(R.id.rdbNu);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUpload();
                HoSo();
            }
        });
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgChoose();
            }
        });
    }

    private void imgChoose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgHS.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void imageUpload() {
        if(filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(NguoiDung.this, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(NguoiDung.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded" +(int)progress+"%");
                        }
                    });
        }
    }
    private void HoSo(){
        String hoTen = edtHoTen.getText().toString();
        String diaChi = edtDiaChi.getText().toString();
        String gioiTinh = "";
        if(radNam.isChecked()){
            gioiTinh= radNam.getText().toString();
        }
        if(radNu.isChecked()){
            gioiTinh= radNu.getText().toString();
        }
        int soDienThoai = Integer.parseInt(edtSDT.getText().toString());
        HoSoCaNhan LS = new HoSoCaNhan(hoTen, diaChi, gioiTinh,soDienThoai);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("USers").child(userId).child("HoSoCaNhan").push().setValue(LS);
        Toast.makeText(this, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
    }
}