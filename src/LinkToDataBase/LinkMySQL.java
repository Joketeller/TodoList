package LinkToDataBase;
//数据存储层
//存在的问题有：表名相同时应报错，记得开发。
import DataType.ListDetail;
import DataType.TableAttributes;

import java.sql.*;
import java.util.ArrayList;
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

    //获取connection，注意关闭
    private static Connection getConn(){
        Connection con=null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("尝试连接数据库...");
            con = DriverManager.getConnection(DataBaseURL,name,pwd);
            if (!con.isClosed()){
                System.out.println("连接数据库成功！");
            }
            else {
                //result=-1; //-1表示连接服务器失败，请确保您的网络环境
                System.out.println("连接数据库失败");
            }
        } catch (Exception e) {
          //  result=-1;
            e.printStackTrace();
        }
        return con;
    }

    //检查数据库链接
    public int CheckWeb(){
        conn=getConn();
        if (conn==null)
            return -1;
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //创建新表单
    public int CreateTable(String TableName) {   //返回值确定窗口类型，建立成功什么都不显示，如果失败会弹出Alert窗口
        int result=0; //result==0 表示正常
        try {
            conn=getConn();
            if (conn==null)
                return -1; //数据库连接出错
            String creatsql = "CREATE TABLE "+TableName+"("
                                + "id int(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,"
                                + "Urgency int(10) NOT NULL,"
                                + "Summary TINYTEXT NOT NULL,"
                                + "Detail TEXT NOT NULL"
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
                conn=null;
                stmt=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //获取所有表单名
    public List<TableAttributes> QueryTheTableName(){  //若返回值为null则数据库链接失败
        List<TableAttributes> Lists=new ArrayList<TableAttributes>();
        try {
            conn=getConn();
            if (conn==null)
                return null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("show tables");
            while (rs.next()){
                TableAttributes tmpdata=new TableAttributes();
                tmpdata.setName(rs.getString(1));
                Lists.add(tmpdata);
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
                conn=null;
                stmt=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Lists;
    }

    //获取详细List数据
    public List<ListDetail> QueryListInfo(String TableName){   //返回值为null说明出错
        List<ListDetail> Lists=new ArrayList<ListDetail>();
        try {
            conn=getConn();
            String sql = "select * from "+TableName;
            PreparedStatement pstmt;
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                ListDetail tmp=new ListDetail(rs.getInt("id"),rs.getString("Detail"),rs.getString("Summary"),rs.getInt("Urgency"));
                Lists.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (!conn.isClosed())
                    conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Lists;
    }

    //删除表单
    public int DropTable(String TableName){
        PreparedStatement pstmt = null;
        int result=0;
        try{
            conn=getConn();
            if (conn==null)
                return -1;
            String sql="DROP TABLE "+TableName;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();;
        }
        finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn=null;
            pstmt=null;
        }
        return result;
    }

    //插入List字段到表单中
    //To be continued
    public int InsertList(ListDetail Detail,String TableName){
        int result=0;
        PreparedStatement pstmt;
        String sql="INSERT";
        try{

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //重命名表名,可用
    public int RenameTable(String OldName,String NewName){
        String sql="rename table "+OldName+" to "+NewName+";";
        PreparedStatement pstmt=null;
        int result=0;
        try {
            conn = getConn();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            result = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}


