package com.goodwiil.goodwillvoice.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.SubItemAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentCallLogBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;
import com.goodwiil.goodwillvoice.model.CallLogData;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.CallNumber;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FragmentCallLog extends Fragment {
    private FragmentCallLogBinding mBinding;
    private ArrayList<CallLogInfo> callLogInfos;
    private SubItemAdapter itemAdapter;
    private ArrayList<CallLogInfo> callLogInfos100;
    private HashMap<String, String> callNumbers;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = FragmentCallLogBinding.inflate(inflater, container, false);
        //mBinding.setViewModel(new SplashViewModel());
        mBinding.setLifecycleOwner(getActivity());

        callLogInfos = new ArrayList<>();

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
            callLogInfos = CallLogDataManager.getCallLog(getContext(), 1);
        }

//        if(callLogInfos.size() == 0)
//            swipeDownImage.setVisibility(View.VISIBLE);
//        else
//            swipeDownImage.setVisibility(View.GONE);


        if (callLogInfos.size() > 100) {
            callLogInfos100 = new ArrayList<CallLogInfo>(callLogInfos.subList(0, 100));
        }

        else{
            callLogInfos100 = callLogInfos;
        }

        callNumbers = CallLogDataManager.getFireBaseCallLog();


        itemAdapter = new SubItemAdapter(getActivity(), callLogInfos100, callNumbers);
        mBinding.headerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.headerRecyclerView.setAdapter(itemAdapter);

        mBinding.phoneSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                    callLogInfos = CallLogDataManager.getCallLog(getContext(), 1);
                    itemAdapter = new SubItemAdapter(getActivity(), callLogInfos100, callNumbers);
                    mBinding.headerRecyclerView.setAdapter(itemAdapter);
                    mBinding.phoneSwipeRefreshLayout.setRefreshing(false);
                }

                }
            });





        return mBinding.getRoot();
    }


}