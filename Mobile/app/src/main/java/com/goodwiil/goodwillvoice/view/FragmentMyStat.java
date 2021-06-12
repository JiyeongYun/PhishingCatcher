package com.goodwiil.goodwillvoice.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.CardPagerAdapter;
import com.goodwiil.goodwillvoice.adapter.FragmentPagerAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentMyStatBinding;
import com.goodwiil.goodwillvoice.model.CardItem;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.ShadowTransformer;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class FragmentMyStat extends Fragment {

    private FragmentMyStatBinding mBinding;

    private FragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;
    public static FirebaseFirestore db;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentMyStatBinding.inflate(inflater, container, false);
        //mBinding.setViewModel(new SplashViewModel());
        mBinding.setLifecycleOwner(getActivity());

        db = FirebaseFirestore.getInstance();
        User user = AppDataManager.getUserModel();
        CollectionReference colRef = db.collection("UserCallLog").document(user.getNumber()).collection("CallNumbers");

        colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                            }
                            String text = "총 " + Integer.toString(count) + "개의 번호가 등록되어있습니다.";
                            mBinding.tvSignedNumber.setText(text);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });





        mFragmentCardAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager(), dpToPixels(2, getContext()));
        mFragmentCardShadowTransformer = new ShadowTransformer(mBinding.viewPager, mFragmentCardAdapter);
        mFragmentCardShadowTransformer.enableScaling(true);

        mBinding.viewPager.setAdapter(mFragmentCardAdapter);
        mBinding.viewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mBinding.viewPager.setOffscreenPageLimit(3);
        mBinding.idIndicator.setSelectedDotColor(ContextCompat.getColor(getContext(),R.color.colorBlue));
        mBinding.idIndicator.setSelectedDotDiameterDp(6);
        mBinding.idIndicator.setUnselectedDotDiameterDp(5);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.idIndicator.setSelectedItem(position, true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        View view = mBinding.getRoot();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }



}