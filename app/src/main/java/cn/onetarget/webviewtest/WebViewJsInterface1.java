package cn.onetarget.webviewtest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.io.InputStream;

public class WebViewJsInterface1 {

    private Context mContext;
    private Handler mHandler;
    private String account;
    private String[] remainExamIds;
    private int caseId;

    public WebViewJsInterface1(Context mContext, Handler mHandler) {
        this.mContext=mContext;
        this.mHandler = mHandler;
    }

    @JavascriptInterface
    public String getExam( ){
        String data;
        try {
            String startStr="{\"code\": \"200\",\"message\": \"获取量表数据成功\",\"data\": {\"examDetail\":";

            InputStream is =mContext.getAssets().open("paper_json_data.txt");

            String middleStr=Utils.getString(is);
            String endStr=",\"startCaseIndex\":0,\"isAllExamFinished\":0,\"isLastExam\":0}}";
            StringBuilder sb=new StringBuilder();

            sb.append(startStr)
                    .append(middleStr)
                    .append(endStr);

            data=sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            data="{\"code\": 500,\"message\": \"操作失败\",\"data\": \"获取量表失败\"}";
        }
        Message message=mHandler.obtainMessage();
        message.what= 1;
        message.obj="from html";
        mHandler.sendMessage(message);
        return data;
    }

    /**
     * 保存成功后需要给予回调
     * */
    @JavascriptInterface
    public String saveCaseAnswer(String text){
        String data="{\"code\": 200,\"message\": \"操作成功\",\"data\": \"保存成功\"}";
        Message message=mHandler.obtainMessage();
        message.what= 2;
        try {
            //
            Log.i("M3", "-saveCaseAnswer---text-----" + text);
            message.obj = "开始插入数据 (插入成功)";
        }catch (NumberFormatException e){
            e.printStackTrace();
            caseId=1;
            message.obj="只能是阿拉伯数字,默认按1进行插入数据";
        }catch (Exception e){
            e.printStackTrace();
            message.obj = "插入失败";
            data="{\"code\": 400,\"message\": \"操作失败\",\"data\": \"保存失败\"}";
        }finally {
            mHandler.sendMessage(message);
        }
        return data;
    }

}
