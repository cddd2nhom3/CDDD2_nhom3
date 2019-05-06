package com.example.admin.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.myapplication.Object.HoSoCaNhan;

import com.example.admin.myapplication.services.RootActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class ShowPersonInformation extends RootActivity {
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
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
       /* GlideApp.with(this *//* context *//*)
                .load(storageReference)
                .into(imageView);*/
        imgHSCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReferenceFromUrl("gs://goodjobs-9ad88.appspot.com").child("*.jpg");

                //get download file url
                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.i("Main", "File uri: " + uri.toString());
                    }
                });

                //download the file
                try {
                    showProgressDialog("Download File", "Downloading File...");
                    final File localFile = File.createTempFile("images", "jpg");
                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            imgHSCN.setImageBitmap(bitmap);
                            dismissProgressDialog();
                            showToast("Download successful!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            dismissProgressDialog();
                            showToast("Download Failed!");
                        }
                    });
                } catch (IOException e ) {
                    e.printStackTrace();
                    Log.e("Main", "IOE Exception");
                }
            }
        });


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
