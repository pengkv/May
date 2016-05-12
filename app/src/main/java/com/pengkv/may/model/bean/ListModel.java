package com.pengkv.may.model.bean;


import android.databinding.BaseObservable;

import java.util.List;

/**
 * 列表数据实体基类
 * <p>
 * Created by lyq on 2014/8/21.
 */
public class ListModel<T> extends BaseObservable {

    private int mPageIndex = 1;//页码，默认1
    private int mPageSize = 1;//一页取的条数

    private List<T> list;//数据实体列表
    private int total;//总条数

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
