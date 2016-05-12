package com.pengkv.may.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pengkv.may.BR;
import com.pengkv.may.R;
import com.pengkv.may.config.ApiClient;
import com.pengkv.may.databinding.ActivityImageListBinding;
import com.pengkv.may.databinding.ItemImageBinding;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.model.bean.ListModel;
import com.pengkv.may.model.bean.SingleImageBean;
import com.pengkv.may.model.param.GetImageListParam;
import com.pengkv.may.util.SystemUtil;
import com.pengkv.may.widget.DividerItemDecoration;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class ImageListActivity extends BaseActivity implements IRequestHander {

    ListModel<SingleImageBean> mList;
    ActivityImageListBinding binding;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list);
        binding.rvData.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL, 20, ContextCompat.getColor(this, R.color.gray_light)));
        binding.rvData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL, 20, ContextCompat.getColor(this, R.color.gray_light)));
//        binding.rvData.addItemDecoration(new DividerGridItemDecoration(this));

        initToolbar(binding.bar.toolbar, "图片列表");

        fetchData(TAG_A);

    }

    @Override
    public void fetchData(int tag) {
        GetImageListParam param = new GetImageListParam("1", "10", "1");
        ApiClient.getImageList(this, param, tag);
    }

    @Override
    public void updateUI(Object response, int tag) {
        mList = (ListModel<SingleImageBean>) response;

        mAdapter = new MyAdapter(ImageListActivity.this, mList.getList());
        binding.rvData.setAdapter(mAdapter);
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private Context context;
        private List<SingleImageBean> list;

        public MyAdapter(Context context, List<SingleImageBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_image, parent, false);
            return new MyHolder(binding);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            SingleImageBean bean = list.get(position);
            ItemImageBinding binding = (ItemImageBinding) holder.getBinding();

            binding.setVariable(BR.image, bean);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            binding.executePendingBindings();

            int width = (SystemUtil.getDisplayWidth(context) - 20) / 2;
            int height = (int) (width * 3 / 2.0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            binding.llItem.setLayoutParams(params);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
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


}
