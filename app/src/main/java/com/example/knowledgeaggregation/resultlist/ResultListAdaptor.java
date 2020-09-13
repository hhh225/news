package com.example.knowledgeaggregation.resultlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.Article;

import java.util.ArrayList;

public class ResultListAdaptor extends RecyclerView.Adapter<ResultListAdaptor.LinerViewHoler> {
    private Context context;
    private Onitem listener;
    private ArrayList<Article> arrayList;
    public ResultListAdaptor(Context c,ArrayList<Article> s,Onitem lis){//构造方法
        context=c;  //所在的activity
        arrayList=s;   //数据
        listener=lis;  //recyclerview中每个条目的点击事件
        System.out.println("adaptor num:"+s.size());
    }
    @NonNull
    @Override
    public ResultListAdaptor.LinerViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinerViewHoler(LayoutInflater.from(context).inflate(R.layout.articlerealeach,parent,false));//设置recyclerview所在的activity，以及recyclerview每个条目的显示样式articlerealeach.xml
    }

    @Override
    public void onBindViewHolder(@NonNull ResultListAdaptor.LinerViewHoler holder, final int position) {
        //设置每个条目的数据
        holder.title.setText(arrayList.get(position).getArticle_title());
        holder.time.setText(arrayList.get(position).getArticle_time());
        holder.author.setText(arrayList.get(position).getArticle_writer());
        //设置每个条目的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("itemcount:"+arrayList.size());
        return arrayList.size();
    }

    //代表每个条目的类
    class LinerViewHoler extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView time;
        private TextView author;


        public LinerViewHoler(@NonNull View itemView) {//构造方法
            super(itemView);
            //绑定条目中的内容
            title=(TextView)itemView.findViewById(R.id.title);
            time=(TextView)itemView.findViewById(R.id.time);
            author=(TextView)itemView.findViewById(R.id.writer);

        }
    }
    public  interface  Onitem{
        void onClick(int pos);
    }
}
