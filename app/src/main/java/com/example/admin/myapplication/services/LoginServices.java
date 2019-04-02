package com.example.admin.myapplication.services;

import android.support.annotation.NonNull;

import com.example.admin.myapplication.base.BaseFireBase;
import com.example.admin.myapplication.listener.LoginListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class LoginServices extends BaseFireBase {
    private FirebaseAuth auth;

    public LoginServices() {
        auth = getFirebaseAuth();
    }

    /**
     * Xác Thực Tài Khoản
     * @param email
     * @param passWord
     * @param listener
     */
    public void loginAccountEmail(String email, String passWord, final LoginListener listener) {
        auth.signInWithEmailAndPassword(email, passWord)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.loginFailure(e.getMessage());
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
               listener.loginSuccess();
            }
        });
    }
}
