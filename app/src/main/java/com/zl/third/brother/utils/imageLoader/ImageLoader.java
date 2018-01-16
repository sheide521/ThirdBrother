package com.zl.third.brother.utils.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.zl.third.brother.MyApplication;
import com.zl.third.brother.R;

import java.io.File;

/**
 * Created by zhenfei.wang on 2016/7/20.
 * 图片加载
 * 参考 ：http://www.cnblogs.com/yuzhongzheng/p/5228354.html
 */
public class ImageLoader {
    private static ImageLoader mInstance;

    public void displayImage(String path, ImageView imageView, int defaultId, int radiusDp) {
        if (path != null && imageView != null) {
            if (radiusDp > 0) { // 有圆角
                if (path.startsWith("http")) {
                    Glide.with(MyApplication.getAppInstance())
                            .load(path)
                            .placeholder(defaultId == 0 ? R.mipmap.image_loading : defaultId)
                            .transform(new GlideRoundTransform(MyApplication.getAppInstance(), radiusDp))
                            /**
                             * DiskCacheStrategy.NONE 什么都不缓存
                             DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
                             DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                             DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                             */
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            //                            .centerCrop()
                            .into(imageView);
                } else {
                    Glide.with(MyApplication.getAppInstance())
                            .load((Uri.fromFile(new File(path))))
                            .placeholder(defaultId == 0 ? R.mipmap.image_loading : defaultId)
                            .transform(new GlideRoundTransform(MyApplication.getAppInstance(), radiusDp))
                            .centerCrop()
                            .into(imageView);
                }


            } else {
                if (path.startsWith("http")) {
                    Glide.with(MyApplication.getAppInstance())
                            .load(path)
                            .placeholder(defaultId == 0 ? R.mipmap.image_loading : defaultId)
                            .thumbnail(0.3f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(imageView);
                } else {
                    Glide.with(MyApplication.getAppInstance())
                            .load(Uri.fromFile(new File(path)))
                            .placeholder(defaultId == 0 ? R.mipmap.image_loading : defaultId)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(imageView);
                }

            }
        }

    }

    public void displayCircleImage(String path, ImageView imageView, int defaultId) {
        if (path != null && imageView != null) {
            if (path.startsWith("http")) {
                Glide.with(MyApplication.getAppInstance())
                        .load(path)
                        .placeholder(R.mipmap.image_loading)
                        .transform(new GlideCircleTransform(MyApplication.getAppInstance()))
                        /**
                         * DiskCacheStrategy.NONE 什么都不缓存
                         DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
                         DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                         DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                         */
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        //                            .centerCrop()
                        .into(imageView);
            } else {
                Glide.with(MyApplication.getAppInstance())
                        .load((Uri.fromFile(new File(path))))
                        .placeholder(defaultId == 0 ? R.mipmap.image_loading : defaultId)
                        .transform(new GlideCircleTransform(MyApplication.getAppInstance()))
                        //                        .centerCrop()
                        .into(imageView);
            }
        }
    }


    public void displayOnlineImage(String path, ImageView imageView, int defaultId, int radiusDp) {
        Glide.with(MyApplication.getAppInstance())
                .load(path)
                .placeholder(defaultId == 0 ? R.mipmap.image_loading : defaultId)
                .transform(new GlideRoundTransform(MyApplication.getAppInstance(), radiusDp))
                /**
                 * DiskCacheStrategy.NONE 什么都不缓存
                 DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像
                 DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                 DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
                 */
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //                  .centerCrop()
                .into(imageView);
    }

    /**
     * 需要在子线程执行
     * 阻塞
     *
     * @param context
     * @param url
     * @return
     */
    public static Bitmap load(Context context, String url) {
        try {
            return Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }
}
