package com.study.line.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by crown on 2016/8/23.
 */
public class ToastUtils {

	public static void show(Context context, String message) {
		showAtTime(context, message, Toast.LENGTH_SHORT);
	}

	public static void showAtTime(Context context, String message, int time) {
		Toast.makeText(context, message, time).show();
	}

}
