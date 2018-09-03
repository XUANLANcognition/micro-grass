package com.example.xuanlan.nightwatchman;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KnowLedgeItemActivity extends AppCompatActivity {

    private String responseData;
    private JSONObject result;
    private JSONArray jsonArray;
    private AnswerAdapter answerAdapter;
    private List<Answer> answerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_ledge_item);

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
        final String data = intent.getStringExtra("title");
        String data1 = intent.getStringExtra("body");
        String data2 = intent.getStringExtra("id");
        TextView textView = (TextView) findViewById(R.id.knowledge_item_title);
        TextView textView1 = (TextView) findViewById(R.id.knowledge_item_body);
        textView.setText(data);
        textView1.setText(data1);

        // 回答数量
        final TextView textViewAnswerNum = (TextView) findViewById(R.id.answer_num);

        // 获取回答数据
        RequestBody requestBody = new FormBody.Builder()
                .add("questionId", data2).build();
        final Request request = new Request.Builder()
                .url("https://guoliang.online/api/question_item")
                .post(requestBody)
                .build();
        HttpUtil.sendOkHttpRequestPost(request, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                try {
                    result = new JSONObject(responseData);
                    jsonArray = result.getJSONArray("answer");
                    answerList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        answerList.add(new Answer(jsonObject.getString("content"),
                                jsonObject.getString("pub_date"),
                                jsonObject.getString("user"),
                                jsonObject.getString("praise"),
                                jsonObject.getString("username"),
                                jsonObject.getString("userPic")));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            answerAdapter.notifyDataSetChanged();
                            Toast.makeText(KnowLedgeItemActivity.this, "已更新回答", Toast.LENGTH_SHORT).show();
                            textViewAnswerNum.setText("共 " + answerList.size() + " 条回答");
                        }
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(KnowLedgeItemActivity.this, "网络卒", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // 设置适配器
        answerAdapter = new AnswerAdapter(answerList);
        RecyclerView recyclerView = findViewById(R.id.answer_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(answerAdapter);
    }
}
