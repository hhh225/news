package com.example.knowledgeaggregation.webUtil;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.knowledgeaggregation.Utils;
import com.example.knowledgeaggregation.pojo.Article;
import com.example.knowledgeaggregation.pojo.RegisterUser;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.search.SearchActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class WebUtil {
    static String path="http://106.54.231.32:8000";

    public ArrayList<Article> search(String sentence,String userID,String addr){   //sentence是搜索的内容，addr是url，这里采用了get传送方式，由于后台使用requestparam
        String result="null";
        System.out.println("userid:"+userID);
        ArrayList<Article> articles = new ArrayList<>();
        HttpURLConnection conn = null;
        try {
            URL url = new URL(path + addr+"?keyword="+sentence+"&userID="+userID);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
  //          JSONObject jsonObject1 = new JSONObject();
 //           jsonObject1.put("keyword", sentence);

//            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//            os.write(("keyword"+sentence).getBytes());
//            os.flush();
//            os.close();
            conn.connect();
            InputStream inputStream=conn.getInputStream();
            result=Utils.getStringFromStream(inputStream);//读出返回的字符串

            org.json.JSONObject jsonObject;
            JSONArray resultJsonArray = new JSONArray(result);//由于返回的字符串是有arraylist转化而来的，因此需要将其转成JSON数组
            System.out.println("r:"+resultJsonArray.length());
            for(int i=0;i<resultJsonArray.length();i++){
                jsonObject = resultJsonArray.getJSONObject( i );
                Article article=new Article();
                //获取到json数据中的activity数组里的内容name
                article.setId(jsonObject.getString("id"));
                article.setArticle_content(jsonObject.getString("article_content"));
                Log.d("article_content", jsonObject.getString("article_content"));
                article.setArticle_source(jsonObject.getString("article_source"));
                article.setArticle_time(jsonObject.getString("article_time"));
                article.setArticle_url(jsonObject.getString("article_url"));
                article.setArticle_title(jsonObject.getString("article_title"));
                article.setArticle_writer(jsonObject.getString("article_writer"));
                articles.add(article);


                //获取到json数据中的activity数组里的内容startTime
                //存入map
            }
            //Log.d("msg",articles.toString());

            //conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
        }
        System.out.println("test1:"+articles.size());
//        final String[] result =new String[120];
//        OkHttpClient mokhttpclient=new OkHttpClient();
//
//        RequestBody requestBody= null;
//        //构造请求数据
//        requestBody = new FormBody.Builder().add("keyword", sentence).build();
//        Request request=new Request.Builder().url(path+addr).post(requestBody).build(); //请求发送封装类
//        Call call=mokhttpclient.newCall(request);  //发送请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d("fail","onfail:"+e.getMessage());
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {  //请求成功
//                result[0]=response.body().string();
//                Log.d("response","onresponse:"+result[0]);
//            }
//        });
//        return result[0];
        return articles;
    }

    public User registerProcess(User user, String addr){    //注册，这里使用post传送方式，由于后台使用requestbody
        int status=0;
        User returnuser=new User();
        try {
            URL url=new URL(path+addr);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id","");
            jsonObject.put("name",user.getName());
            jsonObject.put("pwd",user.getPwd());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("type",0);
            jsonObject.put("password","");

            DataOutputStream os=new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonObject.toString());
            os.flush();
            os.close();
            Log.d("status",String.valueOf(conn.getResponseCode()));
            if (conn.getResponseCode()!=200) System.out.println("false");
            else {
                System.out.println("success");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String message = "";
                while ((line=bufferedReader.readLine())!=null){
                    message+=line;
                }
                System.out.println(message);
                Log.d("regisreturnmess", message);
                org.json.JSONObject jsonObject1=new org.json.JSONObject(message);
                if (jsonObject1.getString("id")==null);
                else {
                    returnuser.setId(jsonObject1.getString("id"));
                    returnuser.setName(jsonObject1.getString("name"));
                    returnuser.setPwd(jsonObject1.getString("pwd"));
                    returnuser.setEmail(jsonObject1.getString("email"));
                    returnuser.setType(jsonObject1.getInt("type"));
                }
            }
            Log.d("msg",conn.getResponseMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnuser;
    }

    public boolean interes(String id,String tag,String addr){
        boolean suc=false;
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(path + addr+"?userID="+id+"&tagName="+tag);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            int code=conn.getResponseCode();
            if (code==HTTP_OK){
                suc=true;
            }
            //conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return suc;
    }
    public ArrayList<Article> recommend(User user,String addr){
        ArrayList<Article> arrayList=null;
        try {
            URL url=new URL(path+addr);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",user.getId());
            jsonObject.put("name",user.getName());
            jsonObject.put("pwd",user.getPwd());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("type",user.getType());
            jsonObject.put("password","");

            DataOutputStream os=new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonObject.toString());
            os.flush();
            os.close();
            Log.d("status",String.valueOf(conn.getResponseCode()));
            if (conn.getResponseCode()!=200) System.out.println("false");
            else {
                System.out.println("success");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String message = "";
                while ((line=bufferedReader.readLine())!=null){
                    message+=line;
                }//返回的文章数据
                org.json.JSONObject jsonObject1;
                JSONArray resultJsonArray = new JSONArray(message);//由于返回的字符串是有arraylist转化而来的，因此需要将其转成JSON数组
                System.out.println("r:"+resultJsonArray.length());
                for(int i=0;i<resultJsonArray.length();i++){
                    jsonObject1 = resultJsonArray.getJSONObject( i );
                    Article article=new Article();
                    //获取到json数据中的activity数组里的内容name
                    article.setId(jsonObject.getString("id"));
                    article.setArticle_content(jsonObject.getString("article_content"));
                    Log.d("article_content", jsonObject.getString("article_content"));
                    article.setArticle_source(jsonObject.getString("article_source"));
                    article.setArticle_time(jsonObject.getString("article_time"));
                    article.setArticle_url(jsonObject.getString("article_url"));
                    article.setArticle_title(jsonObject.getString("article_title"));
                    article.setArticle_writer(jsonObject.getString("article_writer"));
                    arrayList.add(article);


                    //获取到json数据中的activity数组里的内容startTime
                    //存入map
                }
            }
            Log.d("msg",conn.getResponseMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
