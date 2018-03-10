package org.zlx.currentTest.threadPool.itheima;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadLocalDemo
{

	/**�̷߳�Χ�ڵ����ݹ���
	 * ����ģ����������ͬ�Ľ����и��Ե����ݹ�������
	 *
	 * �ó�����ڵ����⣺
	 * 		���ݻ�ȡ��ͬ��
	 * 			Thread-1������������Ϊ��-997057737
				Thread-1--A ģ�����ݣ�-997057737
				Thread-0������������Ϊ��11858818
				Thread-0--A ģ�����ݣ�11858818
				Thread-0--B ģ�����ݣ�-997057737
				Thread-1--B ģ�����ݣ�-997057737
		��ý�Runnable���������ݵķ���Ҳд�ڶ�Ӧ��ģ���У����ȡ����ģ�黥��
	 * @param args
	 */
	private static int data = 0;
	//��Ҫ���̶߳�Ӧ�����ݱ�������
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
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
							//�����ø��߳��е�����  �߳��е�������Ҫ�������ã������޸���ȫ��ֵ
							int data = new Random().nextInt();
							//�������
							threadData.put(Thread.currentThread(), data);
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
			data = threadData.get(Thread.currentThread());
			System.out.println(Thread.currentThread().getName()+"--A ģ�����ݣ�"+data);
		}
	}
	static class B
	{
		public void get()
		{
			data = threadData.get(Thread.currentThread());
			System.out.println(Thread.currentThread().getName()+"--B ģ�����ݣ�"+data);
		}
	}
}
