package com.jemshit.challenge.presentation.ui.profile_list.mvp;

import com.jemshit.challenge.domain.use_case.GetProfileList;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class ProfileListPresenter implements ProfileListContract.Presenter {

    private ProfileListContract.View view;
    private GetProfileList getProfileList;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;
    private Disposable disposable;

    @Inject
    public ProfileListPresenter(GetProfileList getProfileList, @Named("IoWorkScheduler") Scheduler subscribeOnScheduler,
                                @Named("MainScheduler") Scheduler observeOnScheduler) {
        this.getProfileList = getProfileList;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public void getProfileList() {
        disposable = getProfileList.execute()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnSubscribe(disposable1 -> {
                    if (isViewAttached()) view.showLoading();
                })
                .subscribe(
                        userModelList -> {
                            if (isViewAttached()) view.showProfileList(userModelList);
                        },
                        throwable -> {
                            if (isViewAttached()) view.showError(throwable.getMessage());
                        }
                );
    }

    @Override
    public void attachView(ProfileListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    private boolean isViewAttached() {
        return this.view != null;
    }

    @Override
    public void destroy() {
        detachView();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
