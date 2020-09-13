package com.example.knowledgeaggregation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;


import com.example.knowledgeaggregation.register.InteresActivity;
import com.example.knowledgeaggregation.register.RegisterActivity;
import com.example.knowledgeaggregation.search.SearchActivity;
import com.example.knowledgeaggregation.userMessage.MessageChange2Activity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        final Thread myThread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    Intent it=new Intent();
                        it.setClass(getApplicationContext(), JumpActivity.class);
                        it.putExtra("test1",12);
                    startActivity(it);
                    //finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
