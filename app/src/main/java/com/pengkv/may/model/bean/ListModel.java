package com.pengkv.may.model.bean;


import java.util.List;

/**
 * 列表数据实体基类
 *
 * Created by lyq on 2014/8/21.
 */
public class ListModel<T> {

    /**
     * 数据实体列表
     */
    List<T> list;

    /**
     * 总条数
     */
    private int total;

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
