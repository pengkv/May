package com.pengkv.may.model.bean;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pengkv.may.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengkv on 2016/5/14.
 * 列表数据实体基类
 */
public class ListModel<T> extends BaseObservable {

    private int pageIndex = 1;//页码，默认1
    private int pageSize = 10;//一页取的条数
    private boolean isCompelete;//是否加载完毕

    private List<T> list = new ArrayList<>();//数据实体列表
    private int total;//总条数

    @Bindable
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list.addAll(list);

        if (this.list.size() == total) isCompelete = true;

        notifyPropertyChanged(BR.list);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public int getPageIndex() {
        return pageIndex++;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isCompelete() {
        return isCompelete;
    }

    public void setCompelete(boolean compelete) {
        isCompelete = compelete;
    }

    public void reset() {
        pageIndex = 1;
        total = 0;
        list.clear();
        isCompelete = false;
    }


    public void read(Object response) {
        if (response instanceof ListModel) {
            ListModel listModel = (ListModel) response;
            setTotal(listModel.getTotal());
            setList(listModel.getList());
        } else {
            throw new ClassCastException("注意: response需为ListModel类型！");
        }
    }
}
