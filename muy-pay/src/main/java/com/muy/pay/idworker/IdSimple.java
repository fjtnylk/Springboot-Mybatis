package com.muy.pay.idworker;

/**
 * Created by yanglikai on 2017/1/17.
 */
public class IdSimple {
    private static long INFOID_FLAG = 1260000000000L;
    protected static int SERVER_ID = 1;

    public synchronized long nextId() throws Exception {
        if(SERVER_ID <= 0)
            throw new Exception("server id is error,please check config file!");
        long infoid = System.currentTimeMillis() - INFOID_FLAG;
        infoid=(infoid<<7)| SERVER_ID;
        Thread.sleep(1);
        return infoid;
    }
}
