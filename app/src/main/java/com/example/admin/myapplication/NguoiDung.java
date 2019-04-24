package com.example.admin.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.admin.myapplication.base.BaseActivity;
import com.example.admin.myapplication.listener.RegisterListener;
import com.example.admin.myapplication.services.RegisterServices;
import com.example.admin.myapplication.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NguoiDung extends BaseActivity {

   private Button btnUpload;
   private ImageButton imgHS;

   private Uri filePath;

   private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        btnUpload = (Button)findViewById(R.id.btnUpload);
        imgHS = (ImageButton)findViewById(R.id.imgHSCN);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUpload();
            }
        });
        imgHS.setOnClickListener(new View.OnClickListener() {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK
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

    }
}