package com.study.line.cls;

import android.view.View;

/**
 * Created by limian on 2016/8/24.
 */
public class ListTitle {
    private String mTitle;
    private View.OnClickListener mListener;

    public ListTitle(String title, View.OnClickListener listener) {
        this.mTitle = title;
        this.mListener = listener;
    }

    public String getTitle() {
        return mTitle;
    }

    public View.OnClickListener getListener() {
        return mListener;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setListener(View.OnClickListener listener) {
        this.mListener = listener;
    }
}