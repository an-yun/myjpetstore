<%@ include file="../common/IncludeTop.jsp"%>
<div id="Catalog">
	<a href="signonForm?request_locale=zh_CN"><s:text name="chinese"/></a> |
	<a href="signonForm?request_locale=en_US"><s:text name="english"/></a><br>
	<tr><td><s:text name="signonTip"/></td></tr>
	<s:form action="signon" method="POST">
		<s:textfield  name="account.username" value="j2ee" key="username"/>
		<s:password name="account.password" value="j2ee" key="password"/>
		<tr>
			<td><s:text name="validationCode"/></td>
			<td><s:textfield name="validationCode" theme="simple"/><img src="validationCode.action"/></td>
		</tr>
		<div><tr><td><font color="red"><s:property value="logMsg" /></font></td></tr></div>
		<s:submit  value="%{getText('submit')}"/>
	</s:form>
	<s:text name="registerTip"/>
	<a href="newAccountForm"><s:text name="registerLink"/></a>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>

