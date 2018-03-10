package org.zlx.currentTest.threadPool.itheima;

public class ThreadInterViewTest
{

	/**
	 * �տ���������û����֮ǰ��д
	 * ���߳�ѭ��10�Σ������߳�ѭ��100�Σ�
	 * �ٵ����߳�ѭ��10�Σ��ٻ����߳�ѭ��100��
	 * ���ѭ��50��
	 */
	public static void main(String[] args)
	{
		int num = 0;
		while (num++<50)
		{
			new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							circle("���߳�����", 10);
						}
					}).start();
			try
			{
				//������Ǳ�֤�ϱߵ����߳������У��տ�ʼû�ӣ����߳̾��ȿ���
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			circle("���߳�", 100);
		}
	}

	public static  void circle(String name, int count)
	{
		for (int i=1; i<=count; i++)
		{
			System.out.println(name+"::"+i);
		}
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
