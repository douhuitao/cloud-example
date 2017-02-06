<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%> 
<%@ page import="javax.sql.*"%> 
<%@ page import="javax.naming.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
<title>mysql连接池测试</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
   </head>
  
   <body> 
<%  
out.print("我的测试开始<br>"); 
DataSource ds = null; 
Connection conn=null;
       try{ 
InitialContext ctx=new InitialContext(); 
ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); 
conn = ds.getConnection();  

    }catch(Exception ex){
     ex.printStackTrace();
    } 
%>  
<%   
    if(conn!=null){ 
%> 
     <%= conn %>;  
<% 
    } 
%> 
   </body>
</html>