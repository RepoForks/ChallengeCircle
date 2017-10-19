package com.jemshit.challenge.presentation.ui.profile_list;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jemshit.challenge.domain.model.UserModel;
import com.jemshit.challenge.presentation.R;
import com.jemshit.challenge.presentation.ui.profile_detail.ProfileDetailActivity;
import com.jemshit.challenge.presentation.ui.profile_list.di.ProfileListActivityComponent;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListContract;
import com.trevjonez.inject.activity.ActivityComponentBuilderHost;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileListActivity extends AppCompatActivity implements ProfileListContract.View, ItemClickListener {

    //region Fields
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.text_error) TextView textError;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.viewFlipper) ViewFlipper viewFlipper;

    @Inject ProfileListContract.Presenter presenter;
    private ProfileListAdapter adapter;
    //endregion

    private void setupDaggerComponent(ActivityComponentBuilderHost activityComponentBuilderHost) {
        activityComponentBuilderHost
                .getActivityComponentBuilder(ProfileListActivity.class, ProfileListActivityComponent.Builder.class)
                .build().inject(this);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDaggerComponent((ActivityComponentBuilderHost) getApplication());
        setContentView(R.layout.activity_profile_list);
        ButterKnife.bind(this);
        presenter.attachView(this);

        // Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // RecyclerView
        adapter = new ProfileListAdapter(this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent));
        swipeRefresh.setOnRefreshListener(() -> presenter.getProfileList());

        // Get Profile List
        presenter.getProfileList();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onProfileClicked(ImageView imageView, String profileId, String profileImageUrl) {
        Intent intent = new Intent(this, ProfileDetailActivity.class);
        intent.putExtra("profileId", profileId);
        intent.putExtra("profileImageUrl", profileImageUrl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String> se = Pair.create(imageView, "imageProfile");
            Pair<View, String> set = Pair.create(toolbar, "toolbar");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, se, set);
            startActivity(intent, options.toBundle());
        } else
            startActivity(intent);

    }

    //region Loading, Content, Error
    @Override public void showLoading() {
        viewFlipper.setDisplayedChild(0);
    }

    @Override public void showError(String message) {
        textError.setText(TextUtils.isEmpty(message) ? getString(R.string.error_global) : message);
        viewFlipper.setDisplayedChild(1);
        swipeRefresh.setRefreshing(false);
    }

    @Override public void showProfileList(List<UserModel> userModelList) {
        adapter.setUserModelList(userModelList);
        viewFlipper.setDisplayedChild(2);
        swipeRefresh.setRefreshing(false);
    }
    //endregion
}
