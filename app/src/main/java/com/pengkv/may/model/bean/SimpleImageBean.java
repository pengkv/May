package com.pengkv.may.model.bean;

import com.pengkv.may.config.Basic;

/**
 * Created by pro on 2016/4/30.
 */
public class SimpleImageBean extends BaseBean {
    private int id;
    private int gallery; //图片库
    private String src; //图片地址

    public SimpleImageBean() {
    }

    public SimpleImageBean(int id, int gallery, String src) {
        this.id = id;
        this.gallery = gallery;
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public String getSrc() {
        return Basic.URL_BASE_IMAGE + src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
