package org.zlx.extendtions;

public class B {
	private  Object getOneMega() {
	      Object[] lst = new Object[10];
	      lst[0] = new long[256*128];
	      lst[1] = new int[256*256];
	      lst[2] = new double[256*128];
	      lst[3] = new float[64*256];
	      lst[4] = new byte[64*1024];
	      String[] l = new String[64*64];
	      for (int i=0; i<64*64; i++)
	      	 l[i] = new String("12345678"); // 16B
	      lst[5] = l;
	      lst[6] = new char[64*512];
	      return lst;
	   }

	public static void main(String [] ss) throws InterruptedException {
		Object x=new B().getOneMega();
		Thread.sleep(1000*60*60);


		// TODO Auto-generated constructor stub
	}

}
