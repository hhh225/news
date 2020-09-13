package com.example.knowledgeaggregation.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.knowledgeaggregation.JumpActivity;
import com.example.knowledgeaggregation.MainActivity;
import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.pojo.RegisterUser;
import com.example.knowledgeaggregation.pojo.User;
import com.example.knowledgeaggregation.webUtil.WebUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText name;
    Button register;
    WebUtil webUtil=new WebUtil();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showToast("here");
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_Pass);
        name=findViewById(R.id.et_name);
        register=findViewById(R.id.btn_Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String email1=email.getText().toString();    //得到邮箱
                        String password1=password.getText().toString(); //密码
                        String name1=name.getText().toString(); //用户名
                        User user=new User();    //新建用户
                        user.setEmail(email1);
                        user.setPwd(password1);
                        user.setName(name1);
                        Log.d("Regis", "register ");
                        User result=webUtil.registerProcess(user,"/userRegister");
                        if (result.getId()!=null){
                            showToast("注册成功");
                            Intent intent = new Intent();
                            intent.putExtra("user",result);
                            intent.setClass(getApplicationContext(), InteresActivity.class);
                            RegisterActivity.this.startActivity(intent);
                        }
                        else {
                            showToast("注册失败");
                        }
                    }
                }).start();
            }
        });
    }
    public void showToast(final String string){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
