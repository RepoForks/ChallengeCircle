package com.jemshit.challenge.presentation.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jemshit.challenge.presentation.R;
import com.jemshit.challenge.presentation.ui.login.di.LoginActivityComponent;
import com.jemshit.challenge.presentation.ui.login.mvp.LoginContract;
import com.jemshit.challenge.presentation.ui.profile_list.ProfileListActivity;
import com.jemshit.challenge.presentation.validator.ViewValidator;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.trevjonez.inject.activity.ActivityComponentBuilderHost;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    //region Fields
    @BindView(R.id.input_username) EditText inputUsername;
    @BindView(R.id.input_password) EditText inputPassword;
    @BindView(R.id.button_login) AppCompatButton buttonLogin;
    @BindView(R.id.progressWheel) ProgressWheel progressWheel;

    @Inject LoginContract.Presenter presenter;
    @Inject ViewValidator viewValidator;
    //endregion

    // Dagger son surumunde buna gerek kalmiyormus, daha denemedim hic
    private void setupDaggerComponent(ActivityComponentBuilderHost activityComponentBuilderHost) {
        activityComponentBuilderHost
                .getActivityComponentBuilder(LoginActivity.class, LoginActivityComponent.Builder.class)
                .build().inject(this);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDaggerComponent((ActivityComponentBuilderHost) getApplication());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.attachView(this);

        buttonLogin.setOnClickListener(v -> login());
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) // Not Orientation Change
            presenter.destroy();
        else
            presenter.detachView();
    }

    private void login() {
        if (viewValidator.isNonEmpty(inputUsername, getString(R.string.error_input_empty))
                && viewValidator.isNonEmpty(inputPassword, getString(R.string.error_input_empty))) {

            presenter.login(inputUsername.getText().toString().trim(), inputPassword.getText().toString().trim());
        }
    }

    //region Loading, Content, Error
    @Override public void showLoading() {
        buttonLogin.setEnabled(false);
        progressWheel.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        buttonLogin.setEnabled(true);
        progressWheel.setVisibility(View.GONE);
    }

    @Override public void onLoginSuccess() {
        hideLoading();
        startActivity(new Intent(this, ProfileListActivity.class));
        finish();
    }

    @Override public void onLoginError(String message) {
        hideLoading();
        Toast.makeText(this, getString(R.string.error_global), Toast.LENGTH_SHORT).show();
    }
    //endregion
}
