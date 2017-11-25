package cn.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * BaseDAO练习
 * Created by yanshaochen on 17-7-26.
 */
public class BaseDAO {

    //连接四大串
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url="mysql:jdbc://127.0.0.1:3306/t14";
    private static final String user="root";
    private static final String pwd="root";

    //jdbc三大资源
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    //建立连接
    private Connection getCon() throws Exception{
        Class.forName(driver);
        if(con==null||con.isClosed())
            con= DriverManager.getConnection(url,user,pwd);
        return con;
    }

    //关闭连接
    public void closeResources()throws Exception{
        if(rs!=null)
            rs.close();
        if(ps!=null)
            ps.close();
        if(con!=null)
            con.close();
    }

    //增删改
    public int executeUpdate (String sql,Object... objs)throws Exception{
        int count;
        getCon();
        ps=con.prepareStatement(sql);
        if(objs!=null){
            for (int i=0;i<objs.length;i++){
                ps.setObject(i+1,objs[i]);
            }
        }
        count = ps.executeUpdate();
        return count;
    }
    //查询
    public ResultSet executeQuery (String sql,Object... objs)throws Exception{
        getCon();
        ps=con.prepareStatement(sql);
        if(objs!=null){
            for (int i=0;i<objs.length;i++){
                ps.setObject(i+1,objs[i]);
            }
        }
        rs = ps.executeQuery();
        return rs;
    }
}
