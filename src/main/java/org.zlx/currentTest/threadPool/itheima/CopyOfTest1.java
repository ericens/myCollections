package org.zlx.currentTest.threadPool.itheima;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CopyOfTest1
{
	public static void main(String[] args)
	{
		//������Ź������־������Ҫ�߳�ͬ��
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i=0; i<4; i++)
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					while (true)
					{
						//����־�����ó���
						String log;
						try
						{
							log = queue.take();
							parseLog(log);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		//ģ�⴦��16����־������Ĵ������16����־����������16����ܴ�ӡ��
		//�޸ĳ�����룬��4���߳�����16����־��4���Ӵ���
		for (int i=0; i<16; i++)	//���д��벻�ܸĶ�
		{
			final String log = "" + (i+1);	//���д��벻�ܸĶ�
			{
				//�������ڲ������̣߳�ֻ�����ⲿ��������Ҫ����־������й���
				try
				{
					queue.put(log);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				//Test1.parseLog(log);
			}
		}
	}
	//parseLog�����ڲ����벻�ܸĶ�
	public static void parseLog(String log)
	{
		System.out.println(log+":"+(System.currentTimeMillis()/1000));
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
