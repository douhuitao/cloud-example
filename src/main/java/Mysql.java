import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;
import javax.naming.*;

public class Mysql {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Statement stm = null;

    // constructor 1
    public Mysql() throws Exception {
             
        conn=lookForConnection();
        if(conn!=null)
           stm = conn.createStatement();
        else
           System.out.println("get DB Connection Failure!");
    }

    // constructor 2
    public Mysql(String sql) throws Exception {
        conn=lookForConnection();  
         if(conn!=null)
         {   
             this.prepareStatement(sql);
             stm = conn.createStatement();
         }
         else
             System.out.println("get DB Connection Failure!");
    }
    
    private Connection lookForConnection()throws Exception
    {
        DataSource ds = null; 
         try{ 
             InitialContext ctx=new InitialContext(); 
             ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); 
             Connection con= ds.getConnection();  
             return con;
         }
        catch(Exception ex){
             ex.printStackTrace();
             return null;
        } 
    }
    public void prepareStatement(String sql) throws SQLException {
        ps = conn.prepareStatement(sql);
    }

    public void clearParameters() throws SQLException {
        ps.clearParameters();
    }

    // return the connection object
    public Connection getConnection() {
        return conn;
    }

    // return the preparedstatement
    public PreparedStatement getPreparedStatement() {
        return ps;
    }

    // return the set of query
    public ResultSet executeQuery(String sql) throws SQLException {
        if (stm != null) {
            return stm.executeQuery(sql);
        } else
            return null;
    }

    // return the set of query
    public ResultSet executeQuery() throws SQLException {
        if (ps != null) {
            return ps.executeQuery();
        } else
            return null;
    }

    // execute the operation of update
    public void executeUpdate(String sql) throws SQLException {
        if (stm != null)
            stm.executeUpdate(sql);
        else
           System.out.println("the Statement is null");
    }

    // execute the operation of update
    public void executeUpdate() throws SQLException {
        if (ps != null)
        {
          ps.executeUpdate();
        }
        else
            System.out.println("the prepareStatement is null");   
    }

    // close the connection and free resource
    public void closeCon() throws SQLException {
        if (stm != null) {
            stm.close();
            stm = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
        conn.close();
        conn = null;
    }

    // lock the table
    public boolean lockTable(String table, String privilege)throws SQLException {
        String locksql = "lock tables " + table + " " + privilege;
            if(this.executeQuery(locksql)!=null)
            {
                // System.out.println("lock");
                return true;
            }
            else
            return false;
    }

    // unlock the table
    public boolean unLockTable() throws SQLException{
        String unlocksql = "unlock tables ";
        if(this.executeQuery(unlocksql)!=null)
        {
            // System.out.println("unlock");
            return true;
        }
        else
            return false;
    }

    // set the parameter
    public void setString(int index, String value) throws SQLException {
        ps.setString(index, value);
    }

    public void setInt(int index, int value) throws SQLException {
        ps.setInt(index, value);
    }

    public void setBoolean(int index, boolean value) throws SQLException {
        ps.setBoolean(index, value);
    }

    public void setDate(int index, Date value) throws SQLException {
        ps.setDate(index, value);
    }

    public void setLong(int index, long value) throws SQLException {
        ps.setLong(index, value);
    }

    public void setFloat(int index, float value) throws SQLException {
        ps.setFloat(index, value);
    }
    public void setBinaryStream(int index,InputStream in,int length) throws SQLException {
        ps.setBinaryStream(index,in,length);
        }
}