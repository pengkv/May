package com.pengkv.may.model.param;

/**
 * Created by pro on 2016/4/30.
 */
public class GetImageListParam extends BaseParam {
    private int page;//	请求页数，默认page=1
    private int rows;//	每页返回的条数，默认rows=20
    private int id;//	分类ID，默认返回的是全部。这里的ID就是指分类的ID

    public GetImageListParam(int page, int rows, int id) {
        this.page = page;
        this.rows = rows;
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
