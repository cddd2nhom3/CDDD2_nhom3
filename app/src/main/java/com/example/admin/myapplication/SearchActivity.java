package com.example.admin.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.admin.myapplication.Adapter.MyAdapter;
import com.example.admin.myapplication.Object.LichSu;
import com.example.admin.myapplication.Object.Link;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {
    static ArrayList<Link> link = new ArrayList<Link>();
    private MyAdapter adapter;
    private DatabaseReference mDatabase;
    private String userId;

    String strUrl = "https://careerbuilder.vn/viec-lam/";
    String Url1 = "c";
    String Url2 = "l";
    String Url3 = "-vi.html";
    String keyViecLam = "";
    String keyThanhPho = "";

    private Bundle mappings;
    private HashMap<String, String> hmViecLam;
    private HashMap<String, String> hmThanhPho;
    AutoCompleteTextView edtChucDanh, edtTenViecLam, edtTenTP;
    TextView txtNameOne, txtNameTwo;
    NavigationView navigationView;
    ListView lv;
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        navigationView = (NavigationView) findViewById(R.id.navidation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setupDrawerContent();
        setupDrawerContenta(navigationView);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        // Button btnHot = (Button)findViewById(R.id.btnHot);
        final AutoCompleteTextView edtTenViecLam = (AutoCompleteTextView) findViewById(R.id.edtNganhNghe);
        final AutoCompleteTextView edtChucDanh = (AutoCompleteTextView) findViewById(R.id.editChucDanh);
        final AutoCompleteTextView edtTenTP = (AutoCompleteTextView) findViewById(R.id.edtDiaDiem);

        txtNameOne = (TextView) findViewById(R.id.textView6);
        txtNameTwo = (TextView) findViewById(R.id.textView7);
        //hieu ung chuyen dong animation title
        Animation slideright = AnimationUtils.loadAnimation(SearchActivity.this, R.anim.slider_right);
        Animation slideleft = AnimationUtils.loadAnimation(SearchActivity.this, R.anim.slider_left);

        txtNameTwo.startAnimation(slideright);
        txtNameOne.startAnimation(slideleft);
        //edit ten viec lam
        edtTenViecLam.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }

            private void performSearch() {
                // TODO Auto-generated method stub
            }
        });
        //edt ten thanh pho
        edtTenTP.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    PerFormSearch();
                    return true;
                }
                return false;
            }

            private void PerFormSearch() {
                // TODO Auto-generated method stub
            }
        });
        // Ten Viec Lam , goi cac item o file string len
        String[] TenVL = getResources().getStringArray(R.array.TenViecLam);
        // Create thenadapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, TenVL);
        edtTenViecLam.setAdapter(adapter);
        String[] KeyTenVL = getResources()
                .getStringArray(R.array.KeyTenViecLam);

        // Ten Thanh Pho , goi cac item o file string len
        String[] TenTP = getResources().getStringArray(R.array.TenThanhPho);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, TenTP);
        edtTenTP.setAdapter(adapter1);
        String[] KeyTenTP = getResources().getStringArray(R.array.KeyThanhPho);

        hmThanhPho = new HashMap<String, String>();
        hmViecLam = new HashMap<String, String>();

        for (int i = 0; i < TenTP.length; i++) {
            hmThanhPho.put(TenTP[i], KeyTenTP[i]);

        }
        for (int i = 0; i < TenTP.length; i++) {
            hmViecLam.put(TenVL[i], KeyTenVL[i]);
        }
        final Bundle bundle = new Bundle();
        //btn search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String tenVLChon = edtTenViecLam.getText().toString();
                String tenTPChon = edtTenTP.getText().toString();
                String search_chucvu= edtChucDanh.getText().toString();
                String search_nganh = edtTenViecLam.getText().toString();
                String search_thanhpho = edtTenTP.getText().toString();
                LichSu LS = new LichSu(search_chucvu, search_nganh, search_thanhpho);
                mDatabase.child("USers").child(userId).child("SearchHistory").push().setValue(LS);

                Log.d("testTP",tenVLChon);
                Log.d("testVL",tenTPChon);
                // bat loi textedit
                if (TextUtils.isEmpty(tenVLChon)) {
                    edtTenViecLam.setError("chưa nhập");
                    edtTenTP.setError("chưa nhập");
                    return;
                }

                if (hmThanhPho.containsKey(tenTPChon))
                    keyThanhPho = hmThanhPho.get(tenTPChon);
                if (hmViecLam.containsKey(tenVLChon))
                    keyViecLam = hmViecLam.get(tenVLChon);
                Log.d("testTP", keyThanhPho);
                Log.d("testVL", keyViecLam);

                buiurl();

                Intent intent = new Intent(SearchActivity.this,
                        ListViewDSActivity.class);
                //Lấy link ghép buiurl
                bundle.putString("link", buiurl() + "");
                Log.d("aaa", bundle + "");
                //Lấy dữ liệu
                intent.putExtra("data", bundle);
                Log.d("aaa", bundle + "");
                //Bắt đầu chuyển layout
                startActivity(intent);
                finish();
            }

            //ghép chuỗi tạo url
            private String buiurl() {
                String url = strUrl + Url1 + keyViecLam + Url2 + keyThanhPho
                        + Url3;
                Log.d("testurl", url);
                return url;
            }

        });
    }


    /*public void onSuperBackPressed() {
        super.onBackPressed();
    }*/

    //Hỏi khi thoát
    public void outApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Log Out");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SearchActivity.this , MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("outANO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setupDrawerContent() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setupDrawerContenta(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        int id = menuItem.getItemId();

        //Noinspection SimplifiableIfStatement
        if (id == R.id.newhot) {
            Intent in = new Intent(SearchActivity.this, ListViewDSHotActivity.class);
            startActivity(in);

        } else if (id == R.id.test1) {
            Intent in = new Intent(SearchActivity.this, FirstFragment.class);
            startActivity(in);

        } else if (id == R.id.test2) {
            Intent in = new Intent(SearchActivity.this, SecondFragment.class);
            startActivity(in);

        } else if (id == R.id.test3) {
            Intent in = new Intent(SearchActivity.this, ThirdFragment.class);
            startActivity(in);

        } else if (id == R.id.test4) {

            outApp();

        } else if (id == R.id.lichSuTimKiem) {
            Intent in = new Intent(SearchActivity.this, ShowSearchHistory.class);
            startActivity(in);
        }
        else if (id == R.id.hoSoCaNhan) {
            Intent in = new Intent(SearchActivity.this, NguoiDung.class);
            startActivity(in);
        }else if (id == R.id.doiMatKhau) {
            Intent in = new Intent(SearchActivity.this, DoiPassActivity.class);
            startActivity(in);
        }
    }
}
