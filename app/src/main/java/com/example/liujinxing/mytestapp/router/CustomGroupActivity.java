package com.example.liujinxing.mytestapp.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.liujinxing.mytestapp.R;

/**
 *  自定义路由组
 */

@Route(path = "/com/CustomGroupActivity",group = "customGroup")//, group = "customGroup"
public class CustomGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_group);
    }
}
