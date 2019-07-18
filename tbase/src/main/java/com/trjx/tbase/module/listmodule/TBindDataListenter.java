package com.trjx.tbase.module.listmodule;

public interface TBindDataListenter<L> {

    /**
     * 创建适配器
     */
    void createAdapter();

    /**
     * 绑定数据
     * @param listData
     */
    void bindListData(L listData);


    /**
     *  点击条目
     * @param position 位置
     * @param list 集合数据
     */
//    <B> void onItemClick(B bean, int position);

}
