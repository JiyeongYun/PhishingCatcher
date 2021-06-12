package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.adapter.NewsAdapter;
import com.goodwiil.goodwillvoice.databinding.FragmentCallLogBinding;
import com.goodwiil.goodwillvoice.databinding.FragmentNewsBinding;
import com.goodwiil.goodwillvoice.model.NewsItem;


public class FragmentNews extends Fragment {
    private FragmentNewsBinding mBinding;
    private NewsAdapter newsAdapter;
    private ObservableArrayList<NewsItem> newsItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);




        newsItems = new ObservableArrayList<>();
        newsItems.add(new NewsItem("sample", "반사회적 범죄 ‘보이스피싱’ 척결 종합방안, 어떤 대책 담겼나", "디지털 경제·금융의 신뢰 기반 조성을 위해 ‘보이스피싱 척결 종합방안’ 마련\n" +
                "[보안뉴스 원병철 기자] 중대한 반사회적 민생침해 범죄행위인 보이스피싱을 척결하기 위해, 9개 정부부처 및 기관들이 손잡고 전방위적인 예방·차단시스템 구축, 강력한 단속과 엄정한 처벌, 실효성 있는 피해구제 등을 담은 종합방안을 마련해 시행한다고 밝혔다.",
                "https://www.boannews.com/media/view.asp?idx=89206&kind=2"));

        newsItems.add(new NewsItem("sample2", "검찰, ‘보이스피싱 서류 진위 감별’ 찐센터 설립 한달 만 37건 성과",
                "[아시아경제 최석진 기자] 검찰을 사칭한 보이스피싱 범죄를 예방하기 위해 지난 9월 말 서울중앙지검에 설치된 '보이스피싱 서류, 진짜인지 알려줘 콜센터'(약칭 '찐센터')가 한달 사이 37건의 보이스피싱을 예방했다.",
                "https://view.asiae.co.kr/article/2020110317311719552"));

        newsItems.add(new NewsItem("sample3","지인 사칭 메신저피싱 올들어 25% 급증","올해 1~9월 피해건수 6799건, 15%↑\n" +
                "피해액은 297억원으로 25%↑\n" +
                "“문자·카카오톡 통한 지인 사칭 피해 유의”","http://www.hani.co.kr/arti/economy/finance/968313.html"));


        mBinding.rvNews.setLayoutManager(
                new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false)
        );


        newsAdapter = new NewsAdapter();

        mBinding.rvNews.setAdapter(newsAdapter);
        mBinding.setNewsList(newsItems);


        return mBinding.getRoot();
    }


}