package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CallRankAdapter;
import com.goodwiil.goodwillvoice.adapter.CardAdapter;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatFirstBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatThirdBinding;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.TopMaxCallItem;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.DurationSort;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class FragmentMyStatThird extends Fragment {

    private CardView mCardView;
    private CallRankAdapter callRankAdapter;
    private RecyclerView topCallLogList;
    private ArrayList<CallLogInfo> callLogInfos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_third, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        topCallLogList = (RecyclerView) view.findViewById(R.id.rv_top_call);

        setRecyclerView();

        return view;
    }


    public CardView getCardView() { return mCardView; }

    public void setRecyclerView(){

        // 사용자 통화내역 기록 가져오기
        callLogInfos = new ArrayList<>();
        ArrayList<CallLogInfo> callLogList = CallLogDataManager.getCallLog(getActivity(), 1);
        callLogList.sort(new DurationSort().reversed());

        topCallLogList.setLayoutManager(new LinearLayoutManager(getActivity()));

//        ArrayList<TopMaxCallItem> topMaxCallItems = new ArrayList<>();
        ArrayList<CallLogInfo> callLogInfos = new ArrayList<>();
        callLogInfos.add(new CallLogInfo("","054-260-222","학교","",3000,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","010-3906-5345","대출권유","",2544,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","010-2757-3906","보험광고","",1000,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","054-260-222","경찰서","",900,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","070-6644-5567","배달","",765,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","054-620-7722","은행","",542,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","02-080-4432","고객센터","",400,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","010-8501-5544","택배","",150,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","031-260-3322","광고","",80,0.0,0.0));
        callLogInfos.add(new CallLogInfo("","064-645-5324","광고","",10,0.0,0.0));

//        for(int i = 0; i < callLogList.size(); i++){
//            if(i == 0) callLogInfos.add(callLogList.get(i));
//
//            else{
//                if(callLogList.get(i).getNumber().equals(callLogList.get(i-1).getNumber())){
//                    callLogInfos.get(callLogInfos.lastIndexOf()).set
//                }
//                else{
//                    callLogInfos.add(callLogList.get(i));
//                }
//            }
//
//        }



        callRankAdapter = new CallRankAdapter(callLogInfos.subList(0,10));
        topCallLogList.setAdapter(callRankAdapter);


    }

}