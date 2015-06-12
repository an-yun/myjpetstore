<%@ page import="myjpetstore.domain.Order" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
		<form action="continueConfirm" method="post">
	<table>
		<tr>
			<th colspan=2>Payment Details</th>
		</tr>

		<tr>
			<td>Card Type:</td>
			<td><select name="order.cardType">
				<%
					ActionContext actionContext = ActionContext.getContext();
					Map session1 = actionContext.getSession();
					Order o = (Order)session1.get("order");
					String os ="";
					if(o!=null) os=o.getCardType();
				%>
				<option value="Visa" <%="Visa".equals(os)?"selected":"" %>>Visa</option>
				<option value="MasterCart" <%="MasterCart".equals(os)?"selected":"" %>>MasterCart</option>
				<option value="American Express"<%="American Express".equals(os)?"selected":"" %>>American Express</option>
			</select>
			</td>
		</tr>
		<tr>
			<s:textfield name="order.creditCard" value="%{#session.order.creditCard}" label="Card Number"/>
		</tr>
		<tr>
			<s:textfield name="order.expiryDate" value="%{#session.order.expiryDate}" label="Expiry Date (MM/YYYY)"/>
		</tr>
		<tr>
			<th colspan=2>Billing Address</th>
		</tr>

		<tr>
			<s:textfield name="order.billToFirstName" value="%{#session.order.billToFirstName}" label="First name"/>
		</tr>
		<tr>
			<s:textfield name="order.billToLastName" value="%{#session.order.billToLastName}" label="Last name"/>
		</tr>
		<tr>
			<s:textfield name="order.billAddress1" value="%{#session.order.billAddress1}" label="Address 1"/>
		</tr>
		<tr>
			<s:textfield name="order.billAddress2" value="%{#session.order.billAddress2}" label="Address 2"/>
		</tr>
		<tr>
			<s:textfield name="order.billCity" value="%{#session.order.billCity}" label="City"/>
		</tr>
		<tr>
			<s:textfield name="order.State" value="%{#session.order.State}" label="State"/>
		</tr>
		<tr>
			<s:textfield name="order.Zip" value="%{#session.order.Zip}" label="Zip"/>
		</tr>
		<tr>
			<s:textfield name="order.Country" value="%{#session.order.Country}" label="Country"/>
		</tr>
		<tr>
			<td colspan=2><input type="checkbox" name="shippingAddressRequired" />
			Ship to different address...</td>
		</tr>

	</table>
			<input type="submit" name="newOrder" value="Continue" />
		</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>