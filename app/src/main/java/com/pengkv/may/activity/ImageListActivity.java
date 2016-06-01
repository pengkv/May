package com.pengkv.may.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.pengkv.may.R;
import com.pengkv.may.adapter.MyAdapter;
import com.pengkv.may.config.ApiClient;
import com.pengkv.may.databinding.ActivityImageListBinding;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.interfaces.OnScrollBottomListener;
import com.pengkv.may.model.bean.ListModel;
import com.pengkv.may.model.bean.SingleImageBean;
import com.pengkv.may.model.param.GetImageListParam;
import com.pengkv.may.widget.SpacesItemDecoration;

/**
 * Created by Administrator on 2016/5/12.
 * 图片列表页
 */
public class ImageListActivity extends BaseActivity implements IRequestHander {

    private ListModel<SingleImageBean> mList = new ListModel<>();
    private ActivityImageListBinding binding;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        binding.rvData.setLayoutManager(mLayoutManager);
        binding.rvData.addItemDecoration(new SpacesItemDecoration(10));

        mAdapter = new MyAdapter(ImageListActivity.this, mList.getList());
        binding.rvData.setAdapter(mAdapter);

        binding.rvData.addOnScrollListener(new OnScrollBottomListener(mAdapter, mLayoutManager, new OnScrollBottomListener.onBottomListener() {
            @Override
            public void onBottomListener() {
                if (!mList.isCompelete())//未加载全部数据
                    fetchData(TAG_A);
            }
        }));


        binding.viewSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.reset();
                fetchData(TAG_A);
            }
        });

        initToolbar(binding.bar.toolbar, "图片列表");

        fetchData(TAG_A);

    }

    @Override
    public void fetchData(int tag) {
        GetImageListParam param = new GetImageListParam(mList.getPageIndex(), mList.getPageSize(), 1);
        ApiClient.getImageList(this, param, tag);
    }

    @Override
    public void updateUI(Object response, int tag) {
        binding.viewSwipe.setRefreshing(false);
        mList.read(response);
        mAdapter.notifyDataSetChanged();
    }

}
