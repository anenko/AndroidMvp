package com.alex.pattern.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alex.pattern.base.BaseRecyclerAdapter;
import com.alex.pattern.data.models.UserModel;
import com.alex.pattern.databinding.ItemUserBinding;
import com.squareup.picasso.Picasso;

/**
 * Created by Alex
 */

public class UsersAdapter extends BaseRecyclerAdapter<UsersAdapter.ViewHolder, UserModel> {

    private Context mContext;

    UsersAdapter(Context context) {
        mContext = context;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        ItemUserBinding binding = ItemUserBinding.inflate(inflate, parent, false);
        return new UsersAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mList.get(position).getAvatarUrl())
                .into(holder.mBinding.imageViewAvatar);
        holder.mBinding.textViewLogin.setText(mList.get(position).getLogin());
        holder.mBinding.textViewUrl.setText(mList.get(position).getUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemUserBinding mBinding;

        ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            binding.setHolder(this);
        }

        public void clicks(View view) {
            mClickListener.itemClicked(view, getAdapterPosition());
        };
    }

}