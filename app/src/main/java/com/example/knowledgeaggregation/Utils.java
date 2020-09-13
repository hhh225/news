package com.example.knowledgeaggregation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Utils {
    public static String getStringFromStream(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte [] buffer = new byte[1024];
        try {
            while((len = inputStream.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            inputStream.close();
            byte [] bytes = baos.toByteArray();

            return new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
