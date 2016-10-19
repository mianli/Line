package com.study.line.list.fragment;

import com.study.line.activity.ListTitleActivity;
import com.study.line.list.fragment.dynamic.LFragmentDynamicActivity;
import com.study.line.list.fragment.interactive.InteractiveActivity;

/**
 * Created by limian on 2016/8/24.
 */
public class LFragmentActivity extends ListTitleActivity {

    @Override
    public void initList() {
        addSimpleItem("xml添加fragment", LFragmentXMLActivity.class);
        addSimpleItem("动态管理fragment", LFragmentDynamicActivity.class);
        addSimpleItem("Fragment和Activity交互", InteractiveActivity.class);
    }

    @Override
    public void initMenu() {

    }
}
