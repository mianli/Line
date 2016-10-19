package com.study.line.list.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.line.line.R;

/**
 * Created by limian on 2016/8/24.
 */
public class Fragment2 extends Fragment {

    private TextView mTv;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_text, container, false);
        mView.setBackgroundColor(Color.RED);
        setText("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    android:orientation=\"horizontal\"\n" +
            "    android:layout_width=\"fill_parent\"\n" +
            "    android:layout_height=\"fill_parent\">\n" +
            "\n" +
            "    <fragment android:name=\"com.example.android.fragments.HeadlinesFragment\"\n" +
            "              android:id=\"@+id/headlines_fragment\"\n" +
            "              android:layout_weight=\"1\"\n" +
            "              android:layout_width=\"0dp\"\n" +
            "              android:layout_height=\"match_parent\" />\n" +
            "\n" +
            "    <fragment android:name=\"com.example.android.fragments.ArticleFragment\"\n" +
            "              android:id=\"@+id/article_fragment\"\n" +
            "              android:layout_weight=\"2\"\n" +
            "              android:layout_width=\"0dp\"\n" +
            "              android:layout_height=\"match_parent\" />\n" +
            "\n" +
            "</LinearLayout>");
        return mView;
    }

    public void setText(String text) {
        if(mTv == null) {
            mTv = (TextView) mView.findViewById(R.id.textview);
        }
        mTv.setText(text);
    }

}
