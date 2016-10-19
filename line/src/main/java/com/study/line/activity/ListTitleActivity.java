package com.study.line.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.study.line.adapter.iGetViewListener;
import com.study.line.line.R;
import com.study.line.adapter.MainListBaseAdapter;
import com.study.line.adapter.ViewHolder;
import com.study.line.cls.ListTitle;

import java.util.LinkedList;

/**
 * Created by limian on 2016/8/24.
 */
public abstract class ListTitleActivity extends BaseActivity implements iGetViewListener {

    public abstract void initList();
    private ListView mListView;
    private LinkedList<ListTitle> mList = new LinkedList<>();
    private MainListBaseAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initView();
    }

    public void addItem(String text, View.OnClickListener listener) {
        add2List(text, listener);
    }

    public void addSimpleItem(String text, final Class cls) {
        addItem(text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTitleActivity.this, cls));
            }
        });
    }

    private void add2List(String text, View.OnClickListener listener) {
        mList.addFirst(new ListTitle(text, listener));
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new MainListBaseAdapter(this, this, mList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public View addListListener(Context context, View convertView, final ListTitle item) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_textview_listview, null);
        }
        TextView textView = ViewHolder.get(convertView, R.id.textview);
        textView.setText(item.getTitle());
        if(item.getListener() != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.getListener().onClick(v);
                }
            });
        }else {
            textView.setOnClickListener(null);
        }
        return convertView;
    }

}

