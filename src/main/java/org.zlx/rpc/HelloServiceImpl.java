package org.zlx.rpc;

/**
 * Created by @author linxin on 13/10/2017.  <br>
 */
public class HelloServiceImpl implements HelloService {

    public String sayHi(String name) {
        return "Hi, " + name;
    }

}
