package com.pengkv.may.model.param;

/**
 * Created by pro on 2016/4/30.
 */
public class GetImageDetailParam extends BaseParam {
    private String id;//	图库的id

    public GetImageDetailParam(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
