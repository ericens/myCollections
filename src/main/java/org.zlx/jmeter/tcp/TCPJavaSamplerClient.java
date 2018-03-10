package org.zlx.jmeter.tcp;

/**
 * Created by @author linxin on 24/09/2017.  <br>
 */

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * 继承自此接口后，AbstractJavaSamplerClient  ，则在选择request时候，就应该java request
 * api 文档  apache-jmeter-3.3/docs/api/index.html
 * examples 在源文件下面有
 */

public class TCPJavaSamplerClient extends AbstractJavaSamplerClient{

    private static String host = null;
    private static int port;

    private static String DEFAULT_HOST="localhost";
    private static int DEFAULT_PORT=10009;

    private PrintWriter out = null;
    private static ThreadLocal<PrintWriter> outHolder = new ThreadLocal<PrintWriter>();
    private static ThreadLocal<Socket> socketHolder= new ThreadLocal<Socket>() {


        protected Socket initialValue() {

            Socket socket = null;
            try {
                socket = new Socket();
                socket.setKeepAlive(true);
                socket.connect(new InetSocketAddress(host, port));
                if(socket.isConnected()){
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    outHolder.set(out);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return socket;

        }
    };

    @Override
    public void setupTest(JavaSamplerContext context) {
        host = context.getParameter("host");
        port = context.getIntParameter("port");
        socketHolder.get();
        out = outHolder.get();
        System.out.println("setupTest:" + Thread.currentThread().getId());
    }

    //设置默认参数，一定要要有，不然保存测试计划的时候，出现不能保存的情况
    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("host", String.valueOf(DEFAULT_HOST));
        params.addArgument("port", String.valueOf(DEFAULT_PORT));
        return params;
    }



    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        // TODO Auto-generated method stub
        SampleResult result = getSampleResult();
        result.sampleStart();

        out.write(Thread.currentThread().getId()+ ":Hello JavaSamplerClient!");
        out.flush();
        result.setResponseData((Thread.currentThread().getId() + ":success!").getBytes());
        result.sampleEnd();
        result.setSuccessful(true);
        return result;
    }


    public SampleResult getSampleResult()
    {
        SampleResult result = new SampleResult();
        result.setSampleLabel(getLabel());
        return result;
    }

    public String getLabel()
    {
        return "TCPSampler" + Thread.currentThread().getId();
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {

        Socket socket = socketHolder.get();
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("teardownTest:" + Thread.currentThread().getId());

    }

}

