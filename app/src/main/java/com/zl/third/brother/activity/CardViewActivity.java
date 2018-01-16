package com.zl.third.brother.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.zl.third.brother.R;
import com.zl.third.brother.beans.SwipeCardBean;
import com.zl.third.brother.widghts.cardViewNew.CardAdapter;
import com.zl.third.brother.widghts.cardViewNew.CardConfig;
import com.zl.third.brother.widghts.cardViewNew.CardItemTouchHelperCallback;
import com.zl.third.brother.widghts.cardViewNew.CardLayoutManager;
import com.zl.third.brother.widghts.cardViewNew.OnSwipeListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaolong on
 * 2017/12/20
 * 卡片
 */

public class CardViewActivity extends BaseActivity {
    @Bind(R.id.activity_review)
    RecyclerView recyclerView;
    private ArrayList<SwipeCardBean> mList = new ArrayList<>();
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        adapter = new CardAdapter(mList, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(adapter, mList);
        cardCallback.setOnSwipedListener(new OnSwipeListener<SwipeCardBean>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                CardAdapter.CardViewHolder myHolder = (CardAdapter.CardViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, SwipeCardBean o, int direction) {
                CardAdapter.CardViewHolder myHolder = (CardAdapter.CardViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                Toast.makeText(bActivity, direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(bActivity, "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                    }
                }, 3000L);
            }

        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void initData() {
        String[] intimage = new String[]{
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015121521&di=2cb9bd7c381d0960c457c98801f41fc2&imgtype=0&src=http%3A%2F%2Fimg.bjlmfq.com%2FUserEdit%2Fattached%2Fimage%2F20151104%2F20151104104189918991.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015713499&di=1b8be42c51c016269b6cdacdbe08ad26&imgtype=0&src=http%3A%2F%2Fwww.huakaimoshang.com%2Fupload%2Fimage%2F2012%2F10%2F1350122182-2552.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015768682&di=501d13ab40843ccce96bbf76c5e0062f&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160321%2F16-160321135502.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015810587&di=3d8977dd16ad825c273b6f17496e95fd&imgtype=0&src=http%3A%2F%2Fwww.wanhuajing.com%2Fpic%2F1603%2F2210%2F104407%2F5_640_453.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015822900&di=239520cdfa11a297f9d793c3c1a95ca8&imgtype=0&src=http%3A%2F%2Flady.southcn.com%2F6%2Fimages%2Fattachement%2Fjpg%2Fsite4%2F20160322%2F90fba609e427185b028004.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015836368&di=392987c4c31f0770f0c5731ed59245c7&imgtype=0&src=http%3A%2F%2Fimgcache.cjmx.com%2Fdrama%2F201605%2F20160519181128573.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015847520&di=4286bda9f4d6232f563bae2b43f622d5&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170803%2F57a528c367fc422aace1e4c56e5cfc9f_th.png",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516015860794&di=9d3d35fff283ae1aee32883f7bbd42e2&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-10-27%2F59f2fa195810f.jpg%3Fdown",
        };

        for (int i = 0; i < 8; i++) {
            SwipeCardBean swpe = new SwipeCardBean();
            swpe.setImg(intimage[i]);
            swpe.setTitle("小姐姐" + i);
            mList.add(swpe);
        }
    }
}
