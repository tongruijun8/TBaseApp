package com.trjx.tbaseapp.test;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trjx.tbase.module.listmodule.recyclerviewmodule.TRecyclerAdapter;

import java.util.List;

public class TestRvAdapter extends TRecyclerAdapter<TestBean,TestRvViewHolder> {

    public TestRvAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(TestRvViewHolder helper, TestBean item) {
        helper.textView.setText(item.getName());
        helper.textView2.setText(item.getAge()+"");
        helper.textView3.setText(item.getAddress());
    }
}
