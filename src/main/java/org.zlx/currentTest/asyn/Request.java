package org.zlx.currentTest.asyn;


public class Request {
    private RequestContent rc;//请求主体

    public Request(RequestContent rc) {
        this.rc = rc;
    }

    protected void start() {
        //开始请求
        final Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    rc.doSomeThing();
                    //响应请求
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                    rc.onFailure();
                    //如果执行失败
                }
                rc.onSuccess();
                //如果执行成功
            }
        });
        t.start();
    }
}
