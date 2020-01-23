<html>
<script type="text/javascript">
	function myFunction(){
		var anchor = document.getElementById("a");
		const queryString = window.location.search;
		const urlParams = new URLSearchParams(queryString);
		const Username = urlParams.get("Username");
		const url = "uModify.jsp?Username="+Username;
	anchor.setAttribute("href",url);
	}
	function myFun(){
		var anchor = document.getElementById("b");
		const queryString = window.location.search;
		const urlParams = new URLSearchParams(queryString);
		const Username = urlParams.get("Username");
		const url = "http://localhost:8080/demorest/webresources/aliens/viewRecord/?Username="+Username;
		anchor.setAttribute("href",url);
	}
</script>
<body>
<h2>Welcome user!!  <b><%= request.getParameter("Username") %></b></h2>
<p><a href="#" id ="a" onclick="myFunction()">Modify your Records</a>
<p><a href="#" id = "b" onclick="myFun()">view your record</a>
</body>
</html>