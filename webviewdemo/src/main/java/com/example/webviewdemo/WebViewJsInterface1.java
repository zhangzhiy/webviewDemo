package com.example.webviewdemo;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebViewJsInterface1 {

    private Context mContext;

    public WebViewJsInterface1(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * web从本地获取数据
     */
    @JavascriptInterface
    public String getExam() {
        String data = "{\"code\": \"200\",\"message\": \"上传数据\"}";
        return data;
    }

    /**
     * 本地获取web数据，并给予回调
     */
    @JavascriptInterface
    public String saveCaseAnswer(String text) {
        String data = "{\"code\": 200,\"message\": \"获取数据成功\"}";
        try {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            data = "{\"code\": 400,\"message\": \"获取数据成功\"}";
        }
        return data;
    }

}
