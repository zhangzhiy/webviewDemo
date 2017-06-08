package com.example.webviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btMode1;
    private Button btMode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btMode1 = (Button) findViewById(R.id.bt_mode1);
        btMode2 = (Button) findViewById(R.id.bt_mode2);
        btMode1.setOnClickListener(this);
        btMode2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_mode1:
                intent = new Intent(this, WebViewActivity1.class);
                startActivity(intent);
                break;
            case R.id.bt_mode2:
                intent = new Intent(this, WebViewActivity2.class);
                startActivity(intent);
                break;
        }
    }
}
