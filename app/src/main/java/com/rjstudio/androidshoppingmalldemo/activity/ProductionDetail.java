package com.rjstudio.androidshoppingmalldemo.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.rjstudio.androidshoppingmalldemo.R;

public class ProductionDetail extends AppCompatActivity {

    private WebView wb;
    private WebAppInterface webInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_detail);
        initView();

    }
    public void initView()
    {
        wb = (WebView) findViewById(R.id.wb);
        wb.loadUrl("file:///android_asset/index.html");
        wb.getSettings().setJavaScriptEnabled(true);
        webInterface = new WebAppInterface(this);
        wb.addJavascriptInterface(webInterface,"app");
        Button bu_addToCart = (Button) findViewById(R.id.bu_addToCart);
        bu_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webInterface.setName();
                
            }
        });
    }


    class WebAppInterface{
        private Context context;
        public WebAppInterface(Context context)
        {
            this.context = context;

        }

        @JavascriptInterface
        public void getToast(String name)
        {
            Toast.makeText(context, "From html value :"+name, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void setName()
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wb.loadUrl("javascript:setName('"+"Android"+"')");


                }
            });
        }
    }
}
