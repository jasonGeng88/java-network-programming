package com.jason.network.mode.httpasyncclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengjie on 2017/10/5.
 */
public class ReplayWithoutProblem {

    public List<String> loadMockRequest(int n){
        List<String> cache = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            cache.add("http://www.baidu.com?a="+i);
        }
        return cache;
    }

    public void start(List<String> cache) throws InterruptedException {

        HttpAsyncClient httpClient = new HttpAsyncClient();
        int i = 0;

        while (true){

            String url = cache.get(i%cache.size());
            final HttpGet request = new HttpGet(url);
            httpClient.execute(request, new FutureCallback<HttpResponse>() {
                public void completed(final HttpResponse response) {
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                }

                public void failed(final Exception ex) {
                    System.out.println(request.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    System.out.println(request.getRequestLine() + " cancelled");
                }

            });
            i++;
            Thread.sleep(100);
        }
    }

}
