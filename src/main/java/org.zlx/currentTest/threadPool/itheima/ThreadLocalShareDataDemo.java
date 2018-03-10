package org.zlx.currentTest.threadPool.itheima;

import java.util.Random;

public class ThreadLocalShareDataDemo
{

	/**06. ThreadLocal�༰Ӧ�ü���
	 * ���̷߳�Χ�ڹ������ݽ��з�װ����װ��һ���������������У��ṩ���û�ȡ����
	 * �����൥�������ṩ��ȡʵ������ķ�������ȡ����ʵ���������Ѿ���װ�õĵ�ǰ�̷߳�Χ�ڵĶ���
	 * @param args
	 */
	public static void main(String[] args)
	{
		for (int i=0; i<2; i++)
		{
			new Thread(
					new Runnable()
					{
						@Override
						public void run()
						{
							int data = new Random().nextInt(889);
							System.out.println(Thread.currentThread().getName()+"�������ݣ�"+data);
							MyData myData = MyData.getInstance();
							myData.setAge(data);
							myData.setName("Name:"+data);
							new A().get();
							new B().get();
						}
					}).start();
		}

	}

	static class A
	{
		//����ֱ��ʹ�û�ȡ�����̷߳�Χ�ڵĶ���ʵ��������Ӧ����
		String name = MyData.getInstance().getName();
		int age = MyData.getInstance().getAge();
		public void get()
		{
			System.out.println(Thread.currentThread().getName()+"-- AA name:"+name+"...age:"+age);
		}
	}

	static class B
	{
		//����ֱ��ʹ�û�ȡ�����̷߳�Χ�ڵĶ���ʵ��������Ӧ����
		String name = MyData.getInstance().getName();
		int age = MyData.getInstance().getAge();
		public void get()
		{
			System.out.println(Thread.currentThread().getName()+"-- BB name:"+name+"...age:"+age);
		}
	}

	static class MyData
	{
		private String name;
		private int age;
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public int getAge()
		{
			return age;
		}
		public void setAge(int age)
		{
			this.age = age;
		}
		//����
		private MyData() {};
		//�ṩ��ȡʵ������
		public static MyData getInstance()
		{
			//�ӵ�ǰ�̷߳�Χ�����ݼ��л�ȡʵ������
			MyData instance = threadLocal.get();
			if (instance==null)
			{
				instance = new MyData();
				threadLocal.set(instance);
			}
			return instance;
		}
		//��ʵ��������뵱ǰ�̷߳�Χ�����ݼ���
		static ThreadLocal<MyData> threadLocal = new ThreadLocal<MyData>();
	}

}
