package com.jemshit.challenge.presentation.ui.profile_detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jemshit.challenge.domain.model.ProfileModel;
import com.jemshit.challenge.presentation.R;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class ProfileDetailActivity extends AppCompatActivity implements ProfileDetailContract.View {

    //region Fields
    @BindView(R.id.image_toolbarBack) ImageView imageToolbarBack;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.text_error) TextView textError;
    @BindView(R.id.input_address) EditText inputAddress;
    @BindView(R.id.input_phone) EditText inputPhone;
    @BindView(R.id.button_update) AppCompatButton buttonUpdate;
    @BindView(R.id.viewFlipper) ViewFlipper viewFlipper;
    @BindView(R.id.input_name) EditText inputName;
    @BindView(R.id.input_surname) EditText inputSurname;
    @BindView(R.id.input_age) EditText inputAge;
    @BindView(R.id.image_profile) ImageView imageProfile;

    @Inject ProfileDetailContract.Presenter presenter;
    //endregion

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        ButterKnife.bind(this);
        presenter.attachView(this);
        String profileId = getIntent().getStringExtra("profileId");
        String profileImageUrl = getIntent().getStringExtra("profileImageUrl");
        Glide.with(this)
                .load(profileImageUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(imageProfile);

        // Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        imageToolbarBack.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                finishAfterTransition();
            else
                finish();
        });

        // Get Profile
        presenter.getProfileDetail(profileId);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    //region Loading, Content, Error
    @Override public void showLoading() {
        viewFlipper.setDisplayedChild(0);
    }

    @Override public void showError(String message) {
        textError.setText(TextUtils.isEmpty(message) ? getString(R.string.error_global) : message);
        viewFlipper.setDisplayedChild(1);
    }

    @Override public void showProfile(ProfileModel profileModel) {
        inputName.setText(profileModel.getName());
        inputSurname.setText(profileModel.getSurname());
        inputAge.setText(String.valueOf(profileModel.getAge()));
        inputPhone.setText(profileModel.getCellphoneNumber());
        inputAddress.setText(profileModel.getAddress());

        buttonUpdate.setOnClickListener(v -> Toast.makeText(this, "Imaginary Update :)", Toast.LENGTH_SHORT).show());

        viewFlipper.setDisplayedChild(2);
    }
    //endregion
}
