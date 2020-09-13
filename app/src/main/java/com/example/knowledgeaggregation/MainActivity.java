package com.example.knowledgeaggregation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.knowledgeaggregation.pojo.Article;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.recommend.RecommendActivity;
import com.example.knowledgeaggregation.register.RegisterActivity;
import com.example.knowledgeaggregation.resultlist.ArticleActivity;
import com.example.knowledgeaggregation.resultlist.ResultListActivity;
import com.example.knowledgeaggregation.search.SearchActivity;
import com.example.knowledgeaggregation.webUtil.WebUtil;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    private EditText mETUser,mETPass;
    private Button mBtnGetLogin,mBtnPostLogin,mBtnForget,mBtnRegister;
    private TextView mTVReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mETUser = findViewById(R.id.et_User);
        mETPass = findViewById(R.id.et_Pass);
        //mBtnGetLogin = findViewById(R.id.btn_GetLogin);
        mBtnPostLogin = findViewById(R.id.btn_PostLogin);  //登录按钮
        mBtnForget = findViewById(R.id.btn_Forget);
        mBtnRegister = findViewById(R.id.btn_Register);
       // mTVReceive = findViewById(R.id.tv_Receive);
        setOnClick();
    }
    private void setOnClick()
    {
        OnClick onClick =new OnClick();
        //mBtnGetLogin.setOnClickListener(onClick);
        mBtnPostLogin.setOnClickListener(onClick);
        mBtnForget.setOnClickListener(onClick);
        mBtnRegister.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
//                case R.id.btn_GetLogin:
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            String user = mETUser.getText().toString().trim();
//                            String pass = mETPass.getText().toString().trim();
//                            //get方式提交，参数放到url后面
//                            try {
//                                //如果提交的参数包含中文，必须先进行URL编码
//                                // URLEncoder.encode(user,"utf-8")参数，编码方式
//                                String path = "http://192.168.0.109:8080/webServletTest/login?userName="+ URLEncoder.encode(user,"utf-8")+"&password="+pass;
//                                URL url = new URL(path);
//                                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                                //设置请求的方法，方法要大写，默认采用GET
//                                connection.setRequestMethod("GET");
//                                //设置连接超时时间
//                                connection.setConnectTimeout(10000);
//
//                                connection.connect();
//                                //获取响应码
//                                int code = connection.getResponseCode();
//                                if(code == 200){
//                                    InputStream inputStream = connection.getInputStream();
//                                    String result = "HttpGet: "+Utils.getStringFromStream(inputStream);
//                                    Log.d("result",result);
//                                    showToast(result);
//                                }
//                                connection.disconnect();
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }.start();
//                    break;
                case R.id.btn_PostLogin:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String path = "http://106.54.231.32:8000";
                            String user = mETUser.getText().toString().trim();
                            String pass = mETPass.getText().toString().trim();
                            if(user.length()==0||pass.length()==0){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(),"username or password is empty!",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                                return;
                            }
                            try {
                                //设置url参数
                                URL url=new URL(path+"/userLogin");
                                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                                conn.setRequestMethod("POST");
                                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                                conn.setRequestProperty("Accept","application/json");
                                conn.setDoOutput(true);
                                conn.setDoInput(true);
                                //创建对象
                                User user1=new User();
                                user1.setName(user);
                                user1.setPwd(pass);
                                String jsonString= JSON.toJSONString(user1);
                                //传递数据
                                DataOutputStream os=new DataOutputStream(conn.getOutputStream());
                                os.writeBytes(jsonString);
                                os.flush();
                                os.close();
                                //接收response
                                Log.d("status",String.valueOf(conn.getResponseCode()));

                                Log.d("msg",conn.getResponseMessage());
                                //不是200
                                if (conn.getResponseCode()!=200){
                                    showToast("连接失败");
                                }
                                //请求成功
                                else {
                                    //用responseMessage存返回的字符串
                                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                    String line="";
                                    String reponseMessage="";
                                    while ((line=bufferedReader.readLine())!=null){
                                        reponseMessage+=line;
                                    }//reponseMessage存储登录返回的数据
                                    System.out.println("responselogin:"+reponseMessage);
                                    JSONObject jsonObject= new JSONObject(reponseMessage);
                                    //将responseMessage建立对象
                                    User user2=new User();
                                    user2.setId(jsonObject.getString("id"));
                                    System.out.println("useridlogin:"+jsonObject.getString("id"));
                                    user2.setName(jsonObject.getString("name"));
                                    user2.setPwd(jsonObject.getString("pwd"));
                                    user2.setEmail(jsonObject.getString("email"));
                                    user2.setType(jsonObject.getInt("type"));
                                    //返回对象id为空，即这个对象不存在
                                    if ("null".equals(user2.getId())){
                                        Looper.prepare();
                                        Toast.makeText(getApplicationContext(),"用户名或者密码错误，请重试",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                    //登录成功
                                    else {
                                        System.out.println("useridlogin2:"+user2.getId());

                                        Intent intent = new Intent();

                                        intent.putExtra("user",user2);

                                        intent.setClass(getApplicationContext(), RecommendActivity.class);

                                        startActivity(intent);
                                    }
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //return true;
                        }
                    }).start();
                    break;
                case R.id.btn_Forget:
                    new Thread() {
                        @Override
                        public void run() {
                            //获取用户输入
                            String user = mETUser.getText().toString().trim();
                            String pass = mETPass.getText().toString().trim();
                            DefaultHttpClient httpClient = new DefaultHttpClient();
                            try {
                                String path = "http://10.0.2.2:8080/webServletTest/login?userName="+ URLEncoder.encode(user,"utf-8")+"&password="+pass;
                                Log.d("过来没","1过来了");
                                HttpGet request = new HttpGet(path);
                                HttpResponse response = httpClient.execute(request);
                                Log.d("过来没","2过来了");
                                int statusCode = response.getStatusLine().getStatusCode();
                                if (statusCode == 200){
                                    InputStream inputStream = response.getEntity().getContent();
                                    String result = "HttpClientGet: "+Utils.getStringFromStream(inputStream);
                                    showToast(result);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                case R.id.btn_Register:
                    intent = new Intent();

                    intent.setClass(getApplicationContext(), RegisterActivity.class);
                    MainActivity.this.startActivity(intent);
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            String user = mETUser.getText().toString().trim();
//                            String pass = mETPass.getText().toString().trim();
//                            BasicNameValuePair pair1 = new BasicNameValuePair("userName",user);
//                            BasicNameValuePair pair2 = new BasicNameValuePair("password",pass);
//                            ArrayList<BasicNameValuePair> pairs = new ArrayList<>();
//                            pairs.add(pair1);
//                            pairs.add(pair2);
//                            try {
//                                UrlEncodedFormEntity entity = new UrlEncodedFormEntity (pairs,"utf-8");
//                                String path = "http://10.0.2.2:8080/webServletTest/login";
//                                HttpPost httpPost =new HttpPost(path);
//                                httpPost.setEntity(entity);
//                                DefaultHttpClient httpClient = new DefaultHttpClient();
//                                HttpResponse response = httpClient.execute(httpPost);
//                                int statusCode = response.getStatusLine().getStatusCode();
//                                if (statusCode == 200){
//                                    InputStream inputStream = response.getEntity().getContent();
//                                    String result = "HttpClientPost:"+Utils.getStringFromStream(inputStream);
//                                    showToast(result);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }.start();
                    break;
            }
        }
    }
    private void showToast(final String string){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTVReceive.setText(string);
                Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
