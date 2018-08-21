package com.example.xuanlan.nightwatchman;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private Context context;

    private List<Answer> answerList;

    public AnswerAdapter(List<Answer> answerList) {
        this.answerList = answerList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewContent;
        Button buttonPraise;
        TextView textViewPubdate;
        TextView textViewUsername;
        CircleImageView circleImageView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            textViewContent = (TextView) view.findViewById(R.id.answer_content);
            buttonPraise = (Button) view.findViewById(R.id.answer_praise);
            textViewPubdate = (TextView) view.findViewById(R.id.answer_pub_date);
            textViewUsername = (TextView) view.findViewById(R.id.answer_username);
            circleImageView = (CircleImageView) view.findViewById(R.id.answer_userPic);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.answer_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Answer answer = answerList.get(position);
        holder.textViewContent.setText(answer.getContent());
        holder.buttonPraise.setText("  " + answer.getPraise());
        holder.textViewPubdate.setText("发布于  " + answer.getPub_date());
        holder.textViewUsername.setText(answer.getUsername());
        Uri uri = Uri.parse(answer.getUserPic());
        Glide.with(context).load(uri).into(holder.circleImageView);
        Log.e("item", uri.toString());
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }
}
