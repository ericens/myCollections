package org.zlx.currentTest.threadPool.itheima;

public class TranditionalThreadSync {
    public static void main(String[] args) {

        final Outputer outputer = new Outputer();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("1111111111111");
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output2("222222222222222222222222");
                }
            }
        }).start();
    }
}

class Outputer {
    public void output(String name) {
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println();
    }

    public void output2(String name) {
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println();
    }
}
