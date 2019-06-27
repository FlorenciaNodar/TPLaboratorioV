package com.example.fnodar.nodarflorencia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

public class DescriptionActivity extends AppCompatActivity implements ImageButton.OnClickListener {

    private WebView webView;
    ImageButton fab;
    Intent intent;
    String url;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        url = intent.getStringExtra("url");
        this.webView = (WebView) findViewById(R.id.webViewNoticia);
        WebSettings ws = this.webView.getSettings();
        ws.setJavaScriptEnabled(true);
        this.webView.loadUrl(url);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        fab = (ImageButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == fab.getId()){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Quiero compartirte esta noticia: \n" + url);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}
