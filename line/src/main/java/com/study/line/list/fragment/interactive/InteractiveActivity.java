package com.study.line.list.fragment.interactive;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import com.study.line.line.R;

/**
 * Created by limian on 2016/8/26.
 */
public class InteractiveActivity extends Activity implements iPlusCountListener{

    private int num = 15;
    private TextView mContentTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive);
        addFragment();
        mContentTv = (TextView) findViewById(R.id.content_tv);
    }

    private void addFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, new InterActiveFragment()).commit();
    }

    @Override
    public void count() {
        num++;
    }

    @Override
    public String getData() {
        String text = "属性在"+getClass().getSimpleName()+"中已改变为："+num;
        mContentTv.setText(text);
        return text;
    }
}
