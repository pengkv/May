package com.pengkv.may.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 * 基础适配器
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListAdapter.MyHolder> {

    private Context context;
    private List<T> list;

    public BaseListAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(BaseListAdapter.MyHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;


        public MyHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
