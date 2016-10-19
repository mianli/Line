package com.study.line.list.storage;

import android.os.Environment;
import android.view.View;

import com.study.line.tools.ToastUtils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * Created by limian on 2016/8/30.
 */
public class ExternalStorageActivityActivity extends StorageViewActivity {

    private static final String FILE_NAME = "storage.txt";
    private static final String CHILD_FILE = "file";
    @Override
    public void initMenu() {
        addMenuItem("外部存储写", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write2File(null, getInputData());
            }
        });
        addMenuItem("外部存储写 追加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write2File(null, getInputData(), true);
            }
        });
        addMenuItem("外部存储读", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile(null);
            }
        });

        addMenuItem("外部存储写入子文件", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write2File(CHILD_FILE, getInputData());
            }
        });

        addMenuItem("外部存储读子文件" + CHILD_FILE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile(CHILD_FILE);
            }
        });

        addMenuItem("删除存储文件", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(getFile(null));
            }
        });

        addMenuItem("删除存储（子）文件", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(getFile(CHILD_FILE));
            }
        });
    }

    private boolean isExternalStorageWriteable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageReadAble() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private String getInputData() {
        if(mDataEt.getText() == null || mDataEt.getText().toString() == null) {
            return "";
        }else {
            return mDataEt.getText().toString();
        }
    }

    private void write2File(String fileName, String data) {
        if(!isExternalStorageWriteable()) {
            showToast("没有外置存储或外置存储未成功挂载");
            return;
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(getFile(fileName));
            outputStream.write(data.getBytes());
            ToastUtils.show(ExternalStorageActivityActivity.this, "存储成功");
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ToastUtils.show(ExternalStorageActivityActivity.this, "存储失败");
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.show(ExternalStorageActivityActivity.this, "存储失败");
        }
    }

    private void write2File(String fileName, String data, boolean isAppend) {
        try {
            FileOutputStream outputStream = new FileOutputStream(getFile(fileName), isAppend);
            outputStream.write(data.getBytes());
            outputStream.close();
            showToast("存储成功");
        } catch (FileNotFoundException e) {
            showToast("存储失败");
            e.printStackTrace();
        } catch (IOException e) {
            showToast("存储失败");
            e.printStackTrace();
        }
    }

    private void readFromFile(String fileName) {
        if(!isExternalStorageReadAble()) {
            showToast("外置存储不可读");
            return;
        }
        ByteArrayOutputStream outputStream;
        byte[] buf = new byte[1024];
        outputStream = new ByteArrayOutputStream();
        int length;
        try {
            FileInputStream inputStream = new FileInputStream(getFile(fileName));
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            byte[] content = outputStream.toByteArray();
            mDataTv.setText(new String(content, "utf-8"));
            outputStream.close();
            inputStream.close();
            showToast("读取成功");
        } catch (FileNotFoundException e) {
            showToast("读取失败");
            e.printStackTrace();
        } catch (IOException e) {
            showToast("读取失败");
            e.printStackTrace();
        }
    }

    private void deleteFile(File file) {
        file.delete();
    }

    /**
     * 如果想更换存储路径请更换
     * @param childFile
     * @return
     */
    private File getFile(String childFile) {
        return new File(getExternalFilesDir(childFile), FILE_NAME);
    }

/*************************************************扩展/*************************************************
    /**
     * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
     */
    public static void method1(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 追加文件：使用FileWriter
     *
     * @param fileName
     * @param content
     */
    public static void method2(String fileName, String content) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加文件：使用RandomAccessFile
     *
     * @param fileName
     *            文件名
     * @param content
     *            追加的内容
     */
    public static void method3(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
