package com.soon.world;

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
        String modified = "{\"wfSv1\": \"종합 날씨 정보가 입력되어 있습니다.\"}";
        String res= "\n종합 날씨 정보가 입력되어 있습니다.\n" +
                " 테스트 데이터 입니다. \n" +
                " 이것 역시 \n";
        assertEquals(dataProcess.apiResponseProcess(msg),res);
    }

    @Test
    @DisplayName("landApiResponseProcess 테스트")
    void land_api_response_process_test() {

    }

}