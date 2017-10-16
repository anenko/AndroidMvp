package com.alex.pattern.ui.home;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.alex.pattern.R;
import com.alex.pattern.base.BasePresenter;
import com.alex.pattern.data.repositories.UserRepository;
import com.jakewharton.rxbinding.widget.RxTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import rx.Subscription;

/**
 * Created by Alex
 */

public class HomePresenter extends BasePresenter<HomeContract.View>
        implements HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getName();

    private EventBus mBus;
    private UserRepository mUserRepository;
    private Disposable mUsersDisposable;
    private Disposable mSearchDisposable;
    private Subscription mSearchSubscription;
    private int mSince;

    @Inject
    public HomePresenter(EventBus bus, UserRepository userRepository) {
        mBus = bus;
        mUserRepository = userRepository;
    }

    @Override
    public void getUsers(boolean hasInternet, boolean fromStart) {
        if(hasInternet) {
            if(fromStart) {
                mSince = 0;
            }
            mUsersDisposable = mUserRepository.getUsers(mSince)
                    .doOnSubscribe(this::showProgress)
                    .doOnComplete(this::hideProgress)
                    .subscribe(userLists -> {
                        if (userLists != null && isViewAttach()) {
                            mSince += userLists.size();
                            getView().addUsers(userLists);
                        }
                    }, this::showError);
        } else {
            getView().showNoInternet();
        }
    }

    @Override
    public void initLoginObservable(EditText login) {
        mSearchSubscription = RxTextView.textChanges(login)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    if(!TextUtils.isEmpty(charSequence)) {
                        searchUsers(charSequence.toString());
                    }
                }, this::showError);
    }

    @Override
    public void disposeAll() {
        if(mUsersDisposable != null) {
            mUsersDisposable.dispose();
        }
        if(mSearchDisposable != null) {
            mSearchDisposable.dispose();
        }
        if(mSearchSubscription != null) {
            mSearchSubscription.unsubscribe();
        }
    }

    @Override
    public void openUserInfo(String login) {
//        mBus.post(new TransitionEvent(idFragment));
    }

    private void searchUsers(String login) {
        mSearchDisposable = mUserRepository.searchUsers(login)
                .doOnSubscribe(this::showProgress)
                .doOnComplete(this::hideProgress)
                .subscribe(response -> {
                    if (response.getUsers() != null && isViewAttach()) {
                        getView().replaceUsers(response.getUsers());
                    }
                }, this::showError);
    }

    private void showProgress(Disposable disposable) {
        if (isViewAttach()) {
            getView().showProgress();
        }
    }

    private void hideProgress() {
        if (isViewAttach()) {
            getView().hideProgress();
        }
    }

    private void showError(Throwable throwable) {
        if (isViewAttach()) {
            getView().showToast(R.string.it_is_error);
        }
        Log.e(TAG, "" + throwable);
    }
}