package com.soon.world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class ApiRequest {

    private static final String WEATHER = "weather";
    private static final String SLACK = "slack";

    public String request(HttpURLConnection connection , String message, String type) throws IOException {
        if(type.equals(WEATHER)) {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
        }
        if(type.equals(SLACK)){
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String req = "payload={\"text\":\""+message+"\"}";
            System.out.println(req);

            OutputStream os = connection.getOutputStream();
            os.write(req.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        BufferedReader rd;
        if(connection.getResponseCode() >=200 && connection.getResponseCode() <= 300){
            rd = getBufferedReader(connection);
        }else{
            throw new IOException("connection Exception");
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

    public BufferedReader getBufferedReader(HttpURLConnection connection) throws IOException {
        return new BufferedReader(getInputStreamReader(connection));
    }

    public InputStreamReader getInputStreamReader(HttpURLConnection connection) throws IOException {
        return new InputStreamReader(connection.getInputStream());
    }
}
