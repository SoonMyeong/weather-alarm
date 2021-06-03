package com.soon.world;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.util.io.IOUtil;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("API Request 테스트")
class ApiRequestTest {
    private String apiUrl = "http://localhost:8080";
    private String message = "test message";
    private ApiRequest apiRequest;
    @Mock
    private HttpURLConnection connection;

    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        this.apiRequest = new ApiRequest();
    }

    @Test
    @DisplayName("weather type 일 경우")
    void weatherTypeTest() throws IOException {
        String type = "weather";
        this.inputStream = IOUtils.toInputStream("TEST","UTF-8");
        when(connection.getResponseCode()).thenReturn(200);
        when(connection.getInputStream()).thenReturn(inputStream);

        assertEquals(apiRequest.request(connection,"TEST",type),"TEST");

    }

}