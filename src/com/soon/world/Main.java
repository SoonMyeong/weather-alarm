package com.soon.world;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 *  기상청 API call -> 데이터 가공 -> Slack webhook Call
 *  Cron 에 등록 후 사용 예정
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String weather = "weather";
        String slack = "slack";
        //기상개황조회
        String apiUrl = "";
        //슬랙 webHook
        String slackHookUrl = "";

        //냠냠냠
        ApiRequest apiRequest = new ApiRequest();
        DataProcess dataProcess = new DataProcess();

        apiRequest.request(slackHookUrl
                ,dataProcess.apiResponseProcess(apiRequest.request(apiUrl,"",weather))
                ,slack
        );
    }
}
