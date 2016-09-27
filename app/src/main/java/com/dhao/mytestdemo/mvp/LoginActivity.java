package com.dhao.mytestdemo.mvp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.dhao.mytestdemo.R;
import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.mvp.contract.LoginContract;
import com.dhao.mytestdemo.mvp.presenter.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;

    @Override
    protected void initViews() {
        presenter=new LoginPresenter(this);
    }

    @OnClick(R.id.bt_login)
    public void login(){
        String name=etUser.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        presenter.login(name,password);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this,LoadingMvpActivity.class));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
       this.presenter=presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showProgress() {
         viewHelpController.showLoading("正在加载...");
    }

    @Override
    public void hideProgress() {
        viewHelpController.restore();
    }

    @Override
    public void showTip(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(this,R.id.activity_login);
    }
}
