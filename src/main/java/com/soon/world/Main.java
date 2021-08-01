package com.soon.world;

import com.sun.org.apache.bcel.internal.generic.LADD;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  기상청 API call -> 데이터 가공 -> Slack webhook Call
 *  Cron 에 등록 후 사용 예정
 */
public class Main {
    private static final String WEATHER = "weather";
    private static final String SLACK = "slack";
    private static final String WEATHER_API = "";
    private static final String WEATHER_LAND = "";
    private static final String SLACK_HOOK = "";

    public static void main(String[] args) throws IOException {
        String weather = WEATHER;
        String slack = SLACK;
        //기상개황조회
        String api = WEATHER_API;
        //육상예보조회
        String land = WEATHER_LAND;
        //슬랙 webHook
        String slackHook = SLACK_HOOK;

        //냠냠냠
        ApiRequest apiRequest = new ApiRequest();
        DataProcess dataProcess = new DataProcess();
        URL apiUrl = new URL(api);
        URL landApiUrl = new URL(land);
        URL slackHookUrl = new URL(slackHook);

        apiRequest.request((HttpURLConnection) slackHookUrl.openConnection()
                ,dataProcess.landApiResponseProcess(apiRequest.request((HttpURLConnection) landApiUrl.openConnection(),"",weather))
                ,slack
        );

        apiRequest.request((HttpURLConnection) slackHookUrl.openConnection()
                ,dataProcess.apiResponseProcess(apiRequest.request((HttpURLConnection) apiUrl.openConnection(),"",weather))
                ,slack
        );
    }
}
