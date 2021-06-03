package com.soon.world;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  기상청 API call -> 데이터 가공 -> Slack webhook Call
 *  Cron 에 등록 후 사용 예정
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String weather = "weather";
        String slack = "slack";
        //기상개황조회
        String api = "";
        //육상예보조회
        String land = "";
        //슬랙 webHook
        String slackHook = "";

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
