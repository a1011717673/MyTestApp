package com.example.liujinxing.mytestapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.example.liujinxing.mytestapp.listview.RefreshListActivity;
import com.example.liujinxing.mytestapp.router.RouterActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    TextView tv1;
    //    TextView eventbusTv;
    WebView web1;
    private String downUrl = "http://192.168.99.94/%E4%BF%A1%E6%81%AF.txt";
    private String downpptUrl = "http://192.168.99.94/%E6%99%BA%E8%83%BD%E4%BA%A7%E5%93%81%E5%A5%97%E8%A3%852018.10.05.pptx";
    String jsonUrl = "http://192.168.99.94/test.json";
    Class<?> class1 = null;
    Class<?> class2 = null;
    Class<?> class3 = null;

//    @BindView(R.id.tv_router1)
//    private TextView mRouterTv;
//
//    @BindView(R.id.tv_eventbus1)
//    private TextView eventbusTv;


//    @OnClick({R.id.tv_eventbus, R.id.tv_router})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_eventbus:
//                Intent intent = new Intent();
//                intent.setClass(this,EventBusTestActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.tv_router:
//
//                break;
//            default:
//                break;
//        }
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tv_eventbus:
//                Intent intent = new Intent();
//                intent.setClass(this,EventBusTestActivity.class);
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tv1 = (TextView) findViewById(R.id.textView);
//        eventbusTv = (TextView) findViewById(R.id.tv_eventbus);
        web1 = (WebView) findViewById(R.id.web);
//        eventbusTv.setOnClickListener(this);

        try {
            class1 = Class.forName("hai.haiapp.EventBusActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        class2 = new EventBusActivity().getClass();
        class3 = EventBusActivity.class;
//        eventbusTv.setOnClickListener(this);
//        mRouterTv.setOnClickListener(this);
//        System.out.println("类名称1："+class1.getName());
//        System.out.println("类名称2："+class2.getName());
//        System.out.println("类名称3："+class3.getName());

//        web1.loadUrl("http://www.baidu.com/");
        PermisionUtils.verifyStoragePermissions(this);
        EventBus.getDefault().register(this);
        HttpUtils.doGet(jsonUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        tv1.setText("失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        is = response.body().byteStream();
                        File file = new File(isExistDir("down"), ".json");
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
//                    openFile(context,file);

//                                try {
//                                    InputStreamReader isr = new InputStreamReader(getAssets().open("testjson.json"),"UTF-8");
//                                    BufferedReader br = new BufferedReader(isr);
//                                    String line;
//                                    StringBuilder builder = new StringBuilder();
//                                    while((line = br.readLine()) != null){
//                                        builder.append(line);
//                                    }
//                                    br.close();
//                                    isr.close();
//                                    JSONObject testjson = new JSONObject(builder.toString());//builder读取了JSON中的数据。
//                                    //直接传入JSONObject来构造一个实例
//                                    JSONArray array = testjson.getJSONArray("role");         //从JSONObject中取出数组对象
//                                    for (int i = 0; i < array.length(); i++) {
//                                        JSONObject role = array.getJSONObject(i);    //取出数组中的对象
//                                        tv1.append(role.getString("name") + ": ");  //取出数组中对象的各个值
//                                        tv1.append(role.getString("say") + "\n");
//                                    }//
//
////                                    text.append("now the " +testjson.getString("dog") + " is here");
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
////                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
////                Log.e("kkk","失败");
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        if (response.isSuccessful()) {
//                            InputStream is = null;
//                            byte[] buf = new byte[2048];
//                            int len = 0;
//                            FileOutputStream fos = null;
//                            try {
//                                is = response.body().byteStream();
//                                File file = new File(isExistDir("down"),".json");
//                                fos = new FileOutputStream(file);
//                                while ((len = is.read(buf)) != -1) {
//                                    fos.write(buf, 0, len);
//                                }
//                                fos.flush();
////                    openFile(context,file);
//
////                                try {
////                                    InputStreamReader isr = new InputStreamReader(getAssets().open("testjson.json"),"UTF-8");
////                                    BufferedReader br = new BufferedReader(isr);
////                                    String line;
////                                    StringBuilder builder = new StringBuilder();
////                                    while((line = br.readLine()) != null){
////                                        builder.append(line);
////                                    }
////                                    br.close();
////                                    isr.close();
////                                    JSONObject testjson = new JSONObject(builder.toString());//builder读取了JSON中的数据。
////                                    //直接传入JSONObject来构造一个实例
////                                    JSONArray array = testjson.getJSONArray("role");         //从JSONObject中取出数组对象
////                                    for (int i = 0; i < array.length(); i++) {
////                                        JSONObject role = array.getJSONObject(i);    //取出数组中的对象
////                                        tv1.append(role.getString("name") + ": ");  //取出数组中对象的各个值
////                                        tv1.append(role.getString("say") + "\n");
////                                    }//
////
//////                                    text.append("now the " +testjson.getString("dog") + " is here");
////                                } catch (Exception e) {
////                                    e.printStackTrace();
////                                }
//
//
//
//
//
//
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } finally {
//                                if (is != null) {
//                                    try {
//                                        is.close();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                                if (fos != null) {
//                                    try {
//                                        fos.close();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                        }
//                            String bodyInfo = "";
//                            try {
//                                ResponseBody body = response.body();
//                                bodyInfo = inputStream2String(body.byteStream());
//                                body.close();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            int code = response.code();//获取响应码
//                            String message = response.message();//获取响应消息
//                            ResponseBody body = response.body();//获取响应体
//                            Gson gson = new Gson();
//                            String jk = body.toString();
////                            BaiduBean item  = gson.fromJson(body.toString(),BaiduBean.class);
//
//                            InputStream inputStream = body.byteStream();//获取输入流
//                            try {
//                                byte[] bytes = body.bytes();//获取字节数组
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                String str = body.string();//获取字符串数据
////                                Gson gson = new Gson();
////                                BaiduBean item  = gson.fromJson(str,BaiduBean.class);
//                                web1.loadUrl(str);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                            Log.d("--HttpClient", "--body:" + bodyInfo);
////                            callback.onResponse(bodyInfo);
//                        } else {
////                            callback.onFailure(response.code());
//                        }

//                        if (!response.isSuccessful()) {
//                            return;
//                        }
//                        String json =response.body().string();
//                        parse
//                        try {
//                            tv1.setText(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

//                    }
                }


            }
        });
        TextView testTv = (TextView) findViewById(R.id.tv_test);
        testTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = getFileFromSD("/storage/emulated/0/down/.json");
                //                    JSONObject testjson = new JSONObject(result);
//                    String u = "";
                Gson gson = new Gson();
//            String jk = body.toString();
                List<User> item = gson.fromJson(result, new TypeToken<List<User>>() {
                }.getType());
                String u1 = "";
                ;//builder读取了JSON中的数据。
            }
        });
//        //文件下载
//        try {
//            HttpUtils.downFile(downpptUrl,isExistDir("down"),".ppt",this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    public static String getJson(Context context, String fileName) {
        String resultString = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString = new String(buffer, "UTF-8");
        } catch (Exception e) {


        }
        return resultString;
    }

    private String getFileFromSD(String path) {
        String result = "";

        try {
            FileInputStream f = new FileInputStream(path);
            BufferedReader bis = new BufferedReader(new InputStreamReader(f));
            String line = "";
            while ((line = bis.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String getJson(String fileName, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    /**
     * @param saveDir * @return * @throws IOException * 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }


    public String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    private void openPDF(File file) {
        if (file.exists()) {
            Log.d("打开", "打开");
            Uri path1 = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path1, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.d("打开失败", "打开失败");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTestEvent(UserEvent userEvent) {
        tv1.setText(userEvent.getName());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //    @OnClick({R.id.tv_eventbus, R.id.tv_router})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_eventbus:
//                Intent intent = new Intent();
//                intent.setClass(this,EventBusTestActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.tv_router:
//
//                break;
//            default:
//                break;
//        }
//    }
    @OnClick({R.id.tv_eventbus1, R.id.tv_router1,R.id.tv_lv,R.id.tv_refresh_lv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_eventbus1:
                Intent intent = new Intent();
                intent.setClass(this, EventBusTestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_router1:
                Intent intent1 = new Intent();
                intent1.setClass(this, RouterActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_lv:
                Intent intent2 = new Intent();
                intent2.setClass(this, ListViewDelDemoActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_refresh_lv:
                Intent intent3 = new Intent();
                intent3.setClass(this, RefreshListActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
