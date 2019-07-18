package com.trjx.tbaseapp.test;

import androidx.annotation.NonNull;

import com.trjx.tbaseapp.base.DemoPresenter;

import java.util.ArrayList;
import java.util.List;

public class RvPresenter extends DemoPresenter<RvView> {

    public RvPresenter(@NonNull RvView view) {
        super(view);
    }


    private boolean isLoad = false;

    protected void testDate(int page) {

        List<TestBean> list = new ArrayList<>();
        if (isLoad) {
            if (page < 3) {
                TestBean testBean = null;
                for (int i = 0; i < 20; i++) {
                    testBean = new TestBean();
                    testBean.setName("name" + (i + 10));
                    testBean.setAddress("address" + (i + 100));
                    testBean.setAge(1000);
                    list.add(testBean);
                }
            }
        }else {
            isLoad = true;
        }

        getView().testSuccess(list);

    }

}
