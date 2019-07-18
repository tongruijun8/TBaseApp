package com.trjx.tbase.module.listmodule.recyclerviewmodule;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.trjx.R;
import com.trjx.tbase.module.listmodule.TListModule;

import java.util.List;

/**
 * @author tong
 * @date 2018/5/3 12:13
 * RecyclerView 模块化
 * <p>
 * 待研究
 */
public abstract class TRecyclerModule<A extends TRecyclerAdapter> extends TListModule {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    public TRecyclerModule() {
        super();
    }

    @Override
    public void initView(View rootView) {
        this.initView(rootView, true);
    }

    public void initView(View rootView, boolean isRefresh) {
        super.initView(rootView);
        swipeRefreshLayout = rootView.findViewById(R.id.swiperefreshlayout);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        isShowListLayout(true);
        initSwipeRefreshLayout();
        swipeRefreshLayout.setColorSchemeColors(colors);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (null != listenter) {
                page = 1;
                listenter.getData(page);
            }
        });
        recyclerView.setLayoutManager(initLayoutManager());
        swipeRefreshLayout.setRefreshing(isRefresh);
        initAdapter();
    }

    /**
     * 获取 RecyclerView 控件
     *
     * @return
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private int colors[] = {
            Color.rgb(47, 223, 189),
            Color.rgb(223, 47, 189),
            Color.rgb(189, 223, 47),
            Color.rgb(47, 55, 80)
    };

    /**
     * 初始化刷新设置
     */
    private void initSwipeRefreshLayout() {
        int[] cc = initSwipeRefresh();
        if (cc == null || cc.length == 0) {
            return;
        }
        colors = cc;
    }

    /**
     * 是否显示XListView
     *
     * @param isShow
     */
    @Override
    public void isShowListLayout(boolean isShow) {
        if (isShow) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化刷新按钮的样式
     *
     * @return
     */
    public abstract int[] initSwipeRefresh();

    /**
     * 初始化布局管理
     */
    public abstract RecyclerView.LayoutManager initLayoutManager();

    /**
     * 获取线性布局
     * @param context
     * @return
     */
    public static LinearLayoutManager getLinearLayoutManager(Context context){
        return new LinearLayoutManager(context);
    }

    /**
     * 获取表格布局
     * @param context
     * @param num
     * @return
     */
    public static GridLayoutManager getGridLayoutManager(Context context,int num){

        GridLayoutManager layoutManager = new GridLayoutManager(context, num);
        return layoutManager;
    }

    /**
     * 获取瀑布流
     * @param num
     * @return
     */
    public static StaggeredGridLayoutManager getStaggeredGridLayoutManager(int num){
        return new StaggeredGridLayoutManager(num, StaggeredGridLayoutManager.VERTICAL);
    }


    /**
     * 创建适配器
     */
    public abstract A  createAdapter();

    private A adapter;

    public A getAdapter() {
        return adapter;
    }


    private void initAdapter() {

//        adapter = new TestRecyclerAdapter<B>(R.layout.item_recycler, null) {
//            @Override
//            protected void convert(TestBaseViewHolder helper, Users item) {
//                helper.setBinding(variableId, item);
//            }
//        };

        if (listenter != null) {
            adapter = createAdapter();
        } else {
            return;
        }

        adapter.setOnLoadMoreListener(() -> {
            if (listenter != null) {
                page++;
                listenter.getData(page);
            }
        }, recyclerView);

        //添加条目点击事件
//        adapter.setOnItemClickListener((adapter, view, position) -> {
////                B bean = (B) adapter.getData().get(position);
//
////                if (bindDataListenter != null) {
////                    bindDataListenter.onItemClick(bean, position);
////                }
//
////                adapter.notifyItemChanged(position);
////                adapter.getData().remove(position);
////                adapter.notifyItemRemoved(position);
////                adapter.notifyDataSetChanged();
//
////                mData.add("新增");
////                homeAdapter.notifyItemInserted(pos);
//        });


        adapter.openLoadAnimation();
//        homeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);

        recyclerView.setAdapter(adapter);


    }

    public <L> void bindListData(List<L> listData) {
        this.bindListData(listData, false);//默认显示
    }
    public <L> void bindListData(List<L> listData,boolean gone) {

        if (adapter == null) {
            return;
        }
        final int size = listData == null ? 0 : listData.size();
        if (page == 1) {
            adapter.setNewData(listData);
            if (listData == null || size == 0) {
                isShowDefLayout(true);
            }else{
                isShowDefLayout(false);
            }

        } else {
            if (size > 0) {
                adapter.addData(listData);
            }
        }

        if (size < pageSize) {
            //false：显示结尾没有更多数据，反之不显示
            adapter.loadMoreEnd(gone);
        } else {
            adapter.loadMoreComplete();
        }

    }


    public void setRefreshing(boolean isRefresh){
        swipeRefreshLayout.setRefreshing(isRefresh);
    }


}
