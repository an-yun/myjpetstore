<%@ include file="../common/IncludeTop.jsp"%>
<script type="text/javascript">
	var xmlHttpRequest = new XMLHttpRequest();
	function usernameIsExist()
	{
		var username = document.newAccount.username.value;
		sendRequset("usernameIsExist?username="+username);
	}
	function sendRequset(url)
	{
		xmlHttpRequest.open("GET",url,true);
		xmlHttpRequest.onreadystatechange = processResponse;
		xmlHttpRequest.send(null);
	}
	function processResponse()
	{
		if(xmlHttpRequest.readyState==4){
			if(xmlHttpRequest.status== 200)
			{
				var responseInfo = xmlHttpRequest.responseXML.getElementsByTagName("msg")[0].firstChild.data;
				var div1= document.getElementById("usernameMsg");
				if(responseInfo == "Exist")
				{
					div1.innerHTML="<font color = 'red'><s:text name="usernameError"/></font>";
				}
				else{
					div1.innerHTML="<font color = 'green'><s:text name="usernameSuccess"/></font>";
				}
			}
		}
	}
</script>

<div id="Catalog">
	<a href="newAccountForm?request_locale=zh_CN" ><s:text name="chinese"/></a> |
	<a href="newAccountForm?request_locale=en_US"><s:text name="english"/></a><br>
	<form action="newAccout" method="post" name="newAccount">
		<h3><s:text name="userInformation"/></h3>
		<table>
			<tr>
				<s:textfield  name="username"  key="userId" onblur="usernameIsExist();"/>
				<div id = "usernameMsg"></div>
			</tr>
			<tr>
				<s:password name="password"  key="new_password"/>
			</tr>
			<tr>
				<s:password name="repeatedPassword"  key="repeatedPassword"/>
			</tr>
		</table>
		<%@ include file="../account/IncludeAccountFields.jsp"%>
		<input type="submit" name="newAccount" value="<s:text name='saveInformation'/>">
	</form>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>