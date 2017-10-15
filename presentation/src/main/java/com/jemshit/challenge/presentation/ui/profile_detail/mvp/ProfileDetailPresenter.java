package com.jemshit.challenge.presentation.ui.profile_detail.mvp;

import com.jemshit.challenge.domain.interactor.GetProfileById;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.Subscription;

public class ProfileDetailPresenter implements ProfileDetailContract.Presenter {

    private ProfileDetailContract.View view;
    private GetProfileById getProfileById;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;
    private Subscription subscription;

    @Inject
    public ProfileDetailPresenter(GetProfileById getProfileById, @Named("IoWorkScheduler") Scheduler subscribeOnScheduler,
                                  @Named("MainScheduler") Scheduler observeOnScheduler) {
        this.getProfileById = getProfileById;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public void getProfileDetail(String profileId) {
        subscription = getProfileById.execute(profileId)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnSubscribe(() -> {
                    if (isViewAttached()) view.showLoading();
                })
                .subscribe(
                        profileModel -> {
                            if (isViewAttached()) view.showProfile(profileModel);
                        },
                        throwable -> {
                            if (isViewAttached()) view.showError(throwable.getMessage());
                        }
                );
    }

    @Override
    public void attachView(ProfileDetailContract.View view) {
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
