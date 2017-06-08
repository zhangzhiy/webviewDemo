package com.example.webviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by zzy on 2017/6/8.
 */
public class WebViewActivity1 extends FragmentActivity {

    WebView mX5WebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview1);
        mX5WebView = (WebView) findViewById(R.id.tbsContent);
        initWebView();
    }

    private void initWebView() {
        String url = "file:///android_asset/home.html";
        WebViewJsInterface1 jsInterface = new WebViewJsInterface1(this);
        mX5WebView.addJavascriptInterface(jsInterface, "Native");
        mX5WebView.loadUrl(url);
        mX5WebView.requestFocus();
        mX5WebView.setOnKeyListener(new View.OnKeyListener() { // webview can go back
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && mX5WebView.canGoBack()) {
                    mX5WebView.goBack();
                    return true;
                }
                return false;
            }
        });
        WebSettings webSettings = mX5WebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        mX5WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url);
                return false;
            }
        });
    }
}
