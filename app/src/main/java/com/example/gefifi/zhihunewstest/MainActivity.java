package com.example.gefifi.zhihunewstest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.text_content)
    TextView contentText;

    @Bind(R.id.button_get)
    Button getButton;

    Retrofit retrofit;
//    Retrofit retrofitNetease;
    GetRequest_interface request;
//    GetRequest_interface requestNetease;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        getButton.setOnClickListener(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_get:
                newsRequest();
                break;
            default:
                break;
        }
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
