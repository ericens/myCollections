package org.zlx.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by @author linxin on 11/10/2017.  <br>
 */
public class MybatisHandler implements InvocationHandler {

    /**
     * 原来传输的对象，现在只需要传输class
     */
    private Class classz;

    public MybatisHandler(Class target) {
        super();
        this.classz = target;
    }

    /**
     * 不行
     * @return
     */
    public Object getProxy() {

        return Proxy.newProxyInstance(Thread.currentThread()
                        .getContextClassLoader(), classz.getInterfaces(),
                this);
    }

    public <T> T getProxy(Class<T> mapperInterface) {

        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{mapperInterface};

//这样不行
//        return (T) Proxy.newProxyInstance(classLoader, mapperInterface.getInterfaces(),
//                this);

        return (T) Proxy.newProxyInstance(classLoader, interfaces,
                this);
    }


    /**
     * mybaits 自带方法
     * @param mapperInterface
     * @param <T>
     * @return
     */
    public static <T> T newMapperProxy(Class<T> mapperInterface) {
        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{mapperInterface};
        MybatisHandler proxy = new MybatisHandler(mapperInterface);
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("mybatis:"+method.getName());
        //      不再执行目标方法，而是调用其他逻辑
//        Object result = method.invoke(target, args);
        return "success";
    }
}
