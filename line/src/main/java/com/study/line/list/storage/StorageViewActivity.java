package com.study.line.list.storage;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.study.line.line.R;
import com.study.line.activity.BaseActivity;

/**
 * Created by limian on 2016/8/30.
 */
public class StorageViewActivity extends BaseActivity{

    public EditText mDataEt;
    public TextView mDataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        initView();
    }

    @Override
    public void initMenu() {

    }

    private void initView() {
        mDataEt = (EditText) findViewById(R.id.data_et);
        mDataTv = (TextView) findViewById(R.id.data_tv);
    }

}
