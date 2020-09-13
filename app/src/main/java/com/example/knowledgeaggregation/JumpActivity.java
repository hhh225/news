package com.example.knowledgeaggregation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.knowledgeaggregation.htmlTest.HtmlTestActivity;
import com.example.knowledgeaggregation.register.RegisterActivity;
import com.example.knowledgeaggregation.search.SearchActivity;
import com.example.knowledgeaggregation.userMessage.MessageChange2Activity;

public class JumpActivity extends AppCompatActivity {
    Button search;
    Button register;
    Button login;
    Button changeMess;
    Button htmltest;
    Button testintent;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        search=findViewById(R.id.button5);
        register=findViewById(R.id.button6);
        login=findViewById(R.id.button7);
        changeMess=findViewById(R.id.button8);
        htmltest=findViewById(R.id.buttonhtmltest);
        testintent=findViewById(R.id.testintent);

        it=getIntent();
        it.putExtra("test2",15);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it=new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(it);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(it);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });
        changeMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it=new Intent(getApplicationContext(), MessageChange2Activity.class);
                startActivity(it);
            }
        });
        htmltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it=new Intent(getApplicationContext(), HtmlTestActivity.class);
                startActivity(it);
            }
        });
    }
}
