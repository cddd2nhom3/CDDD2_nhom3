package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.myapplication.base.BaseActivity;
import com.example.admin.myapplication.listener.RegisterListener;
import com.example.admin.myapplication.services.RegisterServices;
import com.example.admin.myapplication.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NguoiDung extends BaseActivity {

    @BindView(R.id.edtEmail)
    EditText tvEmail;
    @BindView(R.id.edtPassword)
    EditText tvPassword;
    private String email;
    private String passWord;
    private RegisterServices registerServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        ButterKnife.bind(this);
        registerServices = new RegisterServices(this);

        Button btnDangKyMoi = (Button) findViewById(R.id.btnDangKyMoi);
        btnDangKyMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputData()) {
                    showProgressDialog("Vui lòng đợi");
                    registerServices.registerAccount(email, passWord, new RegisterListener() {
                        @Override
                        public void registerSuccess() {
                            hideProgressDialog();
                            tvEmail.setText("");
                            tvPassword.setText("");
                            AlertDialog.Builder builder = new AlertDialog.Builder(NguoiDung.this);
                            builder.setTitle("Thông Báo");
                            builder.setMessage(getResources().getString(R.string.verifiation));
                            builder.create().show();
                        }

                        @Override
                        public void registerFailure(String message) {
                            hideProgressDialog();
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
}