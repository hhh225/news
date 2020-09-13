package com.example.knowledgeaggregation.search;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) {
        String hello=URLEncoder.encode("hello");
        try {
            System.out.println(URLEncoder.encode("hello","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(URLDecoder.decode(hello,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
