package org.zlx.currentTest.asyn;

import org.junit.Test;

public class test {

    @Test
    public void start(){

        new Request(new RequestContent() {
            void doSomeThing () {
                System.out.println("doSomething");
            }

            void onSuccess() {
                System.out.println("override onSuccess");
            }
        }).start();
    }

}
