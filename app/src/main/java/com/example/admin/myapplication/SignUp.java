package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.myapplication.base.BaseActivity;
import com.example.admin.myapplication.listener.RegisterListener;
import com.example.admin.myapplication.services.RegisterServices;
import com.example.admin.myapplication.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends BaseActivity {

    @BindView(R.id.tv_email)
    EditText tvEmail;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.layout)
    LinearLayout layout;
    private String email;
    private String passWord;
    private RegisterServices registerServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        registerServices = new RegisterServices(this);
    }

    @OnClick({R.id.btn_login_email, R.id.btn_back_login, R.id.btn_reset_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_email:
                if (checkInputData()) {
                    showProgressDialog("Vui lòng đợi");
                    registerServices.registerAccount(email, passWord, new RegisterListener() {
                        @Override
                        public void registerSuccess() {
                            hideProgressDialog();
                            tvEmail.setText("");
                            tvPassword.setText("");
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                            builder.setTitle("Thông Báo");
                            builder.setMessage(getResources().getString(R.string.verifiation));
                            builder.setIcon(R.drawable.user);
                            builder.create().show();
                        }

                        @Override
                        public void registerFailure(String message) {
                            hideProgressDialog();
                            Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.btn_back_login:
                finish();
                break;
            case R.id.btn_reset_password:
                break;
        }
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
}