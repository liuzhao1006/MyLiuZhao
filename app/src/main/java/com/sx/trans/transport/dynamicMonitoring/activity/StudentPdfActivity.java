package com.sx.trans.transport.dynamicMonitoring.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import com.sx.trans.R;
import com.sx.trans.app.LzApp;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.transport.dynamicMonitoring.bean.PdfBean;
import com.sx.trans.widget.font.LzTextView;
import com.sx.trans.widget.utils.FileUtil;


import java.io.File;
import java.util.List;

/**
 * pdf页面
 */

public class StudentPdfActivity extends BaseTransActivity implements BaseNetApi, OnPageChangeListener, OnLoadCompleteListener {
    private static final String TAG = StudentPdfActivity.class.getSimpleName();

    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;

    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";


    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    Uri uri;

    Integer pageNumber = 0;

    String pdfFileName;
    private PDFView pdfView;
    private PdfBean accidentBean;
    private String pdfPath;
    private Intent intent;


    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            //alert user that file manager not working
            Toast.makeText(this, "Unable to pick file. Check status of file manager.", Toast.LENGTH_SHORT).show();
        }
    }

    void afterViews() {
        pdfView.setBackgroundColor(Color.LTGRAY);
        if (uri != null) {
            displayFromUri(uri);
        } else {
            File file = new File(pdfPath);
            LogUtils.i(pdfPath);
            displayFromFile(file);
        }
        setTitle(pdfFileName);
    }

    private void displayFromFile(File file) {
        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
                .load();


    }


    private void displayFromUri(Uri uri) {
        pdfFileName = getFileName(uri);

        pdfView.fromUri(uri)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_student_pdf;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView l = findViewById(R.id.layout_top_modleinfo);
        l.setTextSize(12f);
        if (intent != null) {
            intent = null;
        }
        LzTextView pdf_time = findViewById(R.id.pdf_time);
        ImageView imageView = findViewById(R.id.layout_top_rightinfo);
        LzTextView pdf_name = findViewById(R.id.pdf_name);
        intent = getIntent();
        leftGoBack(this);
        if (intent != null) {
            if (accidentBean != null) {
                accidentBean = null;
            }
            Bundle data = intent.getBundleExtra("StudentPdfActivity");
            accidentBean = (PdfBean) data.getSerializable("pdfContent");
            if (accidentBean != null) {
                LogUtils.i(accidentBean);
//                pdfPath = FileUtil.getRootPath(LzApp.getInstance()).getAbsolutePath() + File.separator +
//                        "Liuzhao" + "/" + accidentBean.name + ".pdf";
                pdfPath =  accidentBean.path;
                l.setText(accidentBean.name);
                pdf_name.setText(accidentBean.name);
                pdf_time.setText(accidentBean.time);
                imageView.setVisibility(View.VISIBLE);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int permissionCheck = ContextCompat.checkSelfPermission(StudentPdfActivity.this,
                                READ_EXTERNAL_STORAGE);

                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(
                                    StudentPdfActivity.this,
                                    new String[]{READ_EXTERNAL_STORAGE},
                                    PERMISSION_CODE
                            );
                            return;
                        }
                        launchPicker();
                    }
                });
            } else {
                l.setText("法律法规");
            }


        }


        pdfView = findViewById(R.id.pdfView);
        pdfView.setBackgroundColor(Color.LTGRAY);
        //权限判断
        //权限判断
        if (ContextCompat.checkSelfPermission(StudentPdfActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请READ_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(StudentPdfActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            if (!TextUtils.isEmpty(pdfPath)) {
                File file = new File(pdfPath);
                LogUtils.i(pdfPath);
                displayFromFile(file);
            }

        }


        setTitle(pdfFileName);
    }

    @Override
    public void netStart() {

    }

    @Override
    public void netStop() {

    }

    @Override
    public void netSuccessed(int what, String data) {

    }

    @Override
    public void netFailed(int what, String message) {

    }

    public void onResult(int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            uri = intent.getData();
            displayFromUri(uri);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();

        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchPicker();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                File file = new File(pdfPath);
                LogUtils.i(pdfPath);
                displayFromFile(file);
            }
        }
    }
}
