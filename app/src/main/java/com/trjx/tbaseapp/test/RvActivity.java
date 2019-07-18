package com.trjx.tbaseapp.test;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.trjx.tbase.module.listmodule.TListListenter;
import com.trjx.tbase.module.listmodule.recyclerviewmodule.TRecyclerModule;
import com.trjx.tbaseapp.R;
import com.trjx.tbaseapp.base.DemoMVPActivity;
import com.trjx.tbaseapp.http.DemoModel;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.trjx.tlibs.uils.ToastUtil;

import java.util.List;

public class RvActivity extends DemoMVPActivity<RvView,RvPresenter>
implements RvView, TListListenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
    }


    private TRecyclerModule recyclerModule;

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试", false);

        recyclerModule = new TRecyclerModule<TestRvAdapter>() {

            @Override
            public int[] initSwipeRefresh() {
                return null;
            }

            @Override
            public RecyclerView.LayoutManager initLayoutManager() {
                return getGridLayoutManager(context,2);
            }

            @Override
            public TestRvAdapter createAdapter() {
                TestRvAdapter adapter = new TestRvAdapter(R.layout.item_test, null);
                adapter.setOnItemClickListener((adapter1, view, position) -> {
                    TestBean testBean = (TestBean) adapter1.getData().get(position);
                    SnackbarUtil.show(view,testBean.getName());
                    ToastUtil.showToast(context, testBean.getName());
                });
                return adapter;
            }
        };
        recyclerModule.setTListListenter(this);
        recyclerModule.initView(rootView,true);

        recyclerModule.setPage(1);
        getData(1);

    }

    @Override
    protected RvPresenter initPersenter() {
        return new RvPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }


    @Override
    public void testSuccess(List<TestBean> testBeanList) {
        recyclerModule.bindListData(testBeanList);
        recyclerModule.setRefreshing(false);

    }



    @Override
    public void exceptionPageClickEvent() {

    }

    @Override
    public void getData(int page) {
        getPresenter().testDate(page);
    }
}
