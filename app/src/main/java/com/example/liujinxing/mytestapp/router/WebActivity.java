package com.example.liujinxing.mytestapp.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.liujinxing.mytestapp.R;

@Route(path = "/com/WebActivity")
public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/schame-test.html");
    }
}
