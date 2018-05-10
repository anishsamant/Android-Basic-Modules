package com.webviewdemo;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;
    EditText mEditText;
    Button mClick;

    public void showResult()
    {
        InputMethodManager mgr=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mWebView.getWindowToken(),0);

        mWebView.setVisibility(View.VISIBLE);
        mEditText.setVisibility(View.INVISIBLE);
        mClick.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.newSite:
                if(Build.VERSION.SDK_INT<18)
                    mWebView.clearView();
                else
                    mWebView.loadUrl("about:blank");
                mWebView.setVisibility(View.INVISIBLE);
                mEditText.setVisibility(View.VISIBLE);
                mClick.setVisibility(View.VISIBLE);
                return true;
            case R.id.htmlContent:
                showResult();
                mWebView.loadData("<html><body><h1>This is WebView Demo App</h1></body></html>","text/html","UTF-8");
                mEditText.setText("");
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClick=(Button)findViewById(R.id.button);
        mWebView=(WebView)findViewById(R.id.webview);
        mEditText=(EditText)findViewById(R.id.editText);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String site=mEditText.getText().toString();

                if(TextUtils.isEmpty(site))
                {
                    mEditText.setError("This field cannot be empty");
                }
                else
                {
                    showResult();

                    mWebView.loadUrl("http://"+site);

                    mEditText.setText("");
                }

            }
        });
    }

}

