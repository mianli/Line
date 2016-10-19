package com.study.line.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.study.line.line.R;
import com.study.line.tools.ToastUtils;
import com.study.line.view.PopupMenu;

/**
 * Created by crown on 2016/8/23.
 */
public abstract class BaseActivity extends Activity {

	private RelativeLayout mContainer;
	private PopupMenu mPopupMenu;
	private boolean mActionEnabel = true;
	private String mUrl;
	private Button mBtn;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		mContainer = (RelativeLayout) findViewById(R.id.main_container);
		mPopupMenu = new PopupMenu(this);
		initMenu();
		initBtn();
		if(mPopupMenu.hasItem()) {
			mPopupMenu.reload();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	public abstract void initMenu();

	public void addRelativeUrl(final String url) {
		mPopupMenu.addItem("知识点", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebActivity.openUrl(BaseActivity.this, url);
			}
		});
	}

	public void addMenuItem(String title, View.OnClickListener listener) {
		mPopupMenu.addItem(title, listener);
	}

	public void setMenuAutoDismiss(boolean autoDismiss) {
		mPopupMenu.setAutoDismiss(autoDismiss);
	}

	public void setActionEnable(boolean enable) {
		this.mActionEnabel = enable;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(mActionEnabel && mPopupMenu.hasItem()) {
			getMenuInflater().inflate(R.menu.menu_action, menu);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.action && mActionEnabel) {
			mPopupMenu.showAtLocation(mContainer, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void showToast(String message) {
		ToastUtils.show(this, message);
	}

	private void initBtn() {
		mBtn = (Button) findViewById(R.id.open_btn);
		mBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebActivity.openUrl(BaseActivity.this, mUrl);
			}
		});
	}

	public void setUrl(String url) {
		mUrl = url;
		if(mUrl == null) {
			mBtn.setVisibility(View.GONE);
		}else {
			mBtn.setVisibility(View.VISIBLE);
		}
	}

}
