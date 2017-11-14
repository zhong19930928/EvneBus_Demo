package com.example.yh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yh
 * on 2017/9/14.
 * eventbus学习网址：http://blog.csdn.net/harvic880925/article/details/40660137
 */

public class FristActivity extends Activity {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_fristpage);
        EventBus.getDefault().register(this);//注册eventbus
        ButterKnife.bind(this);//注册ButterKnife
    }

    @OnClick({R.id.textView, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView:
                break;
            case R.id.button:
                startActivity(new Intent(this,SecondActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void first(FirstEvent event){
        String mas =event.getMsg();
        textView.setText(mas);
        Toast.makeText(this,mas,Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void second(SecondEvent event){
        String mas =event.getMsg();
        textView.setText(mas);
        Toast.makeText(this,mas,Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void second(ThirdEvent event){
        String mas =event.getMsg();
        textView.setText(mas);
        Toast.makeText(this,mas,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//注销eventbus
    }

    /**
     * onEventMainThread:如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，
     *                   onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，
     *                   因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
     *onEventBackground:如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground
     *                   就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground
     *                   函数直接在该子线程中执行。
     *onEventAsync：使用这个函数作为订阅函数，那么无论事件在哪个线程发布，
     *                   都会创建新的子线程在执行onEventAsync.
     */
}
