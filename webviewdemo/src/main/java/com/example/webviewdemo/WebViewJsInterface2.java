package com.example.webviewdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class WebViewJsInterface2 {

    private Context mContext;
    private Handler mHandler;

    public WebViewJsInterface2(Context mContext, Handler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
    }

    /**
     * web从本地获取数据
     */
    @JavascriptInterface
    public void getExam(String text) {
        String data = "{\"code\": \"200\",\"message\": \"上传数据\"}";
        Message message = mHandler.obtainMessage();
        message.what = 1;
        message.obj = data;
        mHandler.sendMessage(message);
    }

    /**
     * 本地获取web数据，并给予回调
     */
    @JavascriptInterface
    public void saveCaseAnswer(String text) {
        String data = "{\"code\": 200,\"message\": \"获取数据成功\"}";
        try {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            data = "{\"code\": 400,\"message\": \"获取数据失败\"}";
        } finally {
            Message message = mHandler.obtainMessage();
            message.what = 2;
            message.obj = data;
            mHandler.sendMessage(message);
        }

    }

}
