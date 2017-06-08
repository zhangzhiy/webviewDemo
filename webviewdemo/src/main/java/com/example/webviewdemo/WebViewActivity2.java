package com.example.webviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by zzy on 2017/6/8.
 */



public class WebViewActivity2 extends AppCompatActivity {


    WebView mX5WebView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            doHandleMessage(msg);
            super.handleMessage(msg);
        }
    };

    private void doHandleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                String data = (String) msg.obj;
                String jsString = "getExamCallback('" + data + "')";
                mX5WebView.evaluateJavascript(jsString, null);
                break;
            case 2:
                String data1 = (String) msg.obj;
                String jsString1 = "saveAnswerCallback('" + data1 + "')";
                mX5WebView.evaluateJavascript(jsString1, null);
                break;
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview1);
        mX5WebView=(WebView)findViewById(R.id.tbsContent);
        initWebView();
    }
    private void initWebView() {
        String url = "file:///android_asset/home2.html";
        WebViewJsInterface2 jsInterface=new WebViewJsInterface2(this,mHandler);
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
                return false;
            }
        });
    }
}