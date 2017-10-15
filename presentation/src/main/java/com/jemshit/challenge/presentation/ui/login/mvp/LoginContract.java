package com.jemshit.challenge.presentation.ui.login.mvp;

public interface LoginContract {

    interface View {
        public void showLoading();

        public void onLoginSuccess();

        public void onLoginError(String message);
    }

    interface Presenter {
        public void login(String username, String password);

        public void attachView(View view);

        public void detachView();

        public void destroy();
    }
}
