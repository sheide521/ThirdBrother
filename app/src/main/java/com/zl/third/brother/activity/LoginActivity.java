package com.zl.third.brother.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.third.brother.R;
import com.zl.third.brother.widghts.CustomVideoView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaolong on
 * 2017/12/4
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.ct_view)
    CustomVideoView ctView;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.tl_account)
    TextInputLayout tlAccount;
    @Bind(R.id.tl_pwd)
    TextInputLayout tlPwd;
    @Bind(R.id.edt_account)
    EditText edtAccount;
    @Bind(R.id.edt_password)
    EditText edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //加载数据
        initView();
    }

    private void initView() {
        //设置播放加载路径
        ctView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //播放
        ctView.start();

    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        ctView.stopPlayback();
        super.onStop();
    }

    @OnClick({R.id.ct_view, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ct_view:
                //循环播放
                ctView.start();
                break;
            case R.id.tv_login:
//                String account = tlAccount.getEditText().getText().toString();
//                String pwd = tlPwd.getEditText().getText().toString();
//                if (TextUtils.isEmpty(account)) {
//                    tlAccount.setError("账户不对");
//                    return;
//                }
//                tlAccount.setErrorEnabled(false);
//
//                if (TextUtils.isEmpty(pwd)) {
//                    tlPwd.setError("密码不对");
//                    return;
//                }
//                tlPwd.setErrorEnabled(false);

                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
