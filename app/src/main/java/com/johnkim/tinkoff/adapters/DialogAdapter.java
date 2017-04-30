package com.johnkim.tinkoff.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johnkim.tinkoff.R;
import com.johnkim.tinkoff.api.DialogItem;

import java.util.ArrayList;

public class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DialogItem> dataset;

    public DialogAdapter(ArrayList<DialogItem> dataset) {
        this.dataset = dataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_left, parent, false);
            return new LeftHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_right, parent, false);
        return new RightHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position))
        {
            case 0:

                RightHolder rightHolder = (RightHolder) holder;
                rightHolder.title.setText(dataset.get(position).getText());
                break;

            case 1:

                LeftHolder leftHolder = (LeftHolder)holder;
                leftHolder.title.setText(dataset.get(position).getText());
                break;

        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (dataset.get(position).getSenderId() == 0)
        {
            return 0;
        } else
        {
            return 1;
        }
    }

    public static class LeftHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public LeftHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewMsg);
        }

    }

    public static class RightHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public RightHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewMsg);
        }

    }
}