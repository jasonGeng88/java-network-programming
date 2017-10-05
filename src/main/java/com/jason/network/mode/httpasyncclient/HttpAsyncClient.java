package com.jason.network.mode.httpasyncclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;

/**
 * Created by gengjie on 2017/10/5.
 */
public class HttpAsyncClient {

    private CloseableHttpAsyncClient httpclient;

    public HttpAsyncClient() {
        httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
    }

    public void execute(HttpUriRequest request, FutureCallback<HttpResponse> callback){
        httpclient.execute(request, callback);
    }

    public void close() throws IOException {
        httpclient.close();
    }

}
