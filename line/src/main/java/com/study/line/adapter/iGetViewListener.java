package com.study.line.adapter;

import android.content.Context;
import android.view.View;

import com.study.line.cls.ListTitle;

/**
 * Created by crown on 2016/8/25.
 */
public interface iGetViewListener {
	View addListListener(Context context, View convertView, final ListTitle item);
}
