package org.zlx.currentTest.threadPool.itheima;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test2
{
	public static void main(String[] args)
	{
		//����һ��һ��˼·��
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
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
						String output = queue.take();
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
				queue.put(TestDo.doSome(input));
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//String output = TestDo.doSome(input);
			//System.out.println(Thread.currentThread().getName()+":"+output);
		}
	}
}
//���ܸĶ���TestDo��
class TestDo
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
