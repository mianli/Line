package com.study.line.list.fragment.dynamic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.study.line.activity.BaseActivity;
import com.study.line.line.R;
import com.study.line.list.fragment.Fragment1;
import com.study.line.list.fragment.Fragment2;
import com.study.line.tools.ToastUtils;

/**
 * Created by limian on 2016/8/24.
 */
public class LFragmentDynamicActivity extends BaseActivity {

    public FragmentManager mManager;
    public FragmentTransaction mTransaction;//已经进行提交了的事务， 每次都需要重新复制
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        ToastUtils.show(this, "点击设置获取更多操作");
        setMenuAutoDismiss(false);
    }

    @Override
    public void initMenu() {
        addMenuItem("删除", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mManager = getFragmentManager();
                    mTransaction = mManager.beginTransaction();
                    mTransaction.remove(fragment2).commit();
                }catch (Exception ex) {
                    ToastUtils.show(LFragmentDynamicActivity.this, "没有添加过fragment1");
                }
            }
        });
        addMenuItem("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mManager = getFragmentManager();
                    mTransaction = mManager.beginTransaction();
                    mTransaction.add(R.id.fragment_container, fragment1).commit();
                } catch (Exception ex) {
                    ToastUtils.show(LFragmentDynamicActivity.this, "出现错误");
                }
            }
        });
        addMenuItem("替换", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mManager = getFragmentManager();
                    mTransaction = mManager.beginTransaction();
                    mTransaction.addToBackStack(null);
                    mTransaction.replace(R.id.fragment_container, fragment2).commit();
                } catch (Exception ex) {
                    ToastUtils.show(LFragmentDynamicActivity.this, "容器中没有可以替代的fragment");
                }
            }
        });
        setMenuAutoDismiss(true);
    }

}
