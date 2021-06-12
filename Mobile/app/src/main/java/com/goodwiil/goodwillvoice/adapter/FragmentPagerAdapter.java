package com.goodwiil.goodwillvoice.adapter;

import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.goodwiil.goodwillvoice.view.FragmentMyStatFirst;
import com.goodwiil.goodwillvoice.view.FragmentMyStatSecond;
import com.goodwiil.goodwillvoice.view.FragmentMyStatThird;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private FragmentMyStatFirst fragmentMyStatFirst;
    private FragmentMyStatSecond fragmentMyStatSecond;
    private FragmentMyStatThird fragmentMyStatThird;
    private float mBaseElevation;

    public FragmentPagerAdapter(FragmentManager fm, float baseElevation) {
        super(fm);
        mBaseElevation = baseElevation;

        fragmentMyStatFirst = new FragmentMyStatFirst();
        fragmentMyStatSecond = new FragmentMyStatSecond();
        fragmentMyStatThird = new FragmentMyStatThird();

    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        switch (position){
            case 0:
                return fragmentMyStatFirst.getCardView();
            case 1:
                return fragmentMyStatSecond.getCardView();
            case 2:
                return fragmentMyStatThird.getCardView();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fragmentMyStatFirst;
            case 1:
                return fragmentMyStatSecond;
            case 2:
                return fragmentMyStatThird;
            default:
                return null;
        }

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);


        return fragment;
    }



}
