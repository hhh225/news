package com.example.knowledgeaggregation.userMessage;

import android.content.Intent;
import android.os.Bundle;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.search.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.knowledgeaggregation.userMessage.ui.main.SectionsPagerAdapter;

public class MessageChange2Activity extends AppCompatActivity {
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_change2);
        navigationView=findViewById(R.id.nv1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String itemvalue=String.valueOf(item.getTitle());
                if (itemvalue.equals("搜索"))
                {
                    User user = (User) getIntent().getSerializableExtra("user");
                    Intent it=new Intent(getApplicationContext(), SearchActivity.class);
                    it.putExtra("user",user);
                    startActivity(it);
                }
                return false;
            }//
        });
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}