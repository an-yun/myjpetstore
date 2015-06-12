<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="saveInformation" method="post">

	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td><s:property value="%{#session.account.username}"/></td>
		</tr>
		<tr>
			<s:password name="password"  label="New password"/>
		</tr>
		<tr>
			<s:password name="repeatedPassword"  label="Repeat password"/>
		</tr>
		<tr>
			<font color = 'red'><s:property value="saveInformationmsg"/></font>
		</tr>
	</table>
	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="editAccount" value="Save Account Information" />

</form>
	<a href="listOrders">My Orders</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
