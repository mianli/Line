package com.study.line.list.intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;

import com.study.line.activity.BaseActivity;
import com.study.line.activity.ListTitleActivity;
import com.study.line.cls.ListTitle;
import com.study.line.tools.ToastUtils;

import java.util.List;

/**
 * Created by limian on 2016/10/10.
 */
public class SendIntentActivity extends ListTitleActivity {

    @Override
    public void initList() {
        setMenuAutoDismiss(true);
        addItem("打开网页", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeb();
            }
        });
        addItem("通话", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUp();
            }
        });
        addItem("位置", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitLocation();
            }
        });
    }

    @Override
    public void initMenu() {
        addRelativeUrl("http://hukai.me/android-training-course-in-chinese/basics/intents/sending.html");
    }

    private void openWeb() {
        String title = "打开网页, 每次都会让用户重新选择";
        Uri webPage = Uri.parse("https://www.baidu.com/");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
        Intent chooser = Intent.createChooser(webIntent, title);
        if(canHandle(webIntent)) {
            startActivity(chooser);
        }
    }

    private void callUp() {
        String nummber = "13262662119";
        Uri number = Uri.parse("tel:" + nummber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        if(canHandle(callIntent)) {
            startActivity(callIntent);
        }
    }

    private void visitLocation() {
        // Map point based on address
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        // Or map point based on latitude/longitude
        // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        if(canHandle(mapIntent)) {
            startActivity(mapIntent);
        }
    }

    private boolean canHandle(Intent intent) {
        PackageManager manager = getPackageManager();
        List<ResolveInfo> activties = manager.queryIntentActivities(intent, 0);
        if(activties.size() > 0) {
            return true;
        }else {
            ToastUtils.show(this, "没有对应的App能够打开界面");
            return false;
        }
    }

}
