package org.zlx.rpc.server;

import java.io.IOException;

/**
 * Created by @author linxin on 13/10/2017.  <br>
 */
public interface Server {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
