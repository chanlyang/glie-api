package com.gaililie.glieapi.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {

    private static BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();


    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                blockingQueue.put("aaa");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("produce..");
        }
    }

    private static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                blockingQueue.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("consumer...");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        for (int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }
    }
}
