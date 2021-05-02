package com.soon.world;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataProcess {

    public String apiResponseProcess(String msg){
        JSONObject jsonObject = new JSONObject(msg);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray item = items.getJSONArray("item");
        JSONObject getItem = item.getJSONObject(0);
        return makeLongMessage( getItem.getString("wfSv1"));
    }

    public String landApiResponseProcess(String msg){
        StringBuilder result = new StringBuilder();

        JSONObject jsonObject = new JSONObject(msg);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray item = items.getJSONArray("item");

        for(int i=1; i<=2; i ++){// 1과 2의 인덱스값이 내일 오전, 내일 오후의 데이터라서 해당 데이터만 추출
            JSONObject getItem = item.getJSONObject(i);
            result.append(makeMessage(getItem, i));
        }

        return result.toString();
    }

    public String makeLongMessage(String msg){
        StringBuilder result = new StringBuilder("\n");
        String [] splitMsg = msg.split("\n");
        for(int i=0; i<3; i++) // 종합 , 오늘, 내일 데이터만 꺼내기 위함
            result.append(splitMsg[i]).append("\n");

        return result.toString();
    }

    public String makeMessage(JSONObject getItem , int index){
        //강수 확률
        int rnSt = getItem.getInt("rnSt");
        //0 : 강수없음 , 1: 비, 2: 비/눈 , 3: 눈, 4: 소나기
        int rnYn = getItem.getInt("rnYn");
        //DB01: 맑음 , DB03: 구름많음 , DB04: 흐림
        String wfCd = getItem.getString("wfCd");
        //예상 온도
        String ta = getItem.getString("ta");
        //글자로 나오는 날씨
        String wf = getItem.getString("wf");
        String result = "";

        if(index == 1){
            result += "\n [내일 오전 날씨]"
                    + "\n 하늘 상태 : " + makeWfCd(wfCd)
                    + "\n 강수 확률 : " + rnSt
                    + "\n 강수 형태 : " + makeRnYn(rnYn)
                    + "\n 최저 온도 : " + ta
                    + "\n 요약 : " + wf
                    + "\n";

        }
        if(index == 2) {
            result += "\n [내일 오후 날씨]"
                    + "\n 하늘 상태 : " + makeWfCd(wfCd)
                    + "\n 강수 확률 : " + rnSt
                    + "\n 강수 형태 : " + makeRnYn(rnYn)
                    + "\n 최고 온도 : " + ta
                    + "\n 요약 : " + wf
                    + "\n";
        }

        return result;
    }

    public String makeWfCd(String wfCd){
        String str = "";
        if(wfCd.equals("DB01")){
            str = ":화창:";
        }
        if(wfCd.equals("DB03")) {
            str = ":구름:";
        }
        if(wfCd.equals("DB04")){
            str = ":안개:";
        }
        return str;
    }

    public String makeRnYn(int rnYn){
        String str = "";
        if(rnYn == 0){
            str = "강수없음";
        }
        if(rnYn == 1){
            str = ":비구름:";
        }
        if(rnYn == 2){
            str = ":비구름::눈과_구름:";
        }
        if(rnYn == 3){
            str = ":눈과_구름:";
        }
        if(rnYn == 4){
            str = "소나기";
        }
        return str;
    }

}
