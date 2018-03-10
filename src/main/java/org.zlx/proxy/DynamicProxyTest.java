package org.zlx.proxy;

/**
 * Created by @author linxin on 11/10/2017.  <br>
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        userServiceTest();
        mybatis();
    }

    public static  void userServiceTest(){
        UserService userService = new UserServiceImpl();
        UserServiceHandler invocationHandler = new UserServiceHandler(
                userService);

        UserService proxy = (UserService) invocationHandler.getProxy();
        proxy.add();
    }

    public static void mybatis() {

        MybatisService service=MybatisHandler.newMapperProxy(MybatisService.class);
        service.update();
        service.insert();



        MybatisHandler invocationHandler = new MybatisHandler(
                MybatisService.class);

        MybatisService dao = invocationHandler.getProxy(MybatisService.class);
        dao.insert();
        dao.update();
    }
}
