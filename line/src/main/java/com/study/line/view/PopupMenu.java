package com.study.line.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.study.line.adapter.MainListBaseAdapter;
import com.study.line.adapter.iGetViewListener;
import com.study.line.line.R;
import com.study.line.adapter.ViewHolder;
import com.study.line.cls.ListTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limian on 2016/8/25.
 */
public class PopupMenu extends PopupWindow implements iGetViewListener {

    private List<ListTitle> mList = new ArrayList<>();
    private Button mCancelBtn;
    private ListView mListView;

    private Context mContext;
    private boolean mAutoDismiss;

    public PopupMenu(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popwindow_list, null);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        setBackgroundDrawable(dw);
        initView(view);
    }

    private void initView(View view) {
        mCancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        mListView = (ListView) view.findViewById(R.id.listview);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void addItem(String text, View.OnClickListener listener) {
        mList.add(new ListTitle(text, listener));
    }

    public void reload() {
        MainListBaseAdapter adapter = new MainListBaseAdapter(mContext, this, mList);
        mListView.setAdapter(adapter);
    }

    public boolean hasItem() {
        return mList.size() > 0;
    }

    @Override
    public View addListListener(Context context, View convertView, final ListTitle item) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_menu, null);
        }
        TextView textView = ViewHolder.get(convertView, R.id.textview);
        textView.setText(item.getTitle());
        if(item.getListener() != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.getListener().onClick(v);
                    if(mAutoDismiss) {
                        dismiss();
                    }
                }
            });
        }else {
            textView.setOnClickListener(null);
        }
        return convertView;
    }

    public void setAutoDismiss(boolean autoDismiss) {
        this.mAutoDismiss = autoDismiss;
    }
}
