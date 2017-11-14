package com.example.yh.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yh
 * on 2017/9/14.
 */

public class SecondActivity extends Activity {
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button2)
    public void onViewClicked() {
        EventBus.getDefault().post(new FirstEvent("FirstEvent btn has beclicked"));
//        EventBus.getDefault().post(new SecondEvent("SecondEvent btn has beclicked"));
//        EventBus.getDefault().post(new ThirdEvent("ThirdEvent btn has beclicked"));
    }
}
