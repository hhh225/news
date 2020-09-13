package com.example.knowledgeaggregation.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.knowledgeaggregation.MainActivity;
import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.Article;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.resultlist.ResultListActivity;
import com.example.knowledgeaggregation.search.SearchActivity;
import com.example.knowledgeaggregation.webUtil.WebUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class InteresActivity extends AppCompatActivity {
    Button btn_java;
    Button btn_cpp;
    Button btn_python;
    Button btn_next;
    @BindView(R.id.graph)
    Button graph;
    @BindView(R.id.dataStruc)
    Button dataStr;
    //ArrayList<String> inters;
    WebUtil webUtil=new WebUtil();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interes);
        Intent intent=getIntent();
        user=(User) intent.getSerializableExtra("user");
        //inters=new ArrayList<>();
        btn_java=findViewById(R.id.button_java);
        btn_cpp=findViewById(R.id.button_cpp);
        btn_python=findViewById(R.id.button_python);
        btn_next=findViewById(R.id.nextstep);
        graph=findViewById(R.id.graph);
        dataStr=findViewById(R.id.dataStruc);
        btn_java.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //inters.add("java");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean response=webUtil.interes(user.getId(),"java","/setUserTag");
                        Looper.prepare();
                        if (response) {
                            System.out.println("success this time");
                            Toast.makeText(InteresActivity.this,"添加了java",Toast.LENGTH_SHORT).show();

                        }
                        else System.out.println("fail this time");
                        Looper.loop();
                        //finish();

                    }
                }).start();

            }
        });
        btn_cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inters.add("c++");
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        boolean response=webUtil.interes(user.getId(),"C++","/setUserTag");
                        Looper.prepare();
                        if (response) {
                            System.out.println("success this time");
                            Toast.makeText(InteresActivity.this,"添加了C++",Toast.LENGTH_SHORT).show();
                        }
                        else System.out.println("fail this time");
                        //finish();
                        Looper.loop();
                    }
                }).start();
            }
        });
        btn_python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean response=webUtil.interes(user.getId(),"python","/setUserTag");
                        Looper.prepare();
                        if (response) {
                            System.out.println("success this time");
                            Toast.makeText(InteresActivity.this,"添加了python",Toast.LENGTH_SHORT).show();
                        }
                        else System.out.println("fail this time");
                        //finish();
                        Looper.loop();
                    }
                }).start();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=getIntent();
                it.setClass(getApplicationContext(), MainActivity.class);
                InteresActivity.this.startActivity(it);
            }
        });
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean response=webUtil.interes(user.getId(),"图","/setUserTag");
                        Looper.prepare();
                        if (response) {
                            System.out.println("success this time");
                            Toast.makeText(InteresActivity.this,"添加了图",Toast.LENGTH_SHORT).show();
                        }
                        else System.out.println("fail this time");
                        //finish();
                        Looper.loop();
                    }
                }).start();
            }
        });
        dataStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean response=webUtil.interes(user.getId(),"数据结构","/setUserTag");
                        Looper.prepare();
                        if (response) {
                            System.out.println("success this time");
                            Toast.makeText(InteresActivity.this,"添加了数据结构",Toast.LENGTH_SHORT).show();
                        }
                        else System.out.println("fail this time");
                        //finish();
                        Looper.loop();
                    }
                }).start();
            }
        });
    }
}
