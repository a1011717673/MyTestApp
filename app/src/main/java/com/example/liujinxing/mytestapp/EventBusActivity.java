package com.example.liujinxing.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends Activity {

    private TextView tv;
    private TextView showTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_layout);
        tv = (TextView) findViewById(R.id.tv_eventbus);
        showTv = (TextView) findViewById(R.id.tv_show);
        //采用双重校验并加锁的单例模式生成EventBus实例
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserEvent userEvent){
        showTv.setText(userEvent.getName());
    }
}
