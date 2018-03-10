package org.zlx.currentTest.secondKill;

/**
 * Created by @author linxin on 08/10/2017.  <br>
 */

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    private static ThreadLocal<SqlSession> local=new ThreadLocal<SqlSession>();

    private static SqlSessionFactory factory;
    public static SqlSessionFactory getFactory(){

        if(factory==null){
            synchronized (MybatisUtils.class){
                String resource="mybatis.xml";
                //加载mybatis 的配置文件（它也加载关联的映射文件）
                InputStream is=MybatisUtils.class.getClassLoader().getResourceAsStream(resource);

                //构建sqlSession 的工厂
                factory=new SqlSessionFactoryBuilder().build(is);

                System.out.println("SqlSessionFactory building.....");
            }
        }
        return factory;
    }


    /**
     * 通过threadlocal, 确保每个线程都是独立的session
     * @return
     */
    public static SqlSession getSession(){
        if(local.get()==null){
            SqlSessionFactory factory=MybatisUtils.getFactory();
            SqlSession session=factory.openSession(true);
            local.set(session);
        }
        return local.get();
    }

    /**
     * 使用了session后，最后需要还回去，
     * 1. closes ssession
     * 2. 清除threadlocal
     */
    public static void close(){
        if(local.get()!=null){
            local.get().close();
            local.remove();
        }
    }




}
