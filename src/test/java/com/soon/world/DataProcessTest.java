package com.soon.world;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DataProcess 테스트")
class DataProcessTest {
    private DataProcess dataProcess;

    @BeforeEach
    void setUp() { this.dataProcess = new DataProcess();}

    @Test
    @DisplayName("apiResponseProcess 테스트")
    void api_response_process_test() {
        String msg = "{\"response\" : { "
                            + "\"body\" : {"
                                +"\"items\" : {"
                                    +"\"item\" : [ {"
                                        +"\"wfSv1\" : \"종합 날씨 정보가 입력되어 있습니다.\\n "
                                            +"테스트 데이터 입니다. \\n 이것 역시 \\n 테스트 입니다.\""
                                            +"} ]"
                                        +"}"
                                    +"}"
                                +"}"
                        +"}";
        String res= "\n종합 날씨 정보가 입력되어 있습니다.\n" +
                " 테스트 데이터 입니다. \n" +
                " 이것 역시 \n";
        assertEquals(dataProcess.apiResponseProcess(msg),res);
    }

    @Test
    @DisplayName("landApiResponseProcess 테스트")
    void land_api_response_process_test() {
        String msg = "{\n" +
                "    \"response\": {\n" +
                "        \"body\": {\n" +
                "            \"dataType\": \"JSON\",\n" +
                "            \"items\": {\n" +
                "                \"item\": [\n" +
                "                    {\n" +
                "                        \"announceTime\": 202106071700,\n" +
                "                        \"numEf\": 0,\n" +
                "                        \"regId\": \"11B10101\",\n" +
                "                        \"rnSt\": 30,\n" +
                "                        \"rnYn\": 0,\n" +
                "                        \"ta\": \"\",\n" +
                "                        \"wd1\": \"W\",\n" +
                "                        \"wd2\": \"NW\",\n" +
                "                        \"wdTnd\": \"1\",\n" +
                "                        \"wf\": \"흐림\",\n" +
                "                        \"wfCd\": \"DB04\",\n" +
                "                        \"wsIt\": \"\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"announceTime\": 202106071700,\n" +
                "                        \"numEf\": 1,\n" +
                "                        \"regId\": \"11B10101\",\n" +
                "                        \"rnSt\": 60,\n" +
                "                        \"rnYn\": 1,\n" +
                "                        \"ta\": \"19\",\n" +
                "                        \"wd1\": \"SE\",\n" +
                "                        \"wd2\": \"S\",\n" +
                "                        \"wdTnd\": \"1\",\n" +
                "                        \"wf\": \"흐리고 가끔 비\",\n" +
                "                        \"wfCd\": \"DB04\",\n" +
                "                        \"wsIt\": \"\"\n" +
                "                    },\n" +
                "{\n" +
                "                        \"announceTime\": 202106071700,\n" +
                "                        \"numEf\": 2,\n" +
                "                        \"regId\": \"11B10101\",\n" +
                "                        \"rnSt\": 30,\n" +
                "                        \"rnYn\": 0,\n" +
                "                        \"ta\": \"28\",\n" +
                "                        \"wd1\": \"SW\",\n" +
                "                        \"wd2\": \"W\",\n" +
                "                        \"wdTnd\": \"1\",\n" +
                "                        \"wf\": \"구름많음\",\n" +
                "                        \"wfCd\": \"DB03\",\n" +
                "                        \"wsIt\": \"\"\n" +
                "                    },"+
                "]\n" +
                "            },\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String res = "\n" +
                " [내일 오전 날씨]\n" +
                " 하늘 상태 : :안개:\n" +
                " 강수 확률 : 60\n" +
                " 강수 형태 : :비구름:\n" +
                " 최저 온도 : 19\n" +
                " 요약 : 흐리고 가끔 비\n" +
                "\n" +
                " [내일 오후 날씨]\n" +
                " 하늘 상태 : :구름:\n" +
                " 강수 확률 : 30\n" +
                " 강수 형태 : 강수없음\n" +
                " 최고 온도 : 28\n" +
                " 요약 : 구름많음\n";
        assertEquals(dataProcess.landApiResponseProcess(msg),res);
    }

    @Test
    @DisplayName("makeLongMessage 테스트")
    void make_long_message_test() {
        String req = "새로운\n테스트\n데이터\n입니다.\n룰룰룰";
        assertEquals(dataProcess.makeLongMessage(req),"\n새로운\n테스트\n데이터\n");
    }

    @Test
    @DisplayName("makeMessage 인덱스 1 테스트")
    void make_message_test_index_1() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("rnSt",50);
        jsonObject.put("rnYn",0);
        jsonObject.put("wfCd","DB01");
        jsonObject.put("ta","29도");
        jsonObject.put("wf","맑음");
        
        String res = "\n [내일 오전 날씨]"
                + "\n 하늘 상태 : " + ":화창:"
                + "\n 강수 확률 : " + "50"
                + "\n 강수 형태 : " + "강수없음"
                + "\n 최저 온도 : " + "29도"
                + "\n 요약 : " + "맑음"
                + "\n";
        assertEquals(dataProcess.makeMessage(jsonObject,1),res);
    }

    @Test
    @DisplayName("makeMessage 인덱스 2 테스트")
    void make_message_test_index_2() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("rnSt",50);
        jsonObject.put("rnYn",0);
        jsonObject.put("wfCd","DB01");
        jsonObject.put("ta","29도");
        jsonObject.put("wf","맑음");

        String res = "\n [내일 오후 날씨]"
                + "\n 하늘 상태 : " + ":화창:"
                + "\n 강수 확률 : " + "50"
                + "\n 강수 형태 : " + "강수없음"
                + "\n 최고 온도 : " + "29도"
                + "\n 요약 : " + "맑음"
                + "\n";
        assertEquals(dataProcess.makeMessage(jsonObject,2),res);
    }

    @Test
    @DisplayName("makeWfCd DB01 테스트")
    void make_wd_cd_test_DB01() {
        String wfCd = "DB01";
        assertEquals(dataProcess.makeWfCd(wfCd),":화창:");
    }
    @Test
    @DisplayName("makeWfCd DB03 테스트")
    void make_wd_cd_test_DB03() {
        String wfCd = "DB03";
        assertEquals(dataProcess.makeWfCd(wfCd),":구름:");
    }
    @Test
    @DisplayName("makeWfCd DB04 테스트")
    void make_wd_cd_test_DB04() {
        String wfCd = "DB04";
        assertEquals(dataProcess.makeWfCd(wfCd),":안개:");
    }

    @Test
    @DisplayName("makeRnYn 0 테스트")
    void make_rn_yn_test_0() {
        int rnYn = 0;
        assertEquals(dataProcess.makeRnYn(rnYn),"강수없음");
    }
    @Test
    @DisplayName("makeRnYn 1 테스트")
    void make_rn_yn_test_1() {
        int rnYn = 1;
        assertEquals(dataProcess.makeRnYn(rnYn),":비구름:");
    }
    @Test
    @DisplayName("makeRnYn 2 테스트")
    void make_rn_yn_test_2() {
        int rnYn = 2;
        assertEquals(dataProcess.makeRnYn(rnYn),":비구름::눈과_구름:");
    }
    @Test
    @DisplayName("makeRnYn 3 테스트")
    void make_rn_yn_test_3() {
        int rnYn = 3;
        assertEquals(dataProcess.makeRnYn(rnYn),":눈과_구름:");
    }
    @Test
    @DisplayName("makeRnYn 4 테스트")
    void make_rn_yn_test_4() {
        int rnYn = 4;
        assertEquals(dataProcess.makeRnYn(rnYn),"소나기");
    }


}