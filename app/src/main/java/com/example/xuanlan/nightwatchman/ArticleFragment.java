package com.example.xuanlan.nightwatchman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ArticleFragment extends Fragment {

    private ArticleAdapter articleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Article> articleList = new ArrayList<>();

    private String responseData;
    private JSONObject result;
    private JSONArray jsonArray;

    private View v;

    public ArticleFragment() {
        HttpUtil.sendOkHttpRequest("https://guoliang.online/api/article_home", new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                try {
                    result = new JSONObject(responseData);
                    jsonArray = result.getJSONArray("result");
                    articleList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        articleList.add(new Article(jsonObject.getString("title"),
                                jsonObject.getString("username"),
                                jsonObject.getString("pub_date"),
                                jsonObject.getString("user"),
                                jsonObject.getString("hot"),
                                jsonObject.getString("userPic")));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            articleAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "已更新文章", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络卒", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.article_main, container, false);
        articleAdapter = new ArticleAdapter(articleList);
        RecyclerView recyclerView = v.findViewById(R.id.article_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(articleAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.article_swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpUtil.sendOkHttpRequest("https://guoliang.online/api/article_home", new okhttp3.Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        responseData = response.body().string();
                        try {
                            result = new JSONObject(responseData);
                            jsonArray = result.getJSONArray("result");
                            articleList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                articleList.add(new Article(jsonObject.getString("title"),
                                        jsonObject.getString("username"),
                                        jsonObject.getString("pub_date"),
                                        jsonObject.getString("user"),
                                        jsonObject.getString("hot"),
                                        jsonObject.getString("userPic")));
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    articleAdapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "已更新文章", Toast.LENGTH_SHORT).show();
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "网络卒", Toast.LENGTH_LONG).show();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                });
            }
        });
        return v;
    }
}
