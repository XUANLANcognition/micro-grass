package com.example.xuanlan.nightwatchman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleAdapter extends RecyclerView.Adapter <ArticleAdapter.ViewHolder> {

    private Context context;

    private List<Article> articleList;

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewTitle;
        CircleImageView circleImageViewUserPic;
        TextView textViewUsername;
        TextView textViewPubDate;
        ImageView imageViewBack;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            textViewTitle = view.findViewById(R.id.article_title);
            circleImageViewUserPic = view.findViewById(R.id.article_item_user_pic);
            textViewUsername = view.findViewById(R.id.article_item_username);
            textViewPubDate = view.findViewById(R.id.article_item_pub_date);
            imageViewBack = view.findViewById(R.id.article_background);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.article_card, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Article article = articleList.get(position);
                Intent intent = new Intent(v.getContext(), ArticleItemActivity.class);
                intent.putExtra("id", article.getId());
                intent.putExtra("userPic",article.getUserPic());
                intent.putExtra("username", article.getUsername());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.textViewTitle.setText(article.getTitle());
        Glide.with(context).load(article.getUserPic()).into(holder.circleImageViewUserPic);
        holder.textViewUsername.setText(article.getUsername());
        holder.textViewPubDate.setText(article.getPubDate());
        Glide.with(context)
                .load("https://cn.bing.com/az/hprichbg/rb/FranceMenton_ZH-CN8996032014_1920x1080.jpg")
                .into(holder.imageViewBack);
        holder.imageViewBack.setImageAlpha(50);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
