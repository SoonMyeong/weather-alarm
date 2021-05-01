package com.soon.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiRequest {

    public String request(String apiUrl , String message, String type) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if(type.equals("weather")) {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");

        }
        if(type.equals("slack")){
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String req = "payload={\"text\":\""+message+"\"}";

            OutputStream os = connection.getOutputStream();
            os.write(req.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        BufferedReader rd;
        if(connection.getResponseCode() >=200 && connection.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else{
            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while((line=rd.readLine())!=null){
            sb.append(line);
        }
        rd.close();
        connection.disconnect();

        return sb.toString();
    }
}
