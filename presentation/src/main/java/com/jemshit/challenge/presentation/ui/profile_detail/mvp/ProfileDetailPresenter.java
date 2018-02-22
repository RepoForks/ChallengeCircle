package com.jemshit.challenge.presentation.ui.profile_detail.mvp;

import com.jemshit.challenge.domain.use_case.GetProfileById;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class ProfileDetailPresenter implements ProfileDetailContract.Presenter {

    private ProfileDetailContract.View view;
    private GetProfileById getProfileById;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;
    private Disposable disposable;

    @Inject
    public ProfileDetailPresenter(GetProfileById getProfileById, @Named("IoWorkScheduler") Scheduler subscribeOnScheduler,
                                  @Named("MainScheduler") Scheduler observeOnScheduler) {
        this.getProfileById = getProfileById;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public void getProfileDetail(String profileId) {
        disposable = getProfileById.execute(profileId)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnSubscribe(disposable1 -> {
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
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
