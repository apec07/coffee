<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>	
<%@ page import="java.util.*"%>
<%@ page import="idv.cm.db.CoffeeVO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Coffee</title>
</head>
<body>
	<form action="CoffeeServlet" method="post">

		<table border=1>
			<thead>
			<tr>
				<td colspan=5>
					<p align=center>
						Coffee</p>
				</td>
			</tr>
			<tr>
			<td colspan=5 align="center">
			<input type="submit" name="action" value="search">
			<input type="submit" name="action" value="edit">
			<input type="submit" name="action" value="reset">
			</td>
		
			</tr>
				<tr style="background-color:grey">
				<th>COF_NAME</th>
				<th>SUP_ID</th>
				<th>PRICE</th>
				<th>SALES</th>
				<th>TOTAL</th>
				</tr>
			</thead>
			<tbody>
			<%
				String msg = null;
				int count =0;
				HashSet<CoffeeVO> set = (HashSet<CoffeeVO>)session.getAttribute("refresh");
				count = (int)session.getAttribute("update");
				if(set!=null){
					msg="已取得資料!";
				}else if(count!=0){
					msg="已更新 "+count+" 筆資料";	
				}else{
					msg="還沒實作喔!";
				}
				%>
			<tr><td colspan=5 align="center"><b><%=msg%></b></td></tr>
				<%
				if(set==null&&count==0)return;
				Iterator<CoffeeVO> it = set.iterator();
				while(it.hasNext()){
					CoffeeVO coffee = it.next();
					
					String cof_name = coffee.getCof_name();
					int sup_id = coffee.getSup_id();
					float price = coffee.getPrice();
					int sales = coffee.getSales();
					int total = coffee.getTotal();
			%>
			
			<tr>	
			<td><%=cof_name %></td>
			<td><%=sup_id %></td>
			<td><%=price %></td>
			<td><%=sales %></td>
			<td><%=total %></td>
			</tr>
			<% }%>
			</tbody>
		</table>
	</form>


</body>
</html>