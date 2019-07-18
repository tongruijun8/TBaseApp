package com.trjx.tbaseapp.test;


import android.view.View;
import android.widget.TextView;

import com.trjx.tbase.module.listmodule.recyclerviewmodule.TBaseRvViewHolder;
import com.trjx.tbaseapp.R;

public class TestRvViewHolder extends TBaseRvViewHolder {

    TextView textView;
    TextView textView2;
    TextView textView3;

    public TestRvViewHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.item_test_name);
        textView2 = view.findViewById(R.id.item_test_age);
        textView3 = view.findViewById(R.id.item_test_address);

    }
}
