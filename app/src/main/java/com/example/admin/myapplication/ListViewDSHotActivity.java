package com.example.admin.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admin.myapplication.Adapter.MyAdapterHot;
import com.example.admin.myapplication.Object.Link;
import com.example.admin.myapplication.Object.ThuocTinhHot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
public class ListViewDSHotActivity extends Activity{
    // URL Address
    String url = "https://careerbuilder.vn/vi/";
    static ArrayList<ThuocTinhHot> questions = new ArrayList<ThuocTinhHot>();
    ListView lvDanhSach;
    MyAdapterHot adapter;
    static ArrayList<Link> arrlink = new ArrayList<Link>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imgBack = (ImageView) findViewById(R.id.btnBack);
        lvDanhSach = (ListView) findViewById(R.id.lvdanhsach);
        getAndUpdateLink();
        new _JSOUP().execute();

        imgBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) { // TODO Auto-generated method stub
                Intent intent = new Intent(ListViewDSHotActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    void getAndUpdateLink() {
        Intent intent = getIntent();
        // Log.d("intent", "intent " + intent);
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("data");
            Log.d("Phát", bundle + "");
            if (bundle != null) {
                Link link = new Link(bundle.getString("link"));
                Log.d("dữ liệu đã lấy", link + "");
                url = link + "";
            } else {
                arrlink.clear();
            }
        }
    }
    private class _JSOUP extends AsyncTask<Void, Integer, ArrayList<ThuocTinhHot>> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            dialog = new ProgressDialog(ListViewDSHotActivity.this);
            dialog.setMessage("loading...");
            Log.d("Phát 1", dialog + "");
            dialog.show();

        }

        @Override
        protected ArrayList<ThuocTinhHot> doInBackground(Void... arg0) {
            questions = new ArrayList<ThuocTinhHot>();

            // TODO Auto-generated method stub
            try {
//                 Log.d("đã vào", "đã vào tới đây");
                Document doccument = Jsoup.connect(url)
                        .timeout(80000)
                        .maxBodySize(1024*1024*10)
                        .get();
                Elements tieude = doccument.select("p.jobtitle");
//                Log.d("aaa", tieude + "");
                Elements tencty = doccument.select("p.jobcompany");


                Elements url2 = doccument.select("p.jobtitle");

                for (int i = 0; i < url2.size() && i < tieude.size()
                        && i < tencty.size(); i++) {

                    ThuocTinhHot question = new ThuocTinhHot("\n" + tieude.get(i).text(),
                            "\n" + tencty.get(i).text(), "\n"
                            + url2.get(i).select("a").attr("href"));
                    questions.add(question);
                }
            } catch (Exception e) {
                // TODO: handle exception
                // Log.d("đã vào tới đây 1", "đã vào tới đây 1");
            }
            return questions;
            // TODO Auto-generated method stub
        }


        @Override
        protected void onPostExecute(ArrayList<ThuocTinhHot> result) {

            super.onPostExecute(result);
            Log.d("Phát", result.size()+"");

            lvDanhSach = (ListView) findViewById(R.id.lvdanhsach);
            adapter = new MyAdapterHot(ListViewDSHotActivity.this,
                    R.layout.item_hot_main, result);
            lvDanhSach.setAdapter(adapter);
            lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent intent = new Intent(ListViewDSHotActivity.this,
                            WebviewActivity.class);
                    String link = questions.get(position).getLink();
                    Bundle bundle = new Bundle();
                    bundle.putString("link", link);
                    intent.putExtra("dataLink", bundle);
                    startActivity(intent);
                }
            });
            dialog.dismiss();

        }
    }
}
