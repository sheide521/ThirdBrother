package com.zl.third.brother.widghts.cardViewNew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.third.brother.R;
import com.zl.third.brother.beans.SwipeCardBean;
import com.zl.third.brother.utils.imageLoader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Zhaolong on 2018/1/15.
 * 卡片
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    public ArrayList<SwipeCardBean> mData;
    public Context context;

    public CardAdapter(ArrayList<SwipeCardBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_view_new, null);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(mData.get(position).getImg(), holder.avatarImageView, 0, 0);
        holder.name.setText(mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatarImageView;
        public ImageView likeImageView;
        public ImageView dislikeImageView;
        public TextView name;

        public CardViewHolder(View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.iv_avatar);
            likeImageView = itemView.findViewById(R.id.iv_like);
            dislikeImageView = itemView.findViewById(R.id.iv_dislike);
            name = itemView.findViewById(R.id.tv_name);
        }
    }
}
