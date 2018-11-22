package com.example.liujinxing.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class EventBusTestActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_test_layout);
        tv = (TextView) findViewById(R.id.tv_eventbus);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送事件
                UserEvent bean = new UserEvent();
                bean.setName("Mr.sorrow");
                bean.setPassword("123456");
                EventBus.getDefault().post(bean);
                finish();
            }
        });
    }
}
