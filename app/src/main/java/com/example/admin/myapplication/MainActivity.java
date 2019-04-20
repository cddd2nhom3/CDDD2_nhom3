package com.example.admin.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.myapplication.base.BaseActivity;
import com.example.admin.myapplication.listener.LoginListener;
import com.example.admin.myapplication.services.LoginServices;
import com.example.admin.myapplication.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_username)
    EditText tvUsername;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String email;
    private String passWord;
    private LoginServices loginServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginServices = new LoginServices();
        mAuth =  FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser userFB = firebaseAuth.getCurrentUser();
                if (userFB != null) {
                    //Kiểm tra users đã active hay chưa
                    if (userFB.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this,SearchActivity.class));
                        finish();
                    } else {
                        mAuth.signOut();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Thông Báo");
                        builder.setMessage(getResources().getString(R.string.verifiation));
                        builder.setIcon(R.drawable.user);
                        builder.create().show();
                    }
                }

            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @OnClick({R.id.btn_login_email, R.id.btn_fb_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_email:
                if (checkInputData()) {
                    showProgressDialog("Đăng nhập...");
                    loginServices.loginAccountEmail(email, passWord, new LoginListener() {
                        @Override
                        public void loginSuccess() {
                            hideProgressDialog();
                            Toast.makeText(MainActivity.this, getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void loginFailure(String message) {
                            hideProgressDialog();
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.btn_fb_login:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.tv_register:
                startActivity( new Intent(this, SignUp.class));
                break;
            case R.id.quenMatKhau:
                startActivity(new Intent(this, QuenMatKhau.class));
                break;
        }
    }
    public void onBackPressed(){
        outApp();
    }
    public void outApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                finish();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    /**
     * Kiểm tra dữ liệu
     * @return
     */
    private boolean checkInputData() {
        if (Utils.isEmpty(tvUsername) && Utils.isEmpty(tvPassword)) {
            email = tvUsername.getText().toString().trim();
            passWord = tvPassword.getText().toString().trim();
            if (!Utils.isEmailValid(email)) {
                tvUsername.requestFocus();
                tvUsername.setError(getResources().getString(R.string.email_error));
                return false;
            } else {
                if (passWord.length() < 6) {
                    tvPassword.requestFocus();
                    tvPassword.setError(getResources().getString(R.string.pass_erro));
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
