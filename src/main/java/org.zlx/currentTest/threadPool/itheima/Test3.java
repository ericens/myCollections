package org.zlx.currentTest.threadPool.itheima;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 extends Thread
{
	private TestDo3 testDo;
	private String key;
	private String value;
	public Test3(String key, String key2, String value)
	{
		this.testDo = TestDo3.getInstance();
		/*������1���� ��1����ͬһ�������������д������Ҫ�á�1��+������
			��ʽ�����µĶ�����ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ��1������
			������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;
		this.value = value;
	}
	public static void main(String[] args) throws InterruptedException
	{
		Test3 a = new Test3("1", "", "1");
		Test3 b = new Test3("1", "", "2");
		Test3 c = new Test3("3", "", "3");
		Test3 d = new Test3("4", "", "4");
		System.out.println("begin"+":"+System.currentTimeMillis()/1000);
		a.start();
		b.start();
		c.start();
		d.start();
	}
	public void run()
	{
		testDo.doSome(key, value);
	}
}

class TestDo3
{
	private TestDo3(){}
	private static TestDo3 _instance = new TestDo3();
	public static TestDo3 getInstance()
	{
		return _instance;
	}
	Lock lock = new ReentrantLock();
	public void doSome(Object key, String value)
	{
		if (key.equals("1"))
			lock.lock();
			//System.out.println("OKOKOOK");
		//synchronized ("1")
		try
		//�˴������ڵĴ�������Ҫ�ֲ�ͬ���Ĵ��룬���ܸĶ���
		{
			try
			{
				Thread.sleep(1000);
				System.out.println(key+":"+value+":"+System.currentTimeMillis()/1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}finally
		{
			if (key.equals("1"))
				lock.unlock();
		}
	}
}

