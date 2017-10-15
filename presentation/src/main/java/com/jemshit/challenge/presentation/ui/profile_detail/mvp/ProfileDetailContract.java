package com.jemshit.challenge.presentation.ui.profile_detail.mvp;

import com.jemshit.challenge.domain.model.ProfileModel;

public interface ProfileDetailContract {

    interface View {
        public void showLoading();

        public void showProfile(ProfileModel profileModel);

        public void showError(String message);
    }

    interface Presenter {
        public void getProfileDetail(String profileId);

        public void attachView(View view);

        public void detachView();

        public void destroy();
    }
}
