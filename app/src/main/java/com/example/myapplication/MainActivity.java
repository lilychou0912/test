package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JsResult;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = (WebView) findViewById(R.id.mapWebview);
        //点击底部按钮
        bottomListener();
        //初始化界面，载入已标记地图
        //退出当前Activity或者跳转到新Activity时被调用
        WebSettings webSettings=myWebView.getSettings();
        //允许JavaScript执行
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("GBK");
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.setWebChromeClient(new WebChromeClient()
        {

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result)
            {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }

        });
        // 添加一个对象, 让javascript可以访问该对象的方法,
        myWebView.addJavascriptInterface(new WebAppInterface(this),
                "myInterfaceName");

        // 载入页面：本地html资源文件，放在assets文件夹下
        myWebView.loadUrl("file:///android_asset/html/map.html");
        //设置 缓存模式
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        myWebView.getSettings().setDomStorageEnabled(true);
        //这样你就可以在返回前一个页面的时候不刷新了
    }

        //对底部按钮的响应
        private void bottomListener() {
            ImageButton NtOverviewBtn = (ImageButton) findViewById(R.id.button_edit);
            ImageButton MapBtn = (ImageButton) findViewById(R.id.button_map);
            ImageButton SearchBtn = (ImageButton) findViewById(R.id.button_search);
            ImageButton CollectBtn = (ImageButton) findViewById(R.id.button_collect);
            //给btn1绑定监听事件
            NtOverviewBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 给bnt添加点击响应事件
                    Intent intent = new Intent(MainActivity.this, NoteEditActivity.class);
                    //启动
                    startActivity(intent);
                }
            });
            MapBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 给bnt添加点击响应事件
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    //启动
                    startActivity(intent);
                }
            });
            SearchBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 给bnt添加点击响应事件
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    //启动
                    startActivity(intent);
                }
            });
            CollectBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 给bnt添加点击响应事件
                    Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                    //启动
                    startActivity(intent);
                }
            });
        }

    class WebAppInterface{
        Context mContext;
        WebAppInterface(Context c){
            mContext=c;
        }
        public void showToast(String toast){
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();            }
    }

}
