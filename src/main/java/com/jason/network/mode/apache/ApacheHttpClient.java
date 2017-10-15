package com.jason.network.mode.apache;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;

/**
 * Created by jason-geng on 10/16/17.
 */
public class ApacheHttpClient {

    public static void main(String[] args) throws InterruptedException {
        ApacheHttpClient client = new ApacheHttpClient();

        for (;;) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1; i++) {
                client.execute();
            }
            long endTime = System.currentTimeMillis();
            long diffTime = endTime - startTime;
            if (diffTime < 1000){
                Thread.sleep(1000000-diffTime);
            }
        }

    }

    CloseableHttpAsyncClient client;

    public ApacheHttpClient() throws InterruptedException {
        IOReactorConfig config = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoLinger(0)
                .setTcpNoDelay(true)
                .setSoReuseAddress(true)
                .build();
        client = HttpAsyncClients.custom().setDefaultIOReactorConfig(config).build();
        client.start();
    }

    public void execute() throws InterruptedException {
        final HttpGet request = new HttpGet("http://www.sina.com/");
        client.execute(request, new FutureCallback<HttpResponse>() {
            public void completed(HttpResponse response) {
                System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
            }

            public void failed(Exception e) {
                System.out.println(request.getRequestLine() + "->" + e);
            }

            public void cancelled() {
                System.out.println("cancel");
            }
        });
    }

}
