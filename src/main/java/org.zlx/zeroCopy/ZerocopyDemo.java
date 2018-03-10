package org.zlx.zeroCopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by @author linxin on 14/01/2018.  <br>
 *     https://www.ibm.com/developerworks/linux/library/j-zerocopy/
 *     http://blog.csdn.net/flyingqr/article/details/6942645
 *     https://my.oschina.net/cloudcoder/blog/299944
 */
public class ZerocopyDemo {


    @SuppressWarnings("resource")
    public static void transferToDemo(String from, String to) throws IOException {
        FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();
        FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);

        fromChannel.close();
        toChannel.close();
    }

    @SuppressWarnings("resource")
    public static void transferFromDemo(String from, String to)
            throws IOException {
        FileChannel fromChannel = new FileInputStream(from).getChannel();
        FileChannel toChannel = new FileOutputStream(to).getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromChannel.close();
        toChannel.close();
    }


    @SuppressWarnings("resource")
    public static void commonFileCopy(String from, String to)
            throws IOException {
        FileInputStream fileInputStream = new FileInputStream(from);
        FileOutputStream fileOutputStream = new FileOutputStream(to);

        int position = 0;

        byte[] b=new byte[1024*8];

        while( (position=fileInputStream.read(b))>0){
            fileOutputStream.write(b,0,position);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }


    public static void main(String[] args) throws IOException {
        String from="/Users/ericens/tmp/products.png";
        String to="/Users/ericens/tmp/products2.png";
//        transferFromDemo(from,to);
        commonFileCopy(from,to);
    }
}
