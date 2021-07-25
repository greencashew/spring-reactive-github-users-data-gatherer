package com.greencashew.githubgatherer.gather.user;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class MockWebServerAbstractTest {
    protected static MockWebServer mockWebServer;

    protected static WebClient getWebClient() {
        return WebClient.create(getBaseUrl());
    }

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    protected static String getBaseUrl() {
        return String.format("http://localhost:%s", mockWebServer.getPort());
    }
}