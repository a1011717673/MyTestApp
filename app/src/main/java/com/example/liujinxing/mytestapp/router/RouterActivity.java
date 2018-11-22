package com.example.liujinxing.mytestapp.router;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liujinxing.mytestapp.R;
import com.example.liujinxing.mytestapp.router.service.IService;

public class RouterActivity extends AppCompatActivity implements View.OnClickListener {

    @Autowired(name = "/service/hello")
    IService service;

    @Autowired(name = "/service/hello2")
    IService service2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);

        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                String url = "";
//                url = "/com/RActivity2";
                //1."/com/CustomGroupActivity"实际路径可以跳转，不需要在前面加组名
                url = "/com/Activity1";
                router(url);
                break;
            case R.id.bt2:
                //自定义路由分组，发起路由
                ARouter.getInstance().build("/com/CustomGroupActivity", "customGroup").navigation();
                //@Route(path = "/com/Activity1",name = "测试类1")
                //1、1正常 2无法跳转  2、12 21 1/2 如果有其他的跳转到相同的path，则再点击可以跳转到其他的路径

                //path相同有分组  @Route(path = "/com/Activity1",name = "测试类1")  @Route(path = "/com/Activity1",group = "customGroup")
                //1.单独点击分别跳转到对应页面
                //1.点击12 "/com/Activity1" 2.点击21 点击分组然后点击不分组页面正确
                break;
            case R.id.bt3:
                //加载本地html
                ARouter.getInstance().build("/com/WebActivity").navigation();
                break;

            case R.id.bt4:
                //暴露服务
                ARouter.getInstance().inject(this);
                service.sayHello(this);
                break;

            case R.id.bt5:
                //暴露服务
                ARouter.getInstance().inject(this);
                service2.sayHello(this);
                break;
        }
    }

    void router(String url) {
        ARouter.getInstance()
                .build(url)
                .withString("key", "value:123")//可以传递大量参数
                .navigation(this, new NavCallback() {

                    @Override
                    public void onFound(Postcard postcard) {
                        super.onFound(postcard);
                        Log.e("zhao", "onArrival: 找到了 ");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        super.onLost(postcard);
                        Log.e("zhao", "onArrival: 找不到了 ");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        String group = postcard.getGroup();
//                        Log.e("zhao", "分组是: " + group);
                        Log.e("zhao", "onArrival: 跳转完了 ");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                        Log.e("zhao", "onArrival: 被拦截了 ");
                    }
                });
    }

}
