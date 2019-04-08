package com.example.admin.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.myapplication.base.BaseActivity;
import com.example.admin.myapplication.listener.RegisterListener;
import com.example.admin.myapplication.services.RegisterServices;
import com.example.admin.myapplication.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;

public class NguoiDung extends AppCompatActivity {

    private EditText tvEmail;
    private EditText tvPassword;
    private Button btnDangKyMoi;
    private String email;
    private String passWord;
    private RegisterServices registerServices;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);
        registerServices = new RegisterServices(this);

        btnDangKyMoi = (Button) findViewById(R.id.btnDangKyMoi);
        tvEmail = (EditText) findViewById(R.id.edtEmail);
        tvPassword = (EditText) findViewById(R.id.edtPassword);
        btnDangKyMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputData()) {
                    //showProgressDialog("Vui lòng đợi");
                    registerServices.registerAccount(email, passWord, new RegisterListener() {
                        @Override
                        public void registerSuccess() {
                            //hideProgressDialog();
                            tvEmail.setText("");
                            tvPassword.setText("");
                            AlertDialog.Builder builder = new AlertDialog.Builder(NguoiDung.this);
                            builder.setTitle("Thông Báo");
                            builder.setMessage(getResources().getString(R.string.verifiation));
                            builder.create().show();
                        }

                        @Override
                        public void registerFailure(String message) {
                            //hideProgressDialog();
                            Toast.makeText(NguoiDung.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * Kiểm tra dữ liệu
     * @return
     */
    private boolean checkInputData() {
        if (Utils.isEmpty(tvEmail) && Utils.isEmpty(tvPassword)) {
            email = tvEmail.getText().toString().trim();
            passWord = tvPassword.getText().toString().trim();
            if (!Utils.isEmailValid(email)) {
                tvEmail.requestFocus();
                tvEmail.setError(getResources().getString(R.string.email_error));
                return false;
            } else {
                if (passWord.length() < 6) {
                    tvEmail.requestFocus();
                    tvEmail.setError(getResources().getString(R.string.pass_erro));
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(NguoiDung.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }

            // ...
        }
    });*/
}