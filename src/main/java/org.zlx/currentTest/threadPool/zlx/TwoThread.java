package org.zlx.currentTest.threadPool.zlx;

public class TwoThread {

	String mutex="";
	String mutex2="";
	public static void main(String args[]) {
		TwoThread thread=new TwoThread();
		thread.init();

	}

	public void init(){
		//�������߳�
		new Thread(){
			@Override
			public void run() {
				// һֱѭ��
				while(true){
					//֪����ȡ����Ȼ�����С�Ȼ��֪ͨ
					synchronized (mutex){
						for (int i = 0; i < 4; i++) {
							System.out.println("thread son  :"+i);
						}
						mutex.notify();
						try {
							mutex.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		}.start();

		// һֱѭ��
		while(true){
			//֪����ȡ����Ȼ�����С�Ȼ��֪ͨ
			synchronized (mutex){
				for (int i = 0; i < 2; i++) {
					System.out.println("thread main  :"+i);
				}
				mutex.notify();
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


}
