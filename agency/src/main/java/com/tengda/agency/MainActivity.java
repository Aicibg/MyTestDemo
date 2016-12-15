package com.tengda.agency;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;

public class MainActivity extends AppCompatActivity {
    private Button mButton,mDynamic;
    private WebView mWebView;
    private BusinessAgent mBusinessAgent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= (Button) findViewById(R.id.bt_agency);
        mDynamic= (Button) findViewById(R.id.bt_dynamic_proxy);
        mWebView= (WebView) findViewById(R.id.web);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBusinessAgent=new BusinessAgent(new Vendor());
                mBusinessAgent.sell();
                mBusinessAgent.add();
                try {
                    InputStream file=getResources().getAssets().open("nitouche.docx");
//                    WordExcelToHtml.getWordAndStyle("file:///android_asset/nitouche.docx");
//                    WordExcelToHtml.getWordAndStyle(file);
                    String word=WordUtils.readDoc(file);
                    Log.e("test",word);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建中介类实例
                DynamicProxy inter=new DynamicProxy(new Vendor());
                //产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
                System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
                //获取代理类实例
                Sell sell= (Sell) (java.lang.reflect.Proxy.newProxyInstance(Sell.class.getClassLoader(),new
                Class[]{Sell.class},inter));
                sell.sell();
                sell.add();
                mWebView.loadUrl(Environment.getExternalStorageDirectory().getPath()+"WordToHtml"+"abc.html");
            }
        });
    }
}
