<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="idv.cm.db.CoffeeVO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Coffee</title>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>

</head>
<body>
<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span>
			<h2>New Coffee</h2>
			<form action="CoffeeServlet" method="post">
			<input type="text" placeholder="coffee name" name="new-cof_name"/>
			<input type="number" placeholder="supplier id" name="new-cof_sup"/>
			<input type="number" placeholder="coffee price" name="new-cof_price"/>
			<p>
			<input type="submit" name="action" value="insert"/>
			<input type="button" id="btn_cancel" value="exit"/>
			</p>
			</form>
		</div>

	</div>
	<form action="CoffeeServlet" method="post">

		<table class="table" border=1>
			<thead>
				<tr>
					<td colspan=5>
						<p align=center>Coffee</p>
					</td>
				</tr>
				<tr>
					<td colspan=5 align="center">
					<input type="submit" name="action" value="search"> 
					<input type="button" value="new dialog" id="newDialog"> 
					<input type="button" value="EditForm" id="editForm">
					<input type="submit" name="action" value="edit" id="edit-form"> 
					<input type="submit" name="action" value="reset"></td>

				</tr>
				<tr style="background-color: grey">
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
				int upcount = 0;
				int newcount=0;
				HashSet<CoffeeVO> set = (HashSet<CoffeeVO>) session.getAttribute("refresh");
				if (null != session.getAttribute("update")) {
					upcount = (Integer) session.getAttribute("update");
				}else if(null != session.getAttribute("new"))
					newcount= (Integer) session.getAttribute("new");
				Enumeration<String> xx = session.getAttributeNames();
				while (xx.hasMoreElements()) {
					out.println(xx.nextElement());
				}
				if (set != null) {
					msg = "已取得資料!";
				} else if (upcount != 0) {
					msg = "已更新 " + upcount + " 筆資料";
				} else if (newcount!=0 ){
					msg = "新增 " + newcount + " 筆資料";
				} else {
					msg = "還沒實作喔!";
				}
				%>
				<tr>
					<td colspan=5 align="center"><b><%=msg%></b></td>
				</tr>
				<%
					if (set == null)
					return;
				Iterator<CoffeeVO> it = set.iterator();
				while (it.hasNext()) {
					CoffeeVO coffee = it.next();

					String cof_name = coffee.getCof_name();
					int sup_id = coffee.getSup_id();
					float price = coffee.getPrice();
					int sales = coffee.getSales();
					int total = coffee.getTotal();
				%>

				<tr>
					<td><%=cof_name%></td>
					<td><%=sup_id%></td>
					<td><%=price%></td>
					<td><%=sales%></td>
					<td><%=total%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</form>


	

	<script>
		//edit form
		var editForm = document.getElementById("editForm");
		const table = document.querySelector(".table");
		table.onclick = function(item) {
			console.log('editForm click');
			var row = item.path[1];
			for(var i=0;i<row.cells.length;i++){
				console.log(row.cells[i]);	
			}
			
			
			
		}
	
	    // https://www.w3schools.com/howto/howto_css_modals.asp
		// Get the modal
		var modal = document.getElementById("myModal");

		// Get the button that opens the modal
		var btn = document.getElementById("newDialog");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		var btn_close = document.getElementById("btn_cancel");
		span.addEventListener('click',closeDialog);
		btn_close.addEventListener('click',closeDialog);
		console.log(span.textContent);
		console.log(btn_close.textContent);
		
		// When the user clicks the button, open the modal 
		btn.onclick = function() {
			modal.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		function closeDialog() {
			console.log('close dialog...');
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
</body>
</html>