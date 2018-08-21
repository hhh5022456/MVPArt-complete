package me.jessyan.mvpart.demo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paginate.Paginate;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.art.base.BaseActivity;
import me.jessyan.art.base.DefaultAdapter;
import me.jessyan.art.mvp.IView;
import me.jessyan.art.mvp.Message;
import me.jessyan.art.utils.ArtUtils;
import me.jessyan.mvpart.demo.R;
import me.jessyan.mvpart.demo.mvp.presenter.TimePresenter;
import me.jessyan.mvpart.demo.mvp.presenter.UserPresenter;
import me.jessyan.mvpart.demo.mvp.ui.adapter.UserAdapter;

import static me.jessyan.art.utils.Preconditions.checkNotNull;

public class TiesActivity extends BaseActivity<TimePresenter> implements IView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.mRecy)
    RecyclerView mRecy;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private UserAdapter mAdapter;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private RxPermissions mRxPermissions;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        ArtUtils.statuInScreen(TiesActivity.this);// 全屏,并且沉侵式状态栏
        return R.layout.activity_ties;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        initRecyclerView();
        mRecy.setAdapter(mAdapter);
        initPaginate();
        mPresenter.requestUsers(Message.obtain(this, new Object[]{true}));//打开app时自动加载列表
    }

    @Override
    @Nullable
    public TimePresenter obtainPresenter() {
        this.mRxPermissions = new RxPermissions(this);
        this.mAdapter = new UserAdapter(new ArrayList<>());
        return new TimePresenter(ArtUtils.obtainAppComponentFromContext(this), mAdapter, mRxPermissions);
    }


    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArtUtils.snackbarText(message);
    }

    @Override
    public void handleMessage(@NonNull Message message) {
        checkNotNull(message);
        switch (message.what) {
            case 0:
                isLoadingMore = true;//开始加载更多
                break;
            case 1:
                isLoadingMore = false;//结束加载更多
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    public void onRefresh() {
        mPresenter.requestUsers(Message.obtain(this, new Object[]{true}));
    }


    private void initRecyclerView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        ArtUtils.configRecyclerView(mRecy,new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }


    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestUsers(Message.obtain(TiesActivity.this, new Object[]{false}));
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecy, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(mRecy);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
        this.mRxPermissions = null;
        this.mPaginate = null;
    }


}
