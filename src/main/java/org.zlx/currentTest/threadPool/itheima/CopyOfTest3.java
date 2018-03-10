package org.zlx.currentTest.threadPool.itheima;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOfTest3 extends Thread
{
	private TestDo33 testDo;
	private String key;
	private String value;
	public CopyOfTest3(String key, String key2, String value)
	{
		this.testDo = TestDo33.getInstance();
		/*������1���� ��1����ͬһ�������������д������Ҫ�á�1��+������
			��ʽ�����µĶ�����ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ��1������
			������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;
		this.value = value;
	}
	public static void main(String[] args) throws InterruptedException
	{
		CopyOfTest3 a = new CopyOfTest3("1", "", "1");
		CopyOfTest3 b = new CopyOfTest3("1", "", "2");
		CopyOfTest3 c = new CopyOfTest3("3", "", "3");
		CopyOfTest3 d = new CopyOfTest3("4", "", "4");
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

class TestDo33
{
	private TestDo33(){}
	private static TestDo33 _instance = new TestDo33();
	public static TestDo33 getInstance()
	{
		return _instance;
	}

	//�����д�������key��������
	//private List<Object> keys = new ArrayList<Object>();
	private CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<Object>();
	public void doSome(Object key, String value)
	{
		//�������key�������ù�һ�ξʹ浽������
		Object o = key;
		//�ж�������ù�û��
		if (!keys.contains(o))
		{
			//������keyû���ù��������������������浽��������
			keys.add(o);
		}
		else	//���������Ѿ��������key
		{
			//���key�Ѿ��������ˣ��Ͱ����ó������������������ͺ����ڵ�key������
			//��Ϊ��֪��ԭ��key��λ�ã�������Ҫ���б���
			for (Iterator<Object> it = keys.iterator(); it.hasNext();)
			{
				//��ǰ�������Ķ���
				Object oo = it.next();
				//����ҵ��ˣ�����������
				if (oo.equals(o))
				{
					o = oo;
					break;	//�ҵ��ˣ�������ѭ����
				}
			}
			//o = keys.get(keys.indexOf(o));	//key��o����ͬһ�������ò���
		}

		synchronized (o)
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
		}
	}
}

