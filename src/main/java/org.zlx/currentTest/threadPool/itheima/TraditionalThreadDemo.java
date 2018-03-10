package org.zlx.currentTest.threadPool.itheima;

public class TraditionalThreadDemo
{

	/**�����̵߳����ִ�ͳ��ʽ
	 * @param args
	 */
	public static void main(String[] args)
	{
		//1������Thread������࣬�������е�run����
		Thread thread1 = new Thread(){
			@Override
			public void run()
			{
				while (true)
				{
					System.out.println("Thread--1::"+Thread.currentThread().getName());
					try	//run�����е��쳣�����׳���ֻ��try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread1.start();

		//2������Thread�����ͬʱ����һ��ʵ��Runnable�ӿڵ�ʵ������
		Thread thread2 = new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					System.out.println("Thread--2::"+Thread.currentThread().getName());
					try	//run�����е��쳣�����׳���ֻ��try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		});
		thread2.start();
	}

}
