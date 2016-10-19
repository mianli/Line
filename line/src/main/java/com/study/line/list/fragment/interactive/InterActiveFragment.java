package com.study.line.list.fragment.interactive;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.study.line.line.R;

/**
 * Created by limian on 2016/8/26.
 */
public class InterActiveFragment extends Fragment {

    private iPlusCountListener mListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (iPlusCountListener) activity;
        }catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interactive, container, false);
        final TextView tv = (TextView) view.findViewById(R.id.textview);
        Button button = (Button) view.findViewById(R.id.btn);
        tv.setText(mListener.getData());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.count();
                tv.setText(mListener.getData());
            }
        });
        return view;
    }


}
