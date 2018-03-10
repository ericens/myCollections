package org.zlx.currentTest.threadPool.itheima;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo
{

	/**��д��ʹ��
	 * �����̶߳��������߳�д
	 */
	public static void main(String[] args)
	{
		//�������
		final Source source = new Source();
		//�����߳�
		for (int i=0; i<3; i++)
		{
			//��
			new Thread(new Runnable()
			{
				public void run()
				{
					while (true)
					{
						source.get();
					}
				}
			}).start();
			//д
			new Thread(new Runnable()
			{
				public void run()
				{
					while (true)
					{
						source.put(new Random().nextInt(999));
					}
				}
			}).start();
		}
	}

	static class Source
	{
		//��������
		private int data = 0;
		//Ҫ����ͬһ�����ϵĶ���д��
		ReadWriteLock rwl = new ReentrantReadWriteLock();

		//������
		public void get()
		{
			////�϶���
			rwl.readLock().lock();
			try
			{
				//��ȡ���ݲ����
				System.out.println("������"+Thread.currentThread().getName()+"���ڻ�ȡ���ݡ�����");
				try
				{
					Thread.sleep(new Random().nextInt(6)*1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println("������"+Thread.currentThread().getName()+"��ȡ�������ݣ�"+data);
			}finally
			{
				////����
				rwl.readLock().unlock();
			}
		}
		//д����
		public void put(int data)
		{
			////��д��
			rwl.writeLock().lock();
			try
			{
				//��ʾ��Ϣ
				System.out.println("д����"+Thread.currentThread().getName()+"���ڸ�д���ݡ�����");
				try
				{
					Thread.sleep(new Random().nextInt(6)*1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				this.data = data;
				System.out.println("д����"+Thread.currentThread().getName()+"�ѽ����ݸ�дΪ��"+data);
			}finally
			{
				////����
				rwl.writeLock().unlock();
			}
		}
	}
}
