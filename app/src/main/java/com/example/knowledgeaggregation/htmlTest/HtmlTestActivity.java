package com.example.knowledgeaggregation.htmlTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.knowledgeaggregation.R;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class HtmlTestActivity extends AppCompatActivity {
    HtmlTextView htmlTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_test);
        htmlTextView=findViewById(R.id.htmltest);
        htmlTextView.setHtml("<h2>Hello wold</h2><ul><li>cats</li><li>dogs</li></ul><img src=\"https://img-blog.csdnimg.cn/20200907230124336.png\"/>",
                new HtmlResImageGetter(htmlTextView.getContext()));
    }
}
