<html>
<script>
function myFunction(){
	console.log("function is invoked");
	var action = document.getElementById("form");
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const Username = urlParams.get("Username");
	const url = "webresources/aliens/umodify?Username="+Username;
	action.setAttribute("action",url);
	}
</script>
<body>
<form action="#" id="form" method="POST" name="form1">
		<h1>Enter the details you want to modify</h1>
		<br/><br/>
		<label>CustomerName</label> <input maxlength="15" name="CustomerName" onclick="myFunction()"/> 
		<br/><br/>
		<label>Place</label> <input maxlength="15" name="Place"/> 
		<br/><br/>
		<label>PhoneNo</label> <input maxlength="15" name="PhoneNo"/> 
		<br/><br/>

		<input type="submit" value="Submit" />
		<input type="reset" value="Reset" />
	</form>
	<% String uname = request.getParameter("Username");out.println(uname); %>
</html>