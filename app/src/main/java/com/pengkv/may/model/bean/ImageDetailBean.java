package com.pengkv.may.model.bean;

import android.databinding.Bindable;

import com.pengkv.may.BR;
import com.pengkv.may.config.Basic;

import java.util.List;

/**
 * Created by pro on 2016/4/30.
 */
public class ImageDetailBean extends BaseBean {

    private int id;
    private int galleryclass;//          图片分类
    private String title;//          标题
    private String img;//          图库封面
    private int count;//          访问数
    private int rcount;//           回复数
    private int fcount;//          收藏数
    private int size;//      图片多少张
    private List<SimpleImageBean> list;// 简单图片类

    public ImageDetailBean() {
    }

    public ImageDetailBean(int id, int galleryclass, String title, String img, int count, int rcount, int fcount, int size, List<SimpleImageBean> list) {
        this.id = id;
        this.galleryclass = galleryclass;
        this.title = title;
        this.img = img;
        this.count = count;
        this.rcount = rcount;
        this.fcount = fcount;
        this.size = size;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public String getImg() {
        return Basic.URL_BASE_IMAGE + img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<SimpleImageBean> getList() {
        return list;
    }

    public void setList(List<SimpleImageBean> list) {
        this.list = list;
    }
}
