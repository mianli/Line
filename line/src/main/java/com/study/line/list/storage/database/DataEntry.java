package com.study.line.list.storage.database;

import android.provider.BaseColumns;

/**
 * Created by limian on 2016/8/31.
 */
public class DataEntry implements BaseColumns {
    public static final String TABLE_NAME = "entry";
    public static final String DATA_ID = "dataId";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    private String dataId;
    private String title;
    private String content;

    public DataEntry(String dataId, String title, String content) {
        this.dataId = dataId;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "id : " + dataId + "\ttitle : " + title + "\tcontent : " + content;
    }
}
