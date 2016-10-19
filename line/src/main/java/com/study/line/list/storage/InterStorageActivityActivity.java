package com.study.line.list.storage;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.study.line.tools.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by limian on 2016/8/29.
 */
public class InterStorageActivityActivity extends StorageViewActivity {

    private static final String FILE_NAME = "line.txt";

    @Override
    public void initMenu() {
        addMenuItem("内部存储写 使用File", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataEt.getText() != null) {
                    write2FileMethod2(getApplicationContext().getFilesDir().getAbsolutePath() + " /" + FILE_NAME + ":" + mDataEt.getText().toString());
                }
            }
        });

        addMenuItem("内部存储写 MODE_PRIVATE", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataEt.getText() != null) {
                    //当前程序路径+内容
                    write2FileMethod1(getApplicationContext().getFilesDir().getAbsolutePath() + " /" + FILE_NAME + ":" + mDataEt.getText().toString(),
                        Context.MODE_PRIVATE);
                }
            }
        });

        addMenuItem("内部存储写 MODE_APPEND", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataEt.getText() != null) {
                    //当前程序路径+内容
                    write2FileMethod1( getApplicationContext().getFilesDir().getAbsolutePath() + " /"+ FILE_NAME +":" + mDataEt.getText().toString(),
                            Context.MODE_APPEND);
                }
            }
        });

        addMenuItem("内部存储读", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile();
            }
        });

        addMenuItem("缓存写", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataEt.getText() != null) {
                    write2FileByFileFun(getCacheDir() + " /" + FILE_NAME + ":" + mDataEt.getText().toString());
                }
            }
        });

        addMenuItem("缓存读", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromCache();
            }
        });

        addMenuItem("删除缓存文件", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCacheFile();
            }
        });
    }

    //文件默认会存储到/data/data/package/files中；
    private void write2FileMethod1(String data, int mode) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(FILE_NAME, mode);
            outputStream.write(data.getBytes("UTF-8"));
            ToastUtils.show(InterStorageActivityActivity.this, "存储成功");
            outputStream.close();
        } catch (Exception e) {
            ToastUtils.show(InterStorageActivityActivity.this, "存储失败");
            e.printStackTrace();
        }
    }

    private void write2FileMethod2(String data) {
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(getFile());
            outputStream.write(data.getBytes());
            ToastUtils.show(InterStorageActivityActivity.this, "存储成功");
            outputStream.close();
        } catch (FileNotFoundException e) {
            ToastUtils.show(InterStorageActivityActivity.this, "存储失败");
            e.printStackTrace();
        } catch (IOException e) {
            ToastUtils.show(InterStorageActivityActivity.this, "存储失败");
            e.printStackTrace();
        }
    }

    //放在缓存文件中
    private void write2FileByFileFun(String data) {

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(getCacheFile());
            outputStream.write(data.getBytes("UTF-8"));
            ToastUtils.show(InterStorageActivityActivity.this, "存储成功");
            outputStream.close();
        } catch (Exception e) {
            ToastUtils.show(InterStorageActivityActivity.this, "存储失败");
            e.printStackTrace();
        }
    }

    private void readFromCache() {
        FileInputStream inputStream;
        ByteArrayOutputStream bout;
        byte[]buf = new byte[1024];
        bout = new ByteArrayOutputStream();
        int length;
        try {
            inputStream = new FileInputStream(getCacheFile());
            while((length = inputStream.read(buf))!=-1){
                bout.write(buf,0,length);
            }
            byte[] content = bout.toByteArray();
            mDataTv.setText(new String(content, "UTF-8"));
            inputStream.close();
            bout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        FileInputStream in;
        ByteArrayOutputStream bout;
        byte[]buf = new byte[1024];
        bout = new ByteArrayOutputStream();
        int length;
        try {
            in = this.openFileInput(FILE_NAME);
            while((length = in.read(buf))!=-1){
                bout.write(buf,0,length);
            }
            byte[] content = bout.toByteArray();
            mDataTv.setText(new String(content,"UTF-8"));
            in.close();
            bout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile() {
//        getFile().delete();
        deleteFile(FILE_NAME);
    }

    private void deleteCacheFile() {
        getCacheFile().delete();
    }

    private boolean getInternalFreeSpace(File file) {
        return file.getTotalSpace() - file.getFreeSpace() > 0;
    }

    //是否适合去写
    private boolean isMatchSave(File file) {
        return file.getFreeSpace() / file.getTotalSpace() > 0.1;
    }

    //缓存文件
    private File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private File getFile() {
        return new File(this.getFilesDir(), FILE_NAME);
    }

    private File getCacheFile() {
        return new File(this.getCacheDir(), FILE_NAME);
    }
}
