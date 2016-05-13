package com.pengkv.may.interfaces;

/**
 * Created by pro on 2016/4/30.
 */
public interface IRequestHander {

    int TAG_A = 0;
    int TAG_B = 1;
    int TAG_C = 2;
    int TAG_D = 3;
    int TAG_E = 4;

    void fetchData(int tag);

    void updateUI(Object response, int tag);

}
