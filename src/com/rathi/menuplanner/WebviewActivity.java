package com.rathi.menuplanner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewActivity extends Activity {
	WebView webView;
	String passedUrl=null;
	ProgressDialog mProgress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		//Action bar
		ActionBar actionBar = getActionBar();

		// Enabling Up / Back navigation
		actionBar.setDisplayHomeAsUpEnabled(true);

		webView = (WebView) findViewById(R.id.chefwebView);
		webView.getSettings().setJavaScriptEnabled(true);

		// get intent data
		Intent i = getIntent();
		Toast.makeText(getApplicationContext(), 
                "Please wait the page is loading....", Toast.LENGTH_LONG).show();
		// Selected image id
		int position = i.getExtras().getInt("id");
		passedUrl=getIntent().getStringExtra("web");
        webView.loadUrl(passedUrl);

	}
}



