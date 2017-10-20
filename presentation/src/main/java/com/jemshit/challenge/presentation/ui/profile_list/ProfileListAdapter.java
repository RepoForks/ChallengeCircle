package com.jemshit.challenge.presentation.ui.profile_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jemshit.challenge.domain.model.UserModel;
import com.jemshit.challenge.presentation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder> {

    private Context context;
    private List<UserModel> userModelList;
    private ItemClickListener itemClickListener;

    public ProfileListAdapter(Context context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        UserModel model = userModelList.get(position);

        Glide.with(context)
                .load(model.getPictureUrl())
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.imageProfile);

        holder.textFullname.setText(model.getProfileFullname());
        holder.textAddress.setText(model.getAddress());
        holder.layoutRoot.setOnClickListener(v -> itemClickListener.onProfileClicked(holder.imageProfile, model.getProfileId(), model.getPictureUrl()));

    }

    @Override public int getItemCount() {
        return userModelList.size();
    }

    public void setUserModelList(List<UserModel> userModelList) {
        this.userModelList = new ArrayList<>(userModelList);
        notifyDataSetChanged();
    }

    static class ProfileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_root) ViewGroup layoutRoot;
        @BindView(R.id.image_profile) ImageView imageProfile;
        @BindView(R.id.text_fullname) TextView textFullname;
        @BindView(R.id.text_address) TextView textAddress;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
