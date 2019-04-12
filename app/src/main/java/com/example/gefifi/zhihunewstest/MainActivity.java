package com.example.gefifi.zhihunewstest;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    public static final String webAdd = "https://news-at.zhihu.com/api/4/news/";

    @Bind(R.id.news_listView)
    ListView newsListView;

    NewsAdapter newsAdapter;
    List<News.StoryBean> newsList;

//    @Bind(R.id.button_get)
//    Button getButton;

    Retrofit retrofit;
//    Retrofit retrofitNetease;
    GetRequest_interface request;
//    GetRequest_interface requestNetease;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    newsListView.setAdapter(newsAdapter);
                    newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            News.StoryBean storyBean = newsList.get(i);
                            Toast.makeText(MainActivity.this, storyBean.getId(), Toast.LENGTH_SHORT).show();
                            toWeb(storyBean);
                        }
                    });
                    break;
            }
        }
    };

    private void toWeb(News.StoryBean storyBean) {
        //https://news-at.zhihu.com/api/4/news/3892357
        String id = storyBean.getId();
        String storyURL = webAdd + id;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(storyURL));
        startActivity(intent);
    }

    @Override
    protected void initData() {
        newsRequest();
    }

    @Override
    protected void initListener() {
//        getButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                Toast.makeText(MainActivity.this, "登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh:
                newsRequest();
                break;
            default:
                break;
        }
        return true;
    }

    private void newsRequest() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        request = retrofit.create(GetRequest_interface.class);
        Call<News> call = request.getCall();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                response.body().show();
                Toast.makeText(MainActivity.this, response.body().getStories().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                newsList = response.body().getStories();
                newsList.addAll(response.body().getTop_stories());
                newsAdapter = new NewsAdapter(MainActivity.this, R.layout.news_item, newsList);
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                System.out.println("请求失败");
                System.out.println(t.getMessage());
            }
        });

//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://fy.iciba.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        request = retrofit.create(GetRequest_interface.class);
//        Call<Translation> call = request.getCall();
//        call.enqueue(new Callback<Translation>() {
//            @Override
//            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                response.body().show();
//            }
//
//            @Override
//            public void onFailure(Call<Translation> call, Throwable t) {
//                System.out.println("连接失败");
//            }
//        });

        //有道翻译POST
//        retrofitNetease = new Retrofit.Builder()
//                        .baseUrl("http://fanyi.youdao.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        requestNetease = retrofitNetease.create(GetRequest_interface.class);
//        Call<TransNetease> callN = requestNetease.getCall("fuck you");
//        callN.enqueue(new Callback<TransNetease>() {
//            @Override
//            public void onResponse(Call<TransNetease> call, Response<TransNetease> response) {
//                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
//            }
//
//            @Override
//            public void onFailure(Call<TransNetease> call, Throwable t) {
//                System.out.println("请求失败");
//                System.out.println(t.getMessage());
//            }
//        });
    }
}
