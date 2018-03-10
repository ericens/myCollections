package org.zlx.currentTest.threadPool.itheima;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeDataShare
{

	/**�̷߳�Χ�ڵ����ݹ���
	 * ����ģ����������ͬ�Ľ����и��Ե����ݹ�������
	 * ��ThreadScopeDataShare�ļ��еļ��ϻ�����ThreadLocal����
	 */
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	public static void main(String[] args)
	{
		//���������߳�
		for (int i=0; i<2; i++)
		{
			new Thread(
					new Runnable()
					{
						@Override
						public void run()
						{
							//�����ø��߳��е�����
							int data = new Random().nextInt();
							//�������
							threadLocal.set(data);
							System.out.println(Thread.currentThread().getName()+"������������Ϊ��"+data);
							new A().get();
							new B().get();
						}
					}).start();
		}
	}

	//����ģ��
	static class A
	{
		public void get()
		{
			int data = threadLocal.get();
			System.out.println(Thread.currentThread().getName()+"--A ģ�����ݣ�"+data);
		}
	}
	static class B
	{
		public void get()
		{
			int data = threadLocal.get();
			System.out.println(Thread.currentThread().getName()+"--B ģ�����ݣ�"+data);
		}
	}
}
