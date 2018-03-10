package org.zlx.jmeter.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by @author linxin on 24/09/2017.  <br>
 */
public class TCPserver {



    private int port = 10009;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 20;

    public TCPserver() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
    }

    public void service(){
        while(true){
            Socket socket = null;
            try {

                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new TCPserver().service();
    }

}

class Handler implements Runnable {
    private Socket socket = null;
    private BufferedReader br = null;
    public Handler(Socket socket){
        this.socket = socket;
    }

    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void run(){
        try {
            System.out.println("New connection accepted " + socket.getInetAddress()+ ":" + socket.getPort());
            BufferedReader br = getReader(socket);

            String msg = null;
            while((msg=br.readLine()) != null){
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(br != null){
                    br.close();
                }
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
