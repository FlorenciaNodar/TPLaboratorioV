package com.example.fnodar.nodarflorencia;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
public class DescriptionActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        String url = this.getIntent().getStringExtra("url");
        this.webView = (WebView) findViewById(R.id.webViewNoticia);
        WebSettings ws = this.webView.getSettings();
        ws.setJavaScriptEnabled(true);
        this.webView.loadUrl(url);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

}
