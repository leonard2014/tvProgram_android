package com.leonard.www.tvprog.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by Leonard Wu on 20/2/17.
 */
@Config(sdk = 23)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseNetworkTest extends BaseTest {
    protected MockWebServer mockWebServer;
    protected String serverUrl;

    protected void enqueueResponseBody(String body, int StatusCode) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(StatusCode)
                .setBody(body));
    }

    @Before
    public void setup() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        serverUrl = "http://127.0.0.1:" + mockWebServer.getPort() + "/";
    }

    public void setupResponse(String resBody) throws Exception {
        enqueueResponseBody(resBody, HttpURLConnection.HTTP_OK);

        //wait for network
        Thread.sleep(WAITING);
    }

    @After
    public void teardown() throws Exception {
        mockWebServer.shutdown();
    }

}
