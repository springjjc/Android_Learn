package com.example.administrator.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Administrator on 2019/3/20.
 */
public class UpAddListView extends ListView implements AbsListView.OnScrollListener {
    private View footer;
    private Boolean isAdding=false;
    private int totalItems,totalItemCount;
    private IUpAddListener iUpAddListener;
    public interface  IUpAddListener
    {
       void OnAdd();
    }

    private void initView(Context context)
    {
        footer= LayoutInflater.from(context).inflate(R.layout.footer_layout,null);
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }
    public UpAddListView(Context context) {
        super(context);
        initView(context);
    }

    public UpAddListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UpAddListView(Context context, AttributeSet attrs, int defStyleAttr, View footer) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        if(totalItemCount==totalItems&&scrollState==SCROLL_STATE_IDLE)
        {
            if(!isAdding)
            {
                footer.findViewById(R.id.ll_footer).setVisibility(View.VISIBLE);
                iUpAddListener.OnAdd();
                isAdding=true;
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        totalItems=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }
    public  void setInterface(IUpAddListener iUpAddListener)
    {
        this.iUpAddListener=iUpAddListener;
    }
    public void addComleted()
    {
        isAdding=false;
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);
    }

}
