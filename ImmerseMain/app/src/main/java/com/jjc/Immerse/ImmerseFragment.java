package com.jjc.Immerse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 单独 只沉浸的页面
 */
public class ImmerseFragment extends Fragment {


    public ImmerseFragment() {
        // Required empty public constructor
    }

    public static ImmerseFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ImmerseFragment fragment = new ImmerseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_immerse, container, false);
    }

}
