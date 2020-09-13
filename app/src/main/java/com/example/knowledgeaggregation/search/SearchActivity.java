package com.example.knowledgeaggregation.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.Article;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.resultlist.ResultListActivity;
import com.example.knowledgeaggregation.userMessage.MessageChange2Activity;
import com.example.knowledgeaggregation.webUtil.WebUtil;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText searchText=null;
    Button searchButt=null;
    Toolbar mtoolbar;
    NavigationView nv;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        it=getIntent();
        nv=findViewById(R.id.nav);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemvalue=String.valueOf(item.getTitle());
                if (itemvalue.equals("修改信息"))
                {
                    Intent it=new Intent(getApplicationContext(), MessageChange2Activity.class);
                    startActivity(it);
                }
                return false;
            }//
        });
        searchButt=findViewById(R.id.buttonSearch);
        searchText=findViewById(R.id.searchText);
        searchButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"点击了",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WebUtil webUtil=new WebUtil();
                        System.out.println("开始搜索");
                        String editinput=searchText.getText().toString();
                        Log.d("editinput",editinput);
                        Intent it=getIntent();
                        User user=(User)it.getSerializableExtra("user");
                        System.out.println("useridsearch:"+user.getId());
                        ArrayList<Article> articles=webUtil.search(editinput,user.getId(),"/esUserSearch"); //传输搜索的参数、搜索的url
                        //System.out.println("result:"+result);
                        //Log.d("result","onresult:"+result);
                        //Intent intent = new Intent();
                        it.putExtra("articles",articles);
                        //intent.putExtra("user",user);
                        it.setClass(SearchActivity.this,ResultListActivity.class);
                        SearchActivity.this.startActivity(it);
                        finish();

                    }
                }).start();
            }
        });
    }
}
