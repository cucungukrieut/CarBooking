package com.inducesmile.taxirental;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inducesmile.taxirental.utils.Constants;
import com.inducesmile.taxirental.utils.Helper;

public class WebActivity extends AppCompatActivity {

    private static final String TAG = WebActivity.class.getSimpleName();

    private WebView webView;
    private WebSettings webSettings;

    private String filename;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        filename = getRequiredPage();

        webView = (WebView)findViewById(R.id.webview);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(true);

        webView.setWebViewClient(new CustomWebView());

        //prepare external url to load
        String remoteUrl = Constants.REMOTE_PATH + filename;
        if(!remoteUrl.contains("http")){
            Helper.displayErrorMessage(this, "Invalid url");
        }else{
            webView.loadUrl(remoteUrl);
        }
    }

    private String getRequiredPage(){
        return getIntent().getExtras().getString("INFORMATION");
    }

    private class CustomWebView extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
