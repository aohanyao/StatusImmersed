package com.jjc.Immerse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjc.Immerse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoImmerseFragment extends Fragment {


    public NoImmerseFragment() {
        // Required empty public constructor
    }

    public static NoImmerseFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NoImmerseFragment fragment = new NoImmerseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_immerse, container, false);
    }

}
