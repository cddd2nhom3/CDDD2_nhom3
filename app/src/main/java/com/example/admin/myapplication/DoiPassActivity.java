package com.example.admin.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoiPassActivity extends AppCompatActivity {

    private EditText edtPassOld , edtPassNew , edtRePassNew;
    private Button btnSub;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        edtPassOld = (EditText) findViewById(R.id.edtPassOld);
        edtPassNew = (EditText) findViewById(R.id.edtPassNew);
        edtRePassNew = (EditText) findViewById(R.id.edtRePassNew);
        btnSub = (Button) findViewById(R.id.btnSub);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getEmail();


                    AuthCredential credential = EmailAuthProvider
                            .getCredential(email, edtPassOld.getText().toString());
                if (edtPassNew.getText().toString().equals(edtRePassNew.getText().toString()) ) {

                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(edtPassNew.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(DoiPassActivity.this, "Something went wrong. Please try again later", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(DoiPassActivity.this, "Password Successfully Modified", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(DoiPassActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {

                    Toast.makeText(DoiPassActivity.this, "Re-Password not match passnew", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
