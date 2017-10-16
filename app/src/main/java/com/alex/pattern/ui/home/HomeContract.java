package com.alex.pattern.ui.home;

import android.widget.EditText;

import com.alex.pattern.data.models.UserModel;
import com.alex.pattern.base.BaseMvpPresenter;
import com.alex.pattern.base.BaseMvpView;

import java.util.List;

/**
 * Created by Alex
 */

public interface HomeContract {

    interface Presenter extends BaseMvpPresenter<View> {

        void getUsers(boolean hasInternet, boolean fromStart);

        void initLoginObservable(EditText login);

        void openUserInfo(String login);

        void disposeAll();
    }

    interface View extends BaseMvpView {

        void showProgress();

        void hideProgress();

        void addUsers(List<UserModel> items);

        void replaceUsers(List<UserModel> items);

        void showNoInternet();
    }
}
