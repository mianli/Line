package com.study.line.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.study.line.line.R;

/**
 * Created by crown on 2016/10/11.
 */
public class WebActivity extends Activity {

	private static final String URL = "webUrl";
	private String mUrl;
	private WebView mWebview;

	public static void openUrl(Context context, String url) {
		Intent intent = new Intent(context, WebActivity.class);
		intent.putExtra(URL, url);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		initBtns();
		mWebview = (WebView) findViewById(R.id.webview);
		mUrl = getIntent().getStringExtra(URL);
		loadUrl();
	}

	private void loadUrl() {
		if(mUrl == null) {
			return;
		}
		mWebview.loadUrl(mUrl);
		WebSettings settings = mWebview.getSettings();
		settings.setLoadWithOverviewMode(true);
//		settings.setUseWideViewPort(true);
		mWebview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	private void initBtns() {
		Button backBtn = (Button) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backAction();
			}
		});

		Button forwardBtn = (Button) findViewById(R.id.forward_btn);
		forwardBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				forwardAction();
			}
		});
		Button refreshBtn = (Button) findViewById(R.id.refresh_btn);
		refreshBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshAction();
			}
		});
	}

	private void backAction() {
		mWebview.goBack();
	}

	private void forwardAction() {

		mWebview.goForward();
	}

	private void refreshAction() {
		mWebview.reload();
	}

}
