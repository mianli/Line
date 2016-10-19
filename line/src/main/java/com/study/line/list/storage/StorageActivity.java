package com.study.line.list.storage;

import com.study.line.activity.ListTitleActivity;
import com.study.line.list.storage.database.DataBaseActivity;

/**
 * Created by limian on 2016/8/30.
 */
public class StorageActivity extends ListTitleActivity {

    @Override
    public void initList() {
        addSimpleItem("内部存储", InterStorageActivityActivity.class);
        addSimpleItem("外部存储", ExternalStorageActivityActivity.class);
        addSimpleItem("数据库存储", DataBaseActivity.class);
    }

    @Override
    public void initMenu() {

    }
}
