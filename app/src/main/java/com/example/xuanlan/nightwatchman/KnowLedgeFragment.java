package com.example.xuanlan.nightwatchman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class KnowLedgeFragment extends Fragment {

    private KnowLedgeApapter knowLedgeApapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private List<KnowLedge> knowLedgeList = new ArrayList<>();

    private String responseData;
    private JSONObject result;
    private JSONArray jsonArray;

    private View v;

    public KnowLedgeFragment() {
        HttpUtil.sendOkHttpRequest("https://guoliang.online/api/home_data", new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                try {
                    result = new JSONObject(responseData);
                    jsonArray = result.getJSONArray("result");
                    knowLedgeList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        knowLedgeList.add(new KnowLedge(jsonObject.getString("title"),
                                jsonObject.getString("content"),
                                jsonObject.getString("hot"),
                                jsonObject.getString("id")));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            knowLedgeApapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "已更新问答", Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.knowledge_main, container, false);
        knowLedgeApapter = new KnowLedgeApapter(knowLedgeList);
        RecyclerView recyclerView = v.findViewById(R.id.knowledge_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(knowLedgeApapter);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.knowledge_swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpUtil.sendOkHttpRequest("https://guoliang.online/api/home_data", new okhttp3.Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        responseData = response.body().string();
                        try {
                            result = new JSONObject(responseData);
                            jsonArray = result.getJSONArray("result");
                            knowLedgeList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                knowLedgeList.add(new KnowLedge(jsonObject.getString("title"),
                                        jsonObject.getString("content"),
                                        jsonObject.getString("hot"),
                                        jsonObject.getString("id")));
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    knowLedgeApapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "已更新问答", Toast.LENGTH_SHORT).show();
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            });

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "网络卒", Toast.LENGTH_SHORT).show();
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

