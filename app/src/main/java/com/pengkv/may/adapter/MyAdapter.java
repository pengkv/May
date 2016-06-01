package com.pengkv.may.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pengkv.may.BR;
import com.pengkv.may.R;
import com.pengkv.may.databinding.ItemImageBinding;
import com.pengkv.may.model.bean.SingleImageBean;
import com.pengkv.may.util.SystemUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyAdapter extends BaseListAdapter<SingleImageBean> {
    private Context context;
    private List<SingleImageBean> list;

    public MyAdapter(Context context, List<SingleImageBean> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_image, parent, false);
        return new MyHolder(binding);
    }


    @Override
    public void onBindViewHolder(BaseListAdapter.MyHolder holder, int position) {
        SingleImageBean bean = list.get(position);
        ItemImageBinding binding = (ItemImageBinding) holder.getBinding();

        binding.setVariable(BR.image, bean);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        binding.executePendingBindings();

        int width = (SystemUtil.getDisplayWidth(context) - 0) / 2;
        int height = (int) (width * 3 / 2.0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        binding.llItem.setLayoutParams(params);
    }
}
