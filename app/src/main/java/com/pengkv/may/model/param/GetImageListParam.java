package com.pengkv.may.model.param;

/**
 * Created by pro on 2016/4/30.
 */
public class GetImageListParam extends BaseParam {
  private String page;//	请求页数，默认page=1
  private String rows	;//	每页返回的条数，默认rows=20
  private String id;//	分类ID，默认返回的是全部。这里的ID就是指分类的ID

    public GetImageListParam(String page, String rows, String id) {
        this.page = page;
        this.rows = rows;
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
