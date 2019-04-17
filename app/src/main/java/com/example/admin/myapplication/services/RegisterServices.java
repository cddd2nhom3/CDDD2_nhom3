package com.example.admin.myapplication.services;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.admin.myapplication.Object.NguoiDung;
import com.example.admin.myapplication.base.BaseFireBase;
import com.example.admin.myapplication.Object.Users;
import com.example.admin.myapplication.define.Constants;
import com.example.admin.myapplication.listener.RegisterListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class RegisterServices extends BaseFireBase {
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Activity activity;

    public RegisterServices(Activity activity) {
        this.activity = activity;
        auth = getFirebaseAuth();
        mDatabase = getDatabaseReference();
    }

    /**
     * Đăng lý tài khoản bằng Email
     *
     * @param email
     * @param passWord
     * @param listener
     */
    public void registerAccount(String email, String passWord, final RegisterListener listener) {
        auth.createUserWithEmailAndPassword(email, passWord)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final FirebaseUser userFB = task.getResult().getUser();
                            if (userFB != null) {
                                //Gửi 1 email xác thực tài khoản
                                userFB.sendEmailVerification().addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            //Tiến hành thông tin user vào Database
                                            NguoiDung nguoidung = new NguoiDung();
                                            nguoidung.setUid(userFB.getUid());
                                            nguoidung.setEmail(userFB.getEmail());
                                            //nguoidung.setHoTen(userFB.getH);
                                            createAccountInDatabase(nguoidung, new RegisterListener() {
                                                @Override
                                                public void registerSuccess() {
                                                    auth.signOut(); // Đăng xuất.
                                                    listener.registerSuccess();
                                                }

                                                @Override
                                                public void registerFailure(String message) {
                                                    listener.registerFailure(message);
                                                }
                                            });

                                        }
                                    }
                                });
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.registerFailure(e.getMessage());
            }
        });

    }

    /**
     * Lưu thông tin user
     *
     * @param nguoidung
     */
    /*public void createAccountInDatabase(Users users, final RegisterListener listener) {
        mDatabase.child(Constants.USERS)
                .child(users.getUid())
                .setValue(users)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.registerSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.registerFailure(e.getMessage());
            }
        });
    }
*/
    public void createAccountInDatabase(NguoiDung nguoidung, final RegisterListener listener) {
        mDatabase.child(Constants.NGUOIDUNG)
                .child(nguoidung.getUid())
                .setValue(nguoidung)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.registerSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.registerFailure(e.getMessage());
            }
        });
    }
}
