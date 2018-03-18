package com.sx.trans.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.sx.trans.R;
import com.sx.trans.app.LoginConfig;
import com.sx.trans.base.BaseNetApi;
import com.sx.trans.base.BaseTransActivity;
import com.sx.trans.company.activity.CompanyMainActivity;
import com.sx.trans.login.bean.LoginBean;
import com.sx.trans.login.manager.LoginManager;
import com.sx.trans.network.cache.PrefUtils;
import com.sx.trans.transport.ui.MainActivity;
import com.sx.trans.widget.font.LzTextView;

public class LoginActivity extends BaseTransActivity implements BaseNetApi {
    private LoginManager builder;
    private LoginBean bean;
    private TextInputEditText etUserName;
    private TextInputEditText etPassWord;
    private String userName;
    private String passWord;
    private Spinner spinner;
    private LzTextView tvLoginFind;
    private static final String[] array1 = new String[]{"从业端", "企业端"};
    private ArrayAdapter<String> adapter;
    private String userType;
    private Button btn;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        etUserName = findViewById(R.id.et_login_name);
        etPassWord = findViewById(R.id.et_login_password);
        spinner = findViewById(R.id.spinner);
        setCoordinate(spinner, array1);
        int position = Integer.parseInt(PrefUtils.getString(LoginActivity.this, LoginConfig.LOGINTYPE, "-1"));
        spinner.setSelection(position);
        btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = etUserName.getText().toString().trim();
                passWord = etPassWord.getText().toString().trim();
                setData(userName, passWord, userType);
            }
        });
        tvLoginFind = findViewById(R.id.tv_login_findlogin);
        tvLoginFind.setText("若忘记密码，请联系系统管理员找回或重置！");
        updataIcon(etUserName);
        updataIcon(etPassWord);


    }

    /**
     * 登录逻辑
     */
    private void setData(String user, String pwd, String userType) {
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
            showToast("用户名或密码不能为空......");
            return;
        }
        bean = new LoginBean(user, pwd, userType);
        builder = new LoginManager.Builder()
                .setContext(this)
                .setBaseNetApi(this)
                .setLoginBean(bean)
                .create();
        builder.lzNetLogin();
    }

    @Override
    public void netStart() {
        //启动进度条
        btn.setEnabled(false);
    }

    @Override
    public void netStop() {

        //停止进度条
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn.setEnabled(true);
            }
        }, 10000);

    }

    @Override
    public void netSuccessed(int what, String data) {
        //获取数据成功操作
//        showToast(data);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                btn.setEnabled(true);
            }
        }, 3000);
        if (userType.equals("0")) {
            startActivity(MainActivity.class);
        } else {
            startActivity(CompanyMainActivity.class);
        }

        finish();
    }

    @Override
    public void netFailed(int what, String message) {
        //获取数据失败操作
        showToast(message);
        btn.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (builder != null)
            builder.onDestory();
    }

    /**
     * 代码修改TextInputEditText展示样式
     *
     * @param edittext
     */
    public void updataIcon(TextInputEditText edittext) {
        Drawable leftDrawable = edittext.getCompoundDrawables()[0];
        if (leftDrawable != null) {
            leftDrawable.setBounds(0, 0, 70, 70);
            edittext.setCompoundDrawables(leftDrawable, edittext.getCompoundDrawables()[1], edittext.getCompoundDrawables()[2], edittext.getCompoundDrawables()[3]);
        }
    }

    // 设置坐标系Spinner
    private void setCoordinate(Spinner spinner, String[] arr) {
        // 将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr);

        // 设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(spinner,
                arr));
        // 设置默认值
        spinner.setVisibility(View.VISIBLE);
    }


    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        private Spinner spinner;
        private String[] arr;

        public SpinnerSelectedListener(Spinner spinner, String[] arr) {
            super();
            this.spinner = spinner;
            this.arr = arr;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            LogUtils.i(arr[position] + ":" + position);
            userType = position + "";
            if (position == 1) {
                //企业
                userName = PrefUtils.getString(LoginActivity.this, LoginConfig.USER_COMPANY_NAME, null);
                passWord = PrefUtils.getString(LoginActivity.this, LoginConfig.PASS_COMPANY_WORD, null);
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
                    etPassWord.setText(passWord);
                    etUserName.setText(userName);
                }
            } else if (position == 0) {
                userName = PrefUtils.getString(LoginActivity.this, LoginConfig.USER_NAME, null);
                passWord = PrefUtils.getString(LoginActivity.this, LoginConfig.PASS_WORD, null);
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
                    etPassWord.setText(passWord);
                    etUserName.setText(userName);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
