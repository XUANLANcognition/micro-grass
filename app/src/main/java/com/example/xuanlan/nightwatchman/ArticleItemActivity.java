package com.example.xuanlan.nightwatchman;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ArticleItemActivity extends AppCompatActivity {

    private String responseData;
    private Article article;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_item);

        // 使用ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 设置Toolbar弹出icon
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        final WebView webView = (WebView) findViewById(R.id.article_item_content);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        ImageView imageView = (ImageView) findViewById(R.id.article_item_image);
        Glide.with(this)
                .load("https://cn.bing.com/az/hprichbg/rb/ManedWolf_ZH-CN10924002958_1920x1080.jpg")
                .into(imageView);

        // 获取文章自内容
        RequestBody requestBody = new FormBody.Builder()
                .add("articleId", id).build();
        final Request request = new Request.Builder()
                .url("https://guoliang.online/api/article_item")
                .post(requestBody)
                .build();
        HttpUtil.sendOkHttpRequestPost(request, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                try {
                    jsonObject = new JSONObject(responseData);
                    article = new Article(jsonObject.getString("title"),
                            jsonObject.getString("content"),
                            jsonObject.getString("lables"),
                            jsonObject.getString("pubDate"),
                            jsonObject.getString("hot"));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String temp = article.getContent().replace("<img", "<img style=\"max-width:100%;\"");
                            webView.loadData(temp, "text/html", "utf-8");
                            collapsingToolbarLayout.setTitle(article.getTitle());

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ArticleItemActivity.this, "网络卒", Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }
}
