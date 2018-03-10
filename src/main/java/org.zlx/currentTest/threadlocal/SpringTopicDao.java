package org.zlx.currentTest.threadlocal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by @author linxin on 06/10/2017.  <br>
 * 不同的线程在使用TopicDao时，先判断connThreadLocal.get()是否是null，
 *      如果是null，则说明当前线程还没有对应的Connection对象，
 *   这时创建一个Connection对象并添加到本地线程变量中；
 *      如果不为null，则说明当前的线程已经拥有了Connection对象，
 *   直接使用就可以了。这样，就保证了不同的线程使用线程相关的Connection，
 *   而不会使用其它线程的Connection。因此，这个TopicDao就可以做到singleton共享了。
 *
 *   当然，这个例子本身很粗糙，将Connection的ThreadLocal直接放在DAO只能做到本DAO的多个方法共享Connection时不发生线程安全问题，
 *   但无法和其它DAO共用同一个Connection，
 *   要做到同一事务多DAO共享同一Connection，
 *   必须在一个共同的外部类使用ThreadLocal保存Connection。
 *   但这个实例基本上说明了Spring对有状态类线程安全化的解决思路。
 */
public class SpringTopicDao {


    //①使用ThreadLocal保存Connection变量
    private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

    public static Connection getConnection() throws SQLException {

        //②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection,并将其保存到线程本地变量中。
        if (connThreadLocal.get() == null) {

            Connection conn=null;

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "username",
                    "password");

            connThreadLocal.set(conn);

            return conn;

        }else{

            return connThreadLocal.get();//③直接返回线程本地变量

        }

    }

    public void addTopic() {

        //④从ThreadLocal中获取线程对应的Connection
        try {
            Statement stat = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
