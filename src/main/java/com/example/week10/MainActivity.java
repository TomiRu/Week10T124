package com.example.week10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.mtp.MtpObjectInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    WebView web;
    EditText url;
    ImageButton button;
    String previousUrl;
    String nextUrl;
    String url1 = "http://www.google.fi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = findViewById(R.id.webView);
        url = findViewById(R.id.urlInput);
        button = findViewById(R.id.button);

        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url1);
        url.setText(url1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setUrl(url.getText().toString());
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });


    }

    public void setUrl(String input){

        System.out.println("BEFORE: " + previousUrl);
        previousUrl = url1;

        System.out.println("AFTER: " + previousUrl);

        // if user inputs "http://"
        if (input.startsWith("http://")){
            url1 = input;
        } else {
            url1 = "http://"+input;
            url.setText("http://"+input);
        }
        web.loadUrl(url1);


    }

    public void refresh(View v){
        web.loadUrl(url.getText().toString());
    }

    public void loadPrevious(View v){
        nextUrl = url.getText().toString();
        web.loadUrl(previousUrl);
        url.setText(previousUrl);
        url1 = url.getText().toString();
    }

    public void loadNext(View v){
        previousUrl = url.getText().toString();
        web.loadUrl(nextUrl);
        url.setText(nextUrl);
        url1 = url.getText().toString();
    }

}