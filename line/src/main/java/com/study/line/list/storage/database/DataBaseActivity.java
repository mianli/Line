package com.study.line.list.storage.database;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.study.line.activity.BaseActivity;
import com.study.line.line.R;

import java.util.List;

/**
 * Created by limian on 2016/8/31.
 */
public class DataBaseActivity extends BaseActivity {

    private EditText mIdEt;
    private EditText mTitleEt;
    private EditText mContentEt;
    private TextView mDataShowTv;

    private EntryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        initView();
        setMenuAutoDismiss(true);
        mDbHelper = new EntryDbHelper(this);
    }

    @Override
    public void initMenu() {
        addMenuItem("插入一行数据", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long item = mDbHelper.insertEntry(getText(mIdEt), getText(mTitleEt), getText(mContentEt));
                if(item == -1) {
                    showToast("插入结束");
                }
            }
        });

        addMenuItem("根据title mli 更新数据", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbHelper.updateData("mli", getText(mTitleEt));
                showToast("更新结束");
            }
        });
        addMenuItem("删除输入title的相同数据的数据行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbHelper.deleteData(getText(mTitleEt));
                showToast("删除结束");
            }
        });

        addMenuItem("显示所有数据", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DataEntry> list = mDbHelper.readEntries();
                mDataShowTv.setText(list.toString());
            }
        });

    }

    private void initView() {
        mIdEt = (EditText) findViewById(R.id.id_et);
        mTitleEt = (EditText) findViewById(R.id.title_et);
        mContentEt = (EditText) findViewById(R.id.content_et);
        mDataShowTv = (TextView) findViewById(R.id.textview);
    }

    private String getText(EditText et) {
        if(et.getText() == null || et.getText().toString() == null) {
            return "";
        }else {
            return et.getText().toString();
        }
    }

}
