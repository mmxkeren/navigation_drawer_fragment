package com.kitatolong.arie.tes004;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Arie on 2/25/2016.
 */
public class ListWarungFragment extends Fragment {
    public ListWarungFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_warung, container, false);
        return rootView;
    }
}
