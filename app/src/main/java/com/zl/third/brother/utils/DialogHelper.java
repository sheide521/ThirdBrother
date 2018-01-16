package com.zl.third.brother.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import com.zl.third.brother.utils.Permisson.PermissionUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/01/10
 *     desc  : 对话框工具类
 * </pre>
 */
public class DialogHelper {

    public static void showRationaleDialog(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle("注意")
                .setMessage("您已经拒绝我们申请授权，请同意授权，否则功能将无法正常使用！")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(true);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(false);
                    }
                })
                .setCancelable(false)
                .create()
                .show();

    }

    public static void showOpenAppSettingDialog() {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle("注意")
                .setMessage("我们需要一些你拒绝的权限或者系统申请失败，请手动设置页面授权，否则功能不能正常使用！")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.openAppSettings();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

//    public static void showKeyboardDialog() {
//        Activity topActivity = ActivityUtils.getTopActivity();
//        if (topActivity == null) return;
//        final View dialogView = LayoutInflater.from(topActivity).inflate(R.layout.dialog_keyboard, null);
//        final EditText etInput = dialogView.findViewById(R.id.et_input);
//        final AlertDialog dialog = new AlertDialog.Builder(topActivity).setView(dialogView).create();
//        dialog.setCanceledOnTouchOutside(false);
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.btn_hide_soft_input:
//                        KeyboardUtils.hideSoftInput(etInput);
//                        break;
//                    case R.id.btn_show_soft_input:
//                        KeyboardUtils.showSoftInput(etInput);
//                        break;
//                    case R.id.btn_toggle_soft_input:
//                        KeyboardUtils.toggleSoftInput();
//                        break;
//                    case R.id.btn_close_dialog:
//                        KeyboardUtils.hideSoftInput(etInput);
//                        dialog.dismiss();
//                        break;
//                }
//            }
//        };
//        dialogView.findViewById(R.id.btn_hide_soft_input).setOnClickListener(listener);
//        dialogView.findViewById(R.id.btn_show_soft_input).setOnClickListener(listener);
//        dialogView.findViewById(R.id.btn_toggle_soft_input).setOnClickListener(listener);
//        dialogView.findViewById(R.id.btn_close_dialog).setOnClickListener(listener);
//        dialog.show();
//    }
}
