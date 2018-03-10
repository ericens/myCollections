package org.zlx.com.taobao.rp.test;

import java.io.File;
import java.lang.reflect.Field;

public class FileTest {

	static class myThrea extends Thread{
		@Override
		public void run() {
			System.out.println("xx");
		}
	}
	public static void main(String s[]) {
		int count=0;
		while (true) {
			count++;
			 File file=new File("/Users/ericens/1.txt");
			 myThrea xThrea=new myThrea();
			 xThrea.start();
			 System.out.println(count+"  path  "+ file.getPath());
			 try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
