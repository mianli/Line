package com.study.line.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.study.line.cls.ListTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crown on 2016/8/23.
 */
public class MainListBaseAdapter extends BaseListAdapter {

	private Context mContext;
	private iGetViewListener mGetListener;
	private List<ListTitle> mList = new ArrayList<>();

	public MainListBaseAdapter(Context context, iGetViewListener getListener, List<ListTitle> list) {
		this.mContext = context;
		this.mGetListener = getListener;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ListTitle item = mList.get(position);
		if(mGetListener != null) {
			return mGetListener.addListListener(mContext, convertView, item);
		}
		return null;
	}

}
