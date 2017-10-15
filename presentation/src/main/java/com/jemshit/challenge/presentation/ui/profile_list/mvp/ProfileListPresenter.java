package com.jemshit.challenge.presentation.ui.profile_list.mvp;

import com.jemshit.challenge.domain.interactor.GetProfileList;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.Subscription;

public class ProfileListPresenter implements ProfileListContract.Presenter {

    private ProfileListContract.View view;
    private GetProfileList getProfileList;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;
    private Subscription subscription;

    @Inject
    public ProfileListPresenter(GetProfileList getProfileList, @Named("IoWorkScheduler") Scheduler subscribeOnScheduler,
                                @Named("MainScheduler") Scheduler observeOnScheduler) {
        this.getProfileList = getProfileList;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public void getProfileList() {
        subscription = getProfileList.execute()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnSubscribe(() -> {
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
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
