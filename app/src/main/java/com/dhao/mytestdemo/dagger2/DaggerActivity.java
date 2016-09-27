package com.dhao.mytestdemo.dagger2;

import android.widget.TextView;

import com.dhao.mytestdemo.base.BaseActivity;
import com.dhao.mytestdemo.R;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class DaggerActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;

    @Inject
    DaggerPresenter daggerPresenter;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    Retrofit retrofit;

    @Override
    protected void initViews() {
        inject();
        daggerPresenter.showUserName();
    }

    private void inject() {
        AppComponent appComponent=((MyApplication)getApplication()).getAppComponent();

        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build().inJect(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_dagger;
    }

    public void showUserName(String userName) {
          textView.setText(userName);
    }
}
