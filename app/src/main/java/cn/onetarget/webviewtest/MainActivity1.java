package cn.onetarget.webviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class MainActivity1 extends AppCompatActivity {


    WebView mX5WebView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  1:
                case  2:
                    if(msg.obj!=null)
                        Toast.makeText(MainActivity1.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mX5WebView=(WebView)findViewById(R.id.tbsContent);
        initWebView();
    }
    private void initWebView() {
        String url = "http://192.168.20.178/pages/front/liangbiao/main.html";
//                String url = "file:///android_asset/home.html";

        WebViewJsInterface1 jsInterface=new WebViewJsInterface1(this,mHandler);
        mX5WebView.addJavascriptInterface(jsInterface, "Native");
//        mX5WebView.addJavascriptInterface(jsInterface, "");
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
