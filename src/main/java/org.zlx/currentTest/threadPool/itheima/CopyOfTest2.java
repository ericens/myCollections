package org.zlx.currentTest.threadPool.itheima;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

//���ڶ��⻻�ַ�ʽ���
public class CopyOfTest2
{
	//ֻ����һ�����
	final Semaphore semaphore = new Semaphore(1);
	final static SynchronousQueue<String> queue = new SynchronousQueue<String>();
	public static void main(String[] args)
	{
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i=0; i<10; i++)
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					//�ȵõ��ڴ�ӡ
					try
					{
						/*
						String output = queue.take();
						*/
						String output = TestDo.doSome(queue.take());
						System.out.println(Thread.currentThread().getName()+":"+output);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}).start();
		}

		for (int i=0; i<10; i++)	//���в��ܸĶ�
		{
			String input = i+"";	//���в��ܸĶ�
			//�Ѵ��������ݷŽ�ȥ
			try
			{
				//�Ƚ����������ݴ�������ȡ�����ٴ���
				queue.put(input);
				//��������Ľ��������
				//queue.put(TestDo.doSome(input));
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//String output = TestDo.doSome(input);
			//System.out.println(Thread.currentThread().getName()+":"+output);
		}
	}
//���ܸĶ���TestDo��
	static class TestDo
	{
		public static String doSome(String input)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			String output = input + ":" + (System.currentTimeMillis()/1000);
			return output;
		}
	}
}
