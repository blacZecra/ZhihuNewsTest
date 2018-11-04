package com.example.gefifi.zhihunewstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.text_content)
    TextView contentText;

    @Bind(R.id.button_get)
    Button getButton;

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
                break;
            default:
                break;
        }
    }
}
