package com.example.admin.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;



public class WebviewActivity extends Activity{
    WebView webview = null;
    String link = "";
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chi_tiet);

        btnBack = (ImageButton) findViewById(R.id.imgBack);
        webview = (WebView) findViewById(R.id.webview);

        //buttom back <-
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("dataLink");
            if (bundle != null) {
                link = bundle.getString("link");
//                Toast.makeText(getApplicationContext(), link,
//                Toast.LENGTH_LONG).show();
                Log.d("web", link+"");
            }

        }

        if (savedInstanceState != null) {
            ((WebView) findViewById(R.id.webview)).restoreState(savedInstanceState);
        } else {
            webview.getSettings().getJavaScriptEnabled();
            webview.loadUrl(link);
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view,
                                                        String url) {
                    webview.setBackgroundColor(Color.TRANSPARENT);
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        webview.saveState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
