package com.example.user.computerassembly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapActivity extends AppCompatActivity {
    private WebView w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        w=findViewById(R.id.webid);

        WebSettings web=w.getSettings();
        web.setJavaScriptEnabled(true);

        w.setWebViewClient(new WebViewClient());

        w.loadUrl("https://www.google.com/maps/dir/24.9101731,91.8858204/computer+shop+sylhet/@24.9060363,91.8855307,13z/data=!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3750552b0c0c7d2f:0x69ae0e692af7b067!2m2!1d91.8681052!2d24.8955923");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
