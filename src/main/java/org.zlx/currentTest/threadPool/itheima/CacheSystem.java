package org.zlx.currentTest.threadPool.itheima;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CacheSystem
{
	/**
	 �����⣺���һ������ϵͳ
	 ����ϵͳ����Ҫȡ���ݣ�������ҵ�public Object getData(String key)������
	 ��Ҫ������ڲ���û��������ݣ�����о�ֱ�ӷ��أ����û�У��ʹ����ݿ��в����������
	 �鵽��������ݴ������ڲ��Ĵ洢���У��´���������Ҫ������ݣ��Ҿ�ֱ�ӷ��������
	 �����ٵ����ݿ������ˡ�		��Ҫȡ���ݲ�Ҫ�����ݿ⣬�����ҡ�
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//���Ի�����
		final CacheSystem cache = new CacheSystem();

		for (int i=0; i<3; i++)
		{
			new Thread(new Runnable()
			{
				public void run()
				{
					while (true)
					{
						int num = new Random().nextInt(10);
						String key = num + "";
						Object result = cache.get(key);
						System.out.println(Thread.currentThread().getName()+"���ڲ�ѯ��"+key);
						try
						{
							Thread.sleep(num*1000);
						} catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+"��ѯ���Ϊ��"+key+"="+result);
					}
				}
			}).start();
		}
	}
	//�ڲ��洢��
	private Map<String, Object> cache = new HashMap<String, Object>();

	//��ȡ���ݷ���
	//�����ж���߳���ȡ���ݣ�û�����ݵĻ��ֻ�ȥ���ݿ��ѯ����Ҫ����,��ͬ���ؼ��ּ���
	public Object get(String key)
	{
		//�ȴ��ڲ��洢���в���û��Ҫ������,�鵽ֱ�ӷ���
		Object value = cache.get(key);
		//���û�в鵽����ȥ���ݿ�����
		if (value==null)
		{
			//ʵ�ʴ����Ǵ����ݿ��л�ȡ   queryDB()
			value = key + "Value";
			//�����ݿ��л�ȡ���Ľ�������ڲ��洢����
			cache.put(key, value);
		}
		return value;
	}
}
/*
�����get������synchronized��ÿ��ֻ����һ���߳�����ѯ����ֻ��д��ʱ�����Ҫ���⣬�޸�����
��һ����д��
private ReadWriteLock rwl = new ReentrantReadWriteLock();
public Object get(String key)
{
	�϶���
	rwl.readLock().lock();
	�Ȳ�ѯ�ڲ��洢������û��Ҫ��ֵ
	Object value = cache.get(key);
	if (value==null)���û�У���ȥ���ݿ��в�ѯ�������鵽�Ľ�������ڲ��洢����
	{
		�ͷŶ���  ��д��
		rwl.readLock().unlock();
		rwl.writeLock().lock();
		if (value==null)�ٴν����жϣ���ֹ���д�̶߳�������ط��ظ�д
		{
			value = ��aaaa��;
			cache.put(key, value);
		}
		������� �ͷ�д�����ָ���д״̬
		rwl.readLock().lock();
		rwl.writeLock().unlock();
	}
	�ͷŶ���
	rwl.readLock().unlock();
	return value;
}ע�⣺try finally��unlock
*/
