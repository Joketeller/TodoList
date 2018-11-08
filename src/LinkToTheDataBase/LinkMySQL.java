package LinkToTheDataBase;

//存在的问题有：表名相同时应报错，记得开发。
import DataType.TableAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LinkMySQL {
    //数据库连接
    Connection conn = null;
    //数据库操作语句
    Statement stmt = null;
    //mysql用户名
    final static String name = "clown";   //本源码中含有数据库账号和密码，权限极大，请大家不要删我的库，也不要SQL注入
    //mysql密码
    final static String pwd = "123456";   //本源码中含有数据库账号和密码，权限极大，请大家不要删我的库，也不要SQL注入
    //指定JDBC驱动
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //mysql地址
    //指定连接数据库的url
    final static String DataBaseURL = "jdbc:mysql://106.15.191.0:3306/TodoListdatabase";
    //注意判断是否用相同表名，记得添加功能和alert窗口
    //Attention
    //Attention
    //Attention
    public int ConnectionToDatabase() {
        int result=0; //result==0 表示正常
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("尝试连接数据库...");
            conn = DriverManager.getConnection(DataBaseURL,name,pwd);
            if (!conn.isClosed()){
                System.out.println("连接数据库成功！");
            }
            else {
                result=-1; //-1表示连接服务器失败，请确保您的网络环境
                System.out.println("连接数据库失败");
            }
        } catch (Exception e) {
            result=-1;
            e.printStackTrace();
        } finally {
            try {
                if (!conn.isClosed())
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return result;
    }

    public int CreateTable(String Name) {   //返回值确定窗口类型，建立成功什么都不显示，如果失败会弹出Alert窗口
        int result=0; //result==0 表示正常
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("尝试连接数据库...");
            conn = DriverManager.getConnection(DataBaseURL,name,pwd);
            if (!conn.isClosed()){
                System.out.println("连接数据库成功！");
            }
            else {
                result=-1; //-1表示连接服务器失败，请确保您的网络环境
                System.out.println("连接数据库失败");
            }
            String creatsql = "CREATE TABLE "+Name+"("
                                + "Summary TINYTEXT not null,"
                                + "Details TEXT not null"
                                + ")charset=utf8;";
            System.out.println("正在创建表...");
            stmt = conn.createStatement();
            if(stmt.executeLargeUpdate(creatsql) == 0)
            {
                System.out.println("成功创建表！");
            }
            else
            {
                result=-2; //创建表出错，可能是服务器已经存在同名表或者服务器端出现错误
                System.out.println("创建表失败！");
            }
        } catch (Exception e) {
          //  System.out.println("创建表失败！");
            e.printStackTrace();
        } finally {
            try {
                if (!conn.isClosed())
                    conn.close();
                if (!stmt.isClosed())
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    //    public List<>
    public List<TableAttributes> QueryTheTable(){
        //int result=0; //result==0 表示正常
        List<TableAttributes> List=null;
        try {
             System.out.println("尝试连接数据库...");
             Class.forName(JDBC_DRIVER);
             conn = DriverManager.getConnection(DataBaseURL, name, pwd);
             if (!conn.isClosed()) {
                 System.out.println("连接数据库成功！");
             } else {
                // result = -1; //-1表示连接服务器失败，请确保您的网络环境
                 System.out.println("连接数据库失败");
            }
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("show tables; ");
            while (rs.next()){
                TableAttributes tmpdata=new TableAttributes();
                tmpdata.setName(rs.getString("ename"));
                List.add(tmpdata);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!conn.isClosed())
                    conn.close();
                if (!stmt.isClosed())
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

//        try {
//                System.out.println("尝试连接数据库...");
//                Class.forName(JDBC_DRIVER);
//                conn = DriverManager.getConnection(DataBaseURL, name, pwd);
//                if (!conn.isClosed()) {
//                System.out.println("连接数据库成功！");
//                } else {
//                // result = -1; //-1表示连接服务器失败，请确保您的网络环境
//                System.out.println("连接数据库失败");
//                }
//                } catch (Exception e) {
//                e.printStackTrace();
//                } finally {
//                try {
//                if (!conn.isClosed())
//                conn.close();
//                if (!stmt.isClosed())
//                stmt.close();
//                } catch (SQLException e) {
//                e.printStackTrace();
//                }
//                }