package com.study.line.list.intent;

import com.study.line.activity.ListTitleActivity;

/**
 * Created by crown on 2016/8/31.
 */
public class IntentActivity extends ListTitleActivity{


	@Override
	public void initList() {
		addSimpleItem("Intent发送", SendIntentActivity.class);
	}

	@Override
	public void initMenu() {

	}
}
