package com.alex.pattern.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alex.pattern.R;
import com.alex.pattern.data.models.UserModel;
import com.alex.pattern.databinding.FragmentHomeBinding;
import com.alex.pattern.interfaces.IClickListener;
import com.alex.pattern.base.BaseFragment;
import com.alex.pattern.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Alex
 */

public class HomeFragment extends BaseFragment implements HomeContract.View,
        IClickListener {

    private static final int LOAD_ON_LAST = 3;
    private static final String IS_SEARCH = "is_search";

    @Inject
    HomeContract.Presenter mPresenter;

    //region values
    private FragmentHomeBinding mBinding;
    private UsersAdapter mAdapter;
    private List<UserModel> mItems = new ArrayList<>();
    private boolean mIsLoading = false;
    private boolean mIsSearch = false;
    //endregion

    //region methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mPresenter.attachView(this);
        if (savedInstanceState != null) {
            mIsSearch = savedInstanceState.getBoolean(IS_SEARCH);
        }
        initToolbar();
        initRecyclerView();
        initEditText();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter.getUsers(NetworkUtils.isNetworkConnected(getActivity()), true);
    }

    @Override
    public void onDestroy() {
        if(getActivity().isFinishing()) {
            mPresenter.disposeAll();
        }
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            if (mBinding.editTextLogin.getVisibility() == View.GONE)
                mBinding.editTextLogin.setVisibility(View.VISIBLE);
            else {
                mBinding.editTextLogin.setVisibility(View.GONE);
                mPresenter.getUsers(NetworkUtils.isNetworkConnected(getActivity()), true);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(IS_SEARCH, mIsSearch);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void addUsers(List<UserModel> items) {
        mIsSearch = false;
        if (!items.isEmpty()) {
            mItems.addAll(items);
            mAdapter.addItems(items);
        }
    }

    @Override
    public void replaceUsers(List<UserModel> items) {
        mIsSearch = true;
        if (!items.isEmpty()) {
            mItems = items;
            mAdapter.replaceItems(items);
            mBinding.textViewEmpty.setVisibility(View.GONE);
        } else {
            mBinding.textViewEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNoInternet() {
        showToast(R.string.error_network_is_unavailable);
    }

    @Override
    public void showProgress() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        mIsLoading = true;
    }

    @Override
    public void hideProgress() {
        mBinding.progressBar.setVisibility(View.GONE);
        mIsLoading = false;
    }

    private void initToolbar() {
        mBinding.toolbar.setTitle(getString(R.string.home_screen));
        setToolbar(mBinding.toolbar, false);
    }

    private void initRecyclerView() {
        mBinding.recyclerViewUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mBinding.recyclerViewUsers.setLayoutManager(layoutManager);
        mAdapter = new UsersAdapter(getActivity());
        mAdapter.setClickListener(this);
        mBinding.recyclerViewUsers.setAdapter(mAdapter);
        mBinding.recyclerViewUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                if(!mIsLoading && !mIsSearch && lastVisibleItem >= totalItemCount - LOAD_ON_LAST) {
                    mPresenter.getUsers(NetworkUtils.isNetworkConnected(getActivity()), false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initEditText() {
        mPresenter.initLoginObservable(mBinding.editTextLogin);
        if(mIsSearch) {
            mBinding.editTextLogin.setVisibility(View.VISIBLE);
        }
    }
    //endregion

    //region views' actions
    @Override
    public void itemClicked(View view, int position) {
        mPresenter.openUserInfo(mItems.get(position).getLogin());
    }
    //endregion
}