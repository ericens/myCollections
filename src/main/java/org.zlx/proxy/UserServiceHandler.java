package org.zlx.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by @author linxin on 11/10/2017.  <br>
 */
public class UserServiceHandler implements InvocationHandler {

    private Object target;

    public UserServiceHandler(Object target) {
        super();
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread()
                        .getContextClassLoader(), target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("----- before -----");
        Object result = method.invoke(target, args);
        System.out.println("----- after -----");
        return result;
    }
}
