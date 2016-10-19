package com.study.line.list.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.study.line.line.R;
import com.study.line.tools.ToastUtils;

/**
 * Created by crown on 2016/8/23.
 */
public class ActionBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//解析menu布局
		getMenuInflater().inflate(R.menu.menu_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//给布局添加点击事件
		int id = item.getItemId();
		switch (id)  {
			case R.id.search_item:
				ToastUtils.show(ActionBarActivity.this, "click search");
				break;
			case R.id.start_next_activity:
				startActivity(new Intent(ActionBarActivity.this, UpButtonActivity.class));
				break;
			case R.id.hide_actionbar:
				// hide actionbar
				getActionBar().hide();
				break;
			default:
				throw new IllegalStateException("没有该id");
		}
		return super.onOptionsItemSelected(item);
	}

}
