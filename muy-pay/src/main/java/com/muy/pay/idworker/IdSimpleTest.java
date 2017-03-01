package com.muy.pay.idworker;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yanglikai on 2017/1/17.
 */
public class IdSimpleTest {
    public static void main(String[] args) throws Exception {
        Set<Long> values = new HashSet<Long>();

        IdSimple idSimple = new IdSimple();
        for (int i = 0; i < 100; i++) {
            long value = idSimple.nextId();
            if (values.contains(value)) {
                System.out.println("yanglikai");
            }
            values.add(value);
//            System.out.println(value);
        }
    }
}
