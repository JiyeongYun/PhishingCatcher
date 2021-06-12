package com.goodwiil.goodwillvoice.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardAdapter;
import com.goodwiil.goodwillvoice.util.ScreenManager;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class FragmentMyStatSecond extends Fragment {


    private CardView mCardView;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_stat_second, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);

        barChart = (BarChart)view.findViewById(R.id.bc_callLog);

        setBarChart();


        return view;
    }




    public CardView getCardView() { return mCardView; }

    private void setBarChart(){
        barChart.getDescription().setEnabled(false);
        //barChart.setDrawBarShadow(false);
        //barChart.setDrawValueAboveBar(true);
        //barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        //barChart.setTouchEnabled(false);
        //barChart.setBackgroundColor(Color.TRANSPARENT);
        //barChart.setDrawGridBackground(false);
        barChart.animateY(1000, Easing.EaseInOutCubic);
        barChart.getLegend().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        //xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);


        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        ArrayList<BarEntry> barEntries3 = new ArrayList<>();

        barEntries1.add(new BarEntry(1, 13));
        barEntries1.add(new BarEntry(2, 5));
        barEntries1.add(new BarEntry(3, 6));
        barEntries1.add(new BarEntry(4, 2));
        barEntries1.add(new BarEntry(5, 10));

        barEntries2.add(new BarEntry(1, 15));
        barEntries2.add(new BarEntry(2, 3));
        barEntries2.add(new BarEntry(3, 1));
        barEntries2.add(new BarEntry(4, 11));
        barEntries2.add(new BarEntry(5, 8));

        barEntries3.add(new BarEntry(1, 4));
        barEntries3.add(new BarEntry(2, 5));
        barEntries3.add(new BarEntry(3, 1));
        barEntries3.add(new BarEntry(4, 9));
        barEntries3.add(new BarEntry(5, 14));

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setColor(getContext().getColor(R.color.blue1));
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "");
        barDataSet2.setColor(getContext().getColor(R.color.blue2));
        BarDataSet barDataSet3 = new BarDataSet(barEntries3, "");
        barDataSet3.setColor(getContext().getColor(R.color.blue3));

        BarData data = new BarData(barDataSet1, barDataSet2, barDataSet3);
        barChart.setData(data);

        String[] types = new String[]{"지인", "대출", "광고", "학교", "기타"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(types));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        //barChart.setDragEnabled(true);
        barChart.getBarData().setValueTextSize(10);
        barChart.getBarData().setValueFormatter(new ScreenManager.MyValueFormatter());
        barChart.setVisibleXRangeMaximum(5);

        // (barWidth + barSpace) * group number + groupSpace = 1.00
        float barSpace = 0.05f;
        float groupSpace = 0.19f;
        float barWidth = 0.22f;

        data.setBarWidth(barWidth);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace,barSpace)*5);
        barChart.getAxisLeft().setAxisMinimum(0);


        barChart.groupBars(0,groupSpace,barSpace);


        barChart.invalidate();

    }




}