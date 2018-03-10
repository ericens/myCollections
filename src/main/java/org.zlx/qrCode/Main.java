package org.zlx.qrCode;

/**
 * Created by @author linxin on 20/10/2017.  <br>
 */

import net.glxn.qrgen.image.ImageType;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        //ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from("Hello World").to(ImageType.PNG).stream();
        ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from("http://www.cnblogs.com/xz-luckydog/p/6402568.html").to(ImageType.PNG).stream();

        try {
            FileOutputStream fout = new FileOutputStream(new File(
                    "/Users/ericens/QR_Code.JPG"));

            fout.write(out.toByteArray());

            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
