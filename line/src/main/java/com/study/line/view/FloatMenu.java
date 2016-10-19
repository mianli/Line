package com.study.line.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.study.line.line.R;
import com.study.line.tools.DeviceUtils;

/**
 * Created by limian on 2016/8/24.
 */
public class FloatMenu extends LinearLayout{

    private float mX;
    private float mY;
    private float mMoveX;
    private float mMoveY;

    private boolean mCanMove;

    private Context mContext;

    public FloatMenu(Context context) {
        this(context, null);
    }

    public FloatMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutParams lp = new LayoutParams(DeviceUtils.dip2px(context, 100), DeviceUtils.dip2px(context, 45));
        setLayoutParams(lp);
        setX(0);
        setY(0);
        setBackgroundColor(context.getResources().getColor(R.color.color_red));
    }

    private void getPosition() {
        int[] position = new int[2];
        getLocationOnScreen(position);
        mX = position[0];
        mY = position[1];
    }

    //应该用手势去判断
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = getX();
        float y = getY();
        Log.i("floatMenu", "x:" + x + "  y:" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if((x >= mX && x <= DeviceUtils.dip2px(mContext, 100) + mX) &&
                    (y > mY && y <= DeviceUtils.dip2px(mContext, 100) + mY)) {
                    mMoveX = x;
                    mMoveY = y;
                    mCanMove  = true;
                    Log.i("floatMenu", "actionDown, can move");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mCanMove) {
                    getPosition();
                    setX(mMoveX - (x - mX));
                    Log.i("floatMenu", "move to x:" + (mMoveX - x + mX) );
                }
                break;
            case MotionEvent.ACTION_UP:
                mCanMove = false;
                Log.i("floatMenu", "onTouchEvent action up");
                break;
        }
        return super.onTouchEvent(event);
    }
}
