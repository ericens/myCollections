package org.zlx.interview;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by @author linxin on 15/10/2017.  <br>
 */
public class SimpleDateFormate {
    public void test() throws IOException {
        ByteArrayInputStream x=null;

        SequenceInputStream si;

        FileInputStream inputStream=new FileInputStream("fileName");
        inputStream.read(new byte[7]);
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("");
         simpleDateFormat.format(new Date());



        FileInputStream fin = new FileInputStream("employee.dat");
        BufferedInputStream bin = new BufferedInputStream(fin);
        DataInputStream din = new DataInputStream(bin);
        Double s = din.readDouble();

    }

}
