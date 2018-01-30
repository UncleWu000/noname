package com.study;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakReferenceTest {

    private static final int  _1M = 1024*1024;

    public static void main(String[] args) {
        ReferenceQueue referenceQueue = new ReferenceQueue();
        Map<Object, Object> map = new HashMap<>();




        Thread thread2 = new Thread(() -> {
            for(int i =0; i<1000; i++){
                byte[] bytes = new byte[_1M];
                WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
                map.put(weakReference, new Object());
                System.out.println(map.size());
            }
            try {
                Thread.sleep(50000);
                System.out.println("main stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        Thread thread = new Thread(() -> {
            try {

                int cnt = 0;
                WeakReference<byte[]> k;
                while((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
                System.out.println("map.size = "+map.size());

            } catch(InterruptedException e) {
                //结束循环
                System.out.println("sotp -- map.size = "+map.size());

            }
        });
        thread2.start();
        thread.setDaemon(true);
        thread.start();


        System.out.println("end");

    }
}
class HelloImpl2 implements Hello{

    @Override
    public void sayHello(String str) {

    }
}
