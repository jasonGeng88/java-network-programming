package com.jason.network.mode.httpasyncclient;

import java.util.List;

/**
 * Created by gengjie on 2017/10/5.
 */
public class ReplayApplication {

    public static void main(String[] args) throws InterruptedException {

//        problem
//        ReplayWithProblem replay1 = new ReplayWithProblem();
//        List<HttpUriRequest> cache1 = replay1.loadMockRequest(10000);
//        replay1.start(cache1);

//        no problem
        ReplayWithoutProblem replay2 = new ReplayWithoutProblem();
        List<String> cache2 = replay2.loadMockRequest(10000);
        replay2.start(cache2);

    }
}
