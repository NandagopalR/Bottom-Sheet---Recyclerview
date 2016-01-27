package com.nanda.bottomsheetlistview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nanda.bottomsheetlistview.R;
import com.nanda.bottomsheetlistview.adapter.BottomSheetRecyclerviewAdapter;
import com.nanda.bottomsheetlistview.bottomsheet.BottomSheetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandagopal on 28-Jan-16.
 */
public class RecyclerViewTest extends BottomSheetFragment {

    private RecyclerView mRecyclerview;
    private List<String> mList = new ArrayList<>();
    private BottomSheetRecyclerviewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList.clear();
        for (int i = 0; i < 25; i++) {
            mList.add("Nanda Bottom List - " + i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview_bottomsheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview_bottomsheet);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new BottomSheetRecyclerviewAdapter(mActivity, mList);
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    makeToast("" + mList.get(position));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
