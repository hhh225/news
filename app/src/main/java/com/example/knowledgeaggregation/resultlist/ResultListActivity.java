package com.example.knowledgeaggregation.resultlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.Article;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.search.SearchActivity;

import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {
    RecyclerView resultList=null;
    TextView returnSearch=null;
    ArrayList<Article> articles=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);   //
        returnSearch=findViewById(R.id.returnSearch);//返回搜索按钮
        //返回搜索事件
        returnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent();
                User user= (User) getIntent().getSerializableExtra("user");
                it.setClass(getApplicationContext(),SearchActivity.class);
                it.putExtra("user",user);
                ResultListActivity.this.startActivity(it);
            }
        });
        resultList=findViewById(R.id.resultlist);    //recyclerview
        resultList.setLayoutManager(new LinearLayoutManager(ResultListActivity.this));
        resultList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //获取list<article>
        Intent intent = getIntent();
        articles = (ArrayList<Article>)intent.getSerializableExtra("articles");
        System.out.println("get data from intent:"+articles.size());
        //给recyclerview设置适配器，传递给构造方法的参数——activity本身、每个条目的内容、每个条目的点击事件
        resultList.setAdapter(new ResultListAdaptor(ResultListActivity.this,articles, new ResultListAdaptor.Onitem() {
            @Override
            public void onClick(int pos) {
                //Intent intent = getIntent();
                Intent intent = new Intent();
                intent.putExtra("article",articles.get(pos));
                intent.setClass(ResultListActivity.this,ArticleActivity.class);
                ResultListActivity.this.startActivity(intent);
                //finish();
            }
        }));

    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            //这个方法的返回值决定了listview会展示多少数据
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //通过这个方法可以把某个具体的数据条目展示到listview里
            TextView textView=null;
            if (view==null){
                //如果没满就新建
                textView=new TextView(ResultListActivity.this);
            }
            else{
                //如果满了就重用
                textView=(TextView)view;
            }
            textView.setText("text:"+i);

            return textView;
        }
    }
}
