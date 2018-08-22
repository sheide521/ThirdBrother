package com.zl.third.brother.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zl.third.brother.R;
import com.zl.third.brother.activity.BleActivity;
import com.zl.third.brother.activity.CardViewActivity;
import com.zl.third.brother.activity.ConstraintLayoutTestActivity;
import com.zl.third.brother.activity.WebActivity;
import com.zl.third.brother.activity.ZhifubaoActivity;
import com.zl.third.brother.utils.netty.NettyTCPClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaolong on
 * 2017/11/14
 * 资讯
 */

public class NewsFragment extends BaseFragment {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.tv_constraintLayout)
    TextView tvConstraintLayout;
    @Bind(R.id.tv_card_view_new)
    TextView tvCardViewNew;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tv_netty)
    TextView tvNetty;
    @Bind(R.id.tv_web)
    TextView mTvWeb;

    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(bActivity));
        recyclerView.setAdapter(mAdapter = new HomeAdapter());
        initData();

        return view;

    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'G'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(bActivity).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

    @OnClick({R.id.tv_constraintLayout, R.id.tv_card_view_new, R.id.tv_city, R.id.tv_netty, R.id.tv_web,
            R.id.tv_zhifubao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_constraintLayout:
                startActivity(new Intent(bActivity, ConstraintLayoutTestActivity.class));
                break;
            case R.id.tv_card_view_new:
                startActivity(new Intent(bActivity, CardViewActivity.class));
                break;
            case R.id.tv_city:
                startActivity(new Intent(bActivity, BleActivity.class));
                break;
            case R.id.tv_netty:
                //                new Thread(new Runnable() {
                //                    @Override
                //                    public void run() {
                NettyTCPClient.goNet();
                //                    }
                //                }).start();
                //                System.out.println("Client：");
                //                Log.d("123","Client：" );
                break;
            case R.id.tv_web:
                startActivity(new Intent(bActivity, WebActivity.class));
                break;
            case R.id.tv_zhifubao:
                startActivity(new Intent(bActivity, ZhifubaoActivity.class));
                break;
            default:
                break;
        }
    }
}
