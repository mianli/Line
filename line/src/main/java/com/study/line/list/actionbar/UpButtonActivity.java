package com.study.line.list.actionbar;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by crown on 2016/8/23.
 */
public class UpButtonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//API>=11可用
		this.getActionBar().setDisplayHomeAsUpEnabled(true);
		//API<11
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
