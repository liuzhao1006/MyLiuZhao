package com.sx.trans.supervision.activity.User;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sx.trans.supervision.Presenter.UpadatePwdPresenter;
import com.sx.trans.R;

import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.app.LzApp;
import com.sx.trans.supervision.app.IConstants;
import com.sx.trans.supervision.constants.Result;
import com.sx.trans.supervision.utils.AlertDialogUtils;
import com.sx.trans.supervision.views.PublicView;
import com.sx.trans.supervision.widget.alertdialog.Effectstype;
import com.sx.trans.supervision.widget.alertdialog.NiftyDialogBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 修改密码
 */

public class UpdatePwdActivity extends BaseActivity implements PublicView {

  private EditText etOldpwd;
  private EditText etNewPwd;
  private EditText etNewPwdag;
  private Button btUpdate;
    private void initControls(){
        etOldpwd = findViewById(R.id.et_oldpwd);
        etNewPwd = findViewById(R.id.et_new_pwd);
        etNewPwdag = findViewById(R.id.et_new_pwdag);
        btUpdate = findViewById(R.id.bt_update);
    }

    private Context mContext;

    private NiftyDialogBuilder niftyDialogBuilderShort;
    private UpadatePwdPresenter upadatePwdPresenter;
    private boolean ifSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd);
        initControls();
        mContext = this;
        setView();
        upadatePwdPresenter = new UpadatePwdPresenter(this, this);
    }

    private void setView() {
        setTitle(true, mContext.getString(R.string.updatepwd_title), false, null);


    }

    @OnClick(R.id.bt_update)
    void updateUserPwd() {//确认修改
        final String oldPwd = etOldpwd.getText().toString().trim();
        final String newPwd = etNewPwd.getText().toString().trim();
        final String newPwdag = etNewPwdag.getText().toString().trim();

        if (oldPwd == null || etOldpwd.length() == 0) {
            Toast.makeText(mContext, getString(R.string.et_old_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPwd == null || newPwd.length() == 0) {
            Toast.makeText(mContext, getString(R.string.et_new_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPwdag == null || newPwdag.length() == 0) {
            Toast.makeText(mContext, getString(R.string.et_new_pwdag), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPwd.equals(newPwdag)) {
            Toast.makeText(mContext, getString(R.string.et_new_pwdag_fault), Toast.LENGTH_SHORT).show();
            return;
        }
        niftyDialogBuilderShort =
                AlertDialogUtils.showDialog(mContext, Effectstype.SlideBottom, "温馨提示", "确认要修改吗?", mContext.getString(R.string.cancel), mContext.getString(R.string.confirm),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                niftyDialogBuilderShort.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                upadatePwdPresenter.putUpdateUserPwd(
                                        LzApp.mSpfProxy.getInt(IConstants.mSpre_Constants.UID, 2),
                                        oldPwd, newPwd, newPwdag
                                );


                            }
                        });
    }

    @Override
    public void showLoading() {
        showDiaLogLoading(false);
    }

    @Override
    public void hideLoading() {
        HideDiaLogLoading();
        if (ifSuccess) {
            finish();
        }
    }

    @Override
    public void Success(Result result, int code) {
        if (code == 0) {
            ifSuccess = true;
        } else {
            ifSuccess = false;
        }
        Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
        niftyDialogBuilderShort.dismiss();
    }

    @Override
    public void showError(String error) {
        ifSuccess = false;
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}
