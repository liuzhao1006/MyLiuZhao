package com.sx.trans.supervision.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sx.trans.R;

import com.sx.trans.supervision.widget.alertdialog.Effectstype;
import com.sx.trans.supervision.widget.alertdialog.NiftyDialogBuilder;


/**
 * Created by wbh on 2017-06-27.
 */
public class AlertDialogUtils {
    public static  ProgressDialog mProgressDialog;

    public static void showDialog(Context context,String message,boolean indeteminate, boolean cancleable){
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            return;
        }
        if (TextUtils.isEmpty(message)) message = "加载中...";
        mProgressDialog = ProgressDialog.show(context,"",message,indeteminate,cancleable);
    }

    public static void dissmissDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    /**
     * 获取弹出对话框
     * @param ctx 当前Activity的上下文对象
     * @param title 标题
     * @param message 内容
     * @param effect 特效
     * @return NiftyDialogBuilder 对话框实例
     */
    public static NiftyDialogBuilder getDialog(Context ctx,String title,String message,Effectstype effect){
        NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ctx);
        dialogBuilder
                .withTitle(title)
                .withMessage(message)
                .withIcon(R.drawable.vehicle)
                .withDividerColor("#11000000")
                .isCancelableOnTouchOutside(false)
                .withDuration(500)
                .withEffect(effect) ;
        return dialogBuilder;
    }

    /**
     * 操作确认提醒对话框
     *
     * 上下文
     * @param context
     * 显示动画效果：警告用 Effectstype.Shake，普通的提示用 Effectstype.SlideBottom
     * @param effect
     * 标题
     * @param title
     * 内容
     * @param message
     * 按钮1文字
     * @param okString
     * 按钮2文字
     * @param canclestring
     * 按钮1点击事件
     * @param ensureListener
     * 按钮2点击事件
     * @param cancleListenr
     */
    public static NiftyDialogBuilder showDialog(Context context,Effectstype effect,String title,String message,String okString,String canclestring,
                                                View.OnClickListener ensureListener,View.OnClickListener cancleListenr){
        final NiftyDialogBuilder dialogBuilder = getDialog(context,
                title, message,
                effect);
        dialogBuilder
                .withButton1Text(okString);
        dialogBuilder.withButton2Text(canclestring);
        dialogBuilder.setButton1Click(ensureListener);
        dialogBuilder.setButton2Click(cancleListenr);
        dialogBuilder.show();
        return dialogBuilder;
    }



    /**
     * 带进度条的对话框
     * @param context
     * @param effect
     * @param title
     * @param message
     * @return
     */
    public static NiftyDialogBuilder showProgressDialog(Context context,Effectstype effect,String title,String message){
        final NiftyDialogBuilder dialogBuilder = getDialog(context,
                title, message,
                effect);
        dialogBuilder.withProgress();
        dialogBuilder.show();
        return dialogBuilder;
    }


    /**
     * 操作确认提醒对话框
     * <p/>
     * 上下文
     *
     * @param context        显示动画效果：警告用 Effectstype.Shake，普通的提示用 Effectstype.SlideBottom
     * @param okString       按钮1文字
     * @param canclestring   按钮2文字
     * @param ensureListener 按钮1点击事件
     * @param cancleListenr  按钮2点击事件
     * @param
     */
    public static NiftyDialogBuilder showDialogShort(Context context, Effectstype effect,String okString, String canclestring,
                                                          View.OnClickListener ensureListener, View.OnClickListener cancleListenr) {
        final NiftyDialogBuilder dialogBuilder = getDialogShort(context,
                effect);
        dialogBuilder
                .withButton1Text(okString);
        dialogBuilder.withButton2Text(canclestring);
        dialogBuilder.setButton1Click(ensureListener);
        dialogBuilder.setButton2Click(cancleListenr);
        dialogBuilder.show();
        return dialogBuilder;
    }


    /**
     * 获取弹出对话框
     *
     * @param ctx    当前Activity的上下文对象
     * @param effect 特效
     * @return NiftyDialogBuilder 对话框实例
     */
    public static NiftyDialogBuilder getDialogShort(Context ctx, Effectstype effect) {
        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ctx);
        dialogBuilder
                .isCancelableOnTouchOutside(true)
                .withDuration(500)
                .withEffect(effect);
        return dialogBuilder;
    }

}
