package com.example.knowledgeaggregation.resultlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.Article;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class ArticleActivity extends AppCompatActivity {
    TextView title;
    TextView writer;
    TextView time;
    TextView source;
    TextView url;
    HtmlTextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articleeach);
        Intent it=getIntent();
        Article article=(Article)it.getSerializableExtra("article");
        title=findViewById(R.id.title);
        writer=findViewById(R.id.name);
        time=findViewById(R.id.time);
        source=findViewById(R.id.source);
        url=findViewById(R.id.url);
        content=(HtmlTextView)findViewById(R.id.htmlText);
        title.setText(article.getArticle_title());
        writer.setText(article.getArticle_writer());
        time.setText(article.getArticle_time());
        source.setText(article.getArticle_source());
        url.setText(article.getArticle_url());
        content.setHtml(article.getArticle_content(),new HtmlResImageGetter(content.getContext()));
    }
}
