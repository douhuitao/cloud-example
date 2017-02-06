

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.sql.*;

/**
 * Servlet implementation class AppMine
 */
@WebServlet("/AppMine")
public class AppMine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AppMine() {
        // TODO Auto-generated constructor stub
    }
    
    //初始化servlet，类似于构造函数  
    //只第一次访问survlet时被调用  
    public void init(ServletConfig parm1) throws ServletException {  
    	// TODO: 在这添加你的代码  
    	System.out.println("init ");  
    }  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
       
     //得到servlet配置文件，不太重要  
    public ServletConfig getServletConfig() {  
        // TODO: 在这添加你的代码  
        return null;  
    }  
  
    /** 
     * Method service 
     * 
     * 
     * @param parm1 
     * @param parm2 
     * 
     @throws ServletException 
     @throws IOException 
     * 
     */  
       
     //用于处理业务逻辑  
    public void service(ServletRequest parm1, ServletResponse res) throws ServletException, IOException {  
        // TODO: 在这添加你的代码  
        System.out.println("service it");  
        PrintWriter pw = res.getWriter();     
        pw.println("Hello World!!!");
        
   /*     String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/mysql"; // 连接到 school 数据库 
        String userid="root"; // 用户 
        String passwd="123456"; // 密码
*/        
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://192.168.1.168:30097/f753c6130b6e4"; // 连接到 school 数据库 
        String userid="98e2cc3dbb954"; // 用户 
        String passwd="06c2a88f9f104"; // 密码
        
        try{
        	Class.forName(driver);
        }
        catch(Exception e){
        	pw.println("can not "+driver+" connect !");
        	e.printStackTrace();
        	};
        try {
        	Connection con= DriverManager.getConnection(url,userid,passwd);
        	if(!con.isClosed())
        		pw.println(" connect success !");
        		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
        		String sql = "select * from test";  
        		ResultSet rs = stmt.executeQuery(sql);  
        		System.out.println(rs.getRow());  //rs.getRow()获得结果集的当前行号（1、2、3等），初始为0            
        		while(rs.next()){                  
        			String aName = rs.getString(1); //取得所在行第三列的String值  
        			pw.println("name="+aName);  
        		}
        		/* sql = "update testnum set name = 'aaa' where test=2";  
        		 //sql = "select * from mysql.user";  
        		 int result = stmt.executeUpdate(sql);  
        		 if(result != -1){  
        			 System.out.println("connect ok");  
        		 }*/
        		 con.close();
        	}
        catch(SQLException SQLe){
        	pw.println(" can not link it!");
        };
    }  
  
    /** 
     * Method getServletInfo 
     * 
     * 
     * @return 
     * 
     */  
    public String getServletInfo() {  
        // TODO: 在这添加你的代码  
        return "";  
    }  
  
    /** 
     * Method destroy 
     * 
     * 
     */  
    public void destroy() {  
        // TODO: 在这添加你的代码  
        System.out.println("destroy!");  
    }  
      
} 