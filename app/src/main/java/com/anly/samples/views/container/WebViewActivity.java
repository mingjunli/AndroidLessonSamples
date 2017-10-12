package com.anly.samples.views.container;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.anly.samples.R;
import com.anly.samples.common.activities.SampleActivityBase;

/**
 * Created on 2017/9/25.
 * Description:
 *
 * @author bianyue
 */
public class WebViewActivity extends SampleActivityBase {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.loadUrl("https://www.android.com/");
    }
}
