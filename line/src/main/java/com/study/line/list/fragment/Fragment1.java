package com.study.line.list.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.line.line.R;
import com.study.line.tools.ToastUtils;

/**
 * Created by limian on 2016/8/24.
 */
public class Fragment1 extends Fragment {

    private TextView mTv;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_text, container, false);
        setText("声明fragment的时候，必须指明fragment的id");
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ToastUtils.show(getActivity(), "onResume view:" + mTv);
    }

    public void setText(String text) {
        if(mTv == null) {
            mTv = (TextView) mView.findViewById(R.id.textview);
        }
        mTv.setText(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
