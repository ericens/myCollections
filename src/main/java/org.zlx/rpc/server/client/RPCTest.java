package org.zlx.rpc.server.client;

import org.zlx.rpc.HelloService;
import org.zlx.rpc.HelloServiceImpl;
import org.zlx.rpc.server.Server;
import org.zlx.rpc.server.ServiceCenter;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by @author linxin on 13/10/2017.  <br>
 */
public class RPCTest {

    public static void main(String[] args) throws IOException {

        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}
