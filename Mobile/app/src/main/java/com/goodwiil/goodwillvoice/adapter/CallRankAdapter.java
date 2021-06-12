package com.goodwiil.goodwillvoice.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.TopMaxCallItem;
import com.goodwiil.goodwillvoice.util.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class CallRankAdapter extends RecyclerView.Adapter<CallRankAdapter.ItemViewHolder>  {

    private List<CallLogInfo> topMaxCallItems;

    public CallRankAdapter(List<CallLogInfo> topMaxCallItems){
        this.topMaxCallItems = topMaxCallItems;
    }


    @NonNull
    @Override
    public CallRankAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.top_call_time_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallRankAdapter.ItemViewHolder holder, int position) {

        holder.itemId.setText(Integer.toString(position+1));
        holder.itemNumber.setText(topMaxCallItems.get(position).getNumber());
        holder.itemType.setText(topMaxCallItems.get(position).getType());
        holder.itemLength.setText(ScreenManager.secondsToString(Integer.toString(topMaxCallItems.get(position).getDuration())));

    }

    @Override
    public int getItemCount() {
        return topMaxCallItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView itemId;
        private TextView itemNumber;
        private TextView itemType;
        private TextView itemLength;

        ItemViewHolder(View view){
            super(view);
            itemId = view.findViewById(R.id.tv_id);
            itemNumber = view.findViewById(R.id.tv_number);
            itemType = view.findViewById(R.id.tv_type);
            itemLength = view.findViewById(R.id.tv_call_time);

        }
    }
}
