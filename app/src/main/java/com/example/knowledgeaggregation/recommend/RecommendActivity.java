package com.example.knowledgeaggregation.recommend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.Article;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.resultlist.ArticleActivity;
import com.example.knowledgeaggregation.resultlist.ResultListActivity;
import com.example.knowledgeaggregation.resultlist.ResultListAdaptor;
import com.example.knowledgeaggregation.search.SearchActivity;
import com.example.knowledgeaggregation.webUtil.WebUtil;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {
    RecyclerView resultList=null;
    TextView returnSearch=null;
    WebUtil webUtil=new WebUtil();
    ArrayList<Article> articles=null;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        it=getIntent();
        final User user= (User) it.getSerializableExtra("user");
        System.out.println("recommendUser:"+user.getId());
        //获取recyclerview
        resultList=findViewById(R.id.resultlist);    //recyclerview
        resultList.setLayoutManager(new LinearLayoutManager(RecommendActivity.this));
        resultList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //获取list<article>
        Intent intent = getIntent();
        new Thread(new Runnable() {
            @Override
            public void run() {
                articles=webUtil.recommend(user,"/getCommandList");
                System.out.println("get data from intent:"+articles.size());
                resultList.setAdapter(new ResultListAdaptor(RecommendActivity.this,articles, new ResultListAdaptor.Onitem() {
                    @Override
                    public void onClick(int pos) {
                        //Intent intent = getIntent();
                        //Intent intent = new Intent();
                        it.putExtra("article",articles.get(pos));
                        it.setClass(RecommendActivity.this, ArticleActivity.class);
                        RecommendActivity.this.startActivity(it);
                        //finish();
                    }
                }));
            }
        }).start();

        //给recyclerview设置适配器，传递给构造方法的参数——activity本身、每个条目的内容、每个条目的点击事件

    }
}
