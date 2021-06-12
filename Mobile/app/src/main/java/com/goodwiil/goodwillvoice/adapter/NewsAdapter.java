package com.goodwiil.goodwillvoice.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwiil.goodwillvoice.BR;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.NewsItemBinding;
import com.goodwiil.goodwillvoice.model.NewsItem;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityNewsLetter;
import com.goodwiil.goodwillvoice.view.ActivitySignUp;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsItem> newsItems;

    public NewsAdapter(){
        this.newsItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding mBinding = NewsItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, final int position) {
        NewsItem item = newsItems.get(position);
        holder.bind(item);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ActivityNewsLetter.class);
                intent.putExtra("url", newsItems.get(position).getUrl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    void setItem(List<NewsItem> items){
        if (items != null){
            this.newsItems = items;
            notifyDataSetChanged();
        }
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView recyclerView, ObservableArrayList<NewsItem> items){
        NewsAdapter adapter = (NewsAdapter) recyclerView.getAdapter();

        if(adapter != null){
            adapter.setItem(items);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        NewsItemBinding mBinding;

        public ViewHolder(NewsItemBinding mBinding){
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        void bind(NewsItem item){
            String title = item.getNewsTitle();
            String description = item.getNewsDescription();

            mBinding.setVariable(BR.news, item);
            mBinding.setVariable(BR.title, title);
            mBinding.setVariable(BR.description, description);


            if(item.getThumbnail().equals("sample")) mBinding.ivParcelStat.setImageResource(R.drawable.news_sample_image);
            else if(item.getThumbnail().equals("sample2")) mBinding.ivParcelStat.setImageResource(R.drawable.news_sample_image2);
            else if(item.getThumbnail().equals("sample3")) mBinding.ivParcelStat.setImageResource(R.drawable.news_sample_image3);
        }

    }
}
