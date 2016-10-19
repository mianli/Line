package com.study.line.activity;

import com.study.line.list.actionbar.ActionBarActivity;
import com.study.line.list.fragment.LFragmentActivity;
import com.study.line.list.intent.IntentActivity;
import com.study.line.list.storage.StorageActivity;

public class MainActivity extends ListTitleActivity {

    public void initList() {
        addSimpleItem("ActionBar相关", ActionBarActivity.class);
        addSimpleItem("Fragment相关", LFragmentActivity.class);
        addSimpleItem("Storage相关" ,StorageActivity.class);
        addSimpleItem("Intent相关", IntentActivity.class);
    }

    @Override
    public void initMenu() {

    }
}
