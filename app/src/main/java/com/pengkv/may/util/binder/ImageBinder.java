package com.pengkv.may.util.binder;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ImageBinder {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }


//    @BindingAdapter("bind:contentList")
//    public static void setContentList(RecyclerView view, List<?> list) {
//        MainAdapter adapter = (MainAdapter) view.getAdapter();
//        adapter.addRows(list);
//    }
}
