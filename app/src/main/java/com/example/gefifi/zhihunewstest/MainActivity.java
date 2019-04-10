package com.example.gefifi.zhihunewstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
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
    GetRequest_interface request;

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
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        request = retrofit.create(GetRequest_interface.class);
        Call<Translation> call = request.getCall();
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                response.body().show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }
}
