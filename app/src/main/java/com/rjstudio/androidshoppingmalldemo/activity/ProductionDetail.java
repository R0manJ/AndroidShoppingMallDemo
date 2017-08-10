package com.rjstudio.androidshoppingmalldemo.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.rjstudio.androidshoppingmalldemo.Contants;
import com.rjstudio.androidshoppingmalldemo.R;

import dmax.dialog.SpotsDialog;

public class ProductionDetail extends AppCompatActivity {

    private WebView wb;
    private WebAppInterface webInterface;

    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_detail);
        initView();

    }
    public void initView()
    {
        spotsDialog = new SpotsDialog(this);

        wb = (WebView) findViewById(R.id.wb);
        wb.loadUrl(Contants.API.WARES_DETAIL);
        wb.getSettings().setJavaScriptEnabled(true);
        webInterface = new WebAppInterface(this);
        wb.addJavascriptInterface(webInterface,"appInterface");
        wb.setWebViewClient(new CnWebClient());
        spotsDialog.show();

    }


    class WebAppInterface{
        private Context context;

        public WebAppInterface(Context context)
        {
            this.context = context;

        }
        @JavascriptInterface
        public void showDetail()
        {

        }


    }

    class CnWebClient extends WebViewClient
    {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webInterface.showDetail();
            spotsDialog.dismiss();
        }
    }
}
