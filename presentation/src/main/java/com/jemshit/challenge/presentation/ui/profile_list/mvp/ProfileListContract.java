package com.jemshit.challenge.presentation.ui.profile_list.mvp;

import com.jemshit.challenge.domain.model.UserModel;

import java.util.List;

public interface ProfileListContract {

    interface View {
        public void showLoading();

        public void showProfileList(List<UserModel> userModelList);

        public void showError(String message);
    }

    interface Presenter {
        public void getProfileList();

        public void attachView(View view);

        public void detachView();

        public void destroy();
    }
}
