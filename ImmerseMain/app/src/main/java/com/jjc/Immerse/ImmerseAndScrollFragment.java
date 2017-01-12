package com.jjc.Immerse;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjc.Immerse.event.ScrollChangeEvent;
import com.jjc.Immerse.ui.ListenerScrollView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 沉浸到状态栏 并且滚动透明
 */
public class ImmerseAndScrollFragment extends Fragment {


    @Bind(R.id.lsv)
    ListenerScrollView lsv;

    public ImmerseAndScrollFragment() {
        // Required empty public constructor
    }

    public static ImmerseAndScrollFragment newInstance() {

        Bundle args = new Bundle();

        ImmerseAndScrollFragment fragment = new ImmerseAndScrollFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stastu_and_scroll, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        //设置接口监听
        lsv.setOnScrollChangeListener(new ListenerScrollView.onScrollChangeListener() {
            @Override
            public void onScrollChangeListener(float scrollX) {
                EventBus.getDefault().post(new ScrollChangeEvent(scrollX));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
