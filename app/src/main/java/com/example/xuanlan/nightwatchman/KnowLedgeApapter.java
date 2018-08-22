package com.example.xuanlan.nightwatchman;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class KnowLedgeApapter extends RecyclerView.Adapter<KnowLedgeApapter.ViewHolder> {

    private Context context;

    private List<KnowLedge> knowLedgeList;

    public KnowLedgeApapter(List<KnowLedge> knowLedgeList) {
        this.knowLedgeList = knowLedgeList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewHead;
        TextView textViewBody;
        TextView textViewHot;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            textViewHead = (TextView) view.findViewById(R.id.knowledge_head);
            textViewBody = (TextView) view.findViewById(R.id.knowledge_body);
            textViewHot = (TextView) view.findViewById(R.id.knowledge_hot);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.knowledge_card, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                KnowLedge knowLedge = knowLedgeList.get(position);
                Intent intent = new Intent(v.getContext(), KnowLedgeItemActivity.class);
                intent.putExtra("title", knowLedge.getHead());
                intent.putExtra("body", knowLedge.getBody());
                intent.putExtra("id", knowLedge.getId());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KnowLedge knowLedge = knowLedgeList.get(position);
        holder.textViewHead.setText(knowLedge.getHead());
        holder.textViewBody.setText(knowLedge.getBody());
        holder.textViewHot.setText("浏览  " + knowLedge.getHot());
        if (knowLedge.getBody() == "") {
            holder.textViewBody.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return knowLedgeList.size();
    }
}
