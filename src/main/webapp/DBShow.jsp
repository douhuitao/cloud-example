<sql:query var="rs" dataSource="jdbc/mysql">
   select * from user;
   </sql:query>
    <html>   
    <head> 
         <title>DB Test</title>   </head>
    </body>    
    <h2>Results</h2>  
    <c:forEach var="row" items="${rs.rows}">
    	Foo ${row.Host}<br/>
    	<!--这里写你要显示的变量-->     
    	Bar ${row.User}<br/> 
    </c:forEach>     
    </body> 
</html>