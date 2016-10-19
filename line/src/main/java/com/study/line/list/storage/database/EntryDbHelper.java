package com.study.line.list.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.study.line.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limian on 2016/8/31.
 */
public class EntryDbHelper extends SQLiteOpenHelper{

    private Context mContext;
    private static final String DATA_BASE_NAME = "entry.db";
    private static final String TEXT = " text";
    private static final String SEP = ",";
    private static final int VERSION = 1;

    private static final String SQL_DELETE_TABLE = "drop table if exist " + DataEntry.TABLE_NAME;

    public EntryDbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "create table " + DataEntry.TABLE_NAME + "(" + DataEntry.DATA_ID + TEXT + SEP
                +DataEntry.TITLE + TEXT + SEP + DataEntry.CONTENT + TEXT +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //假如需要删除所有数据
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public long insertEntry(String id, String title, String content) {
        if(TextUtils.isEmpty(id) || TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
            ToastUtils.show(mContext, "请输入数据");
            return -1;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntry.DATA_ID, id);
        values.put(DataEntry.TITLE, title);
        values.put(DataEntry.CONTENT, content);
        //第二个参数会使得系统自动对那些ContentValues 没有提供数据的列填充数据为null，如果第二个参数传递的是null，那么系统则不会对那些没有提供数据的列进行填充。
        return db.insert(DataEntry.TABLE_NAME, null, values);
    }

    public List<DataEntry> readEntries() {
        List<DataEntry> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = new String[] {
            DataEntry.DATA_ID + SEP +
            DataEntry.TITLE + SEP +
            DataEntry.CONTENT
        };
        Cursor cursor = db.query(DataEntry.TABLE_NAME, projection, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String dataId = cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.DATA_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.CONTENT));
            list.add(new DataEntry(dataId, title, content));
        }
        return list;
    }

    public void deleteData(String deleteTitle) {
        String selecton = " title = ?";//title like ?
        String[] selectionArgs = new String[] {deleteTitle};
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DataEntry.TABLE_NAME, selecton, selectionArgs);
    }

    public void updateData(String changeTitle, String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntry.TITLE, title);
        String selection = "  title = ?";
        String[] selectionArgs = new String[] {changeTitle};
        db.update(DataEntry.TABLE_NAME, values, selection, selectionArgs);
    }

}
