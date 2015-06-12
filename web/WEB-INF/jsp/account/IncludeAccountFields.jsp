<%@ page import="myjpetstore.domain.Account" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<h3><s:text name="accountInformation"/></h3>

<table>
	<tr>
		<s:textfield  name="account.firstName" value="%{#session.account.firstName}" key="firstName"/>
	</tr>
	<tr>
		<s:textfield  name="account.lastName" value="%{#session.account.lastName}" key="lastName"/>
	</tr>
	<tr>
		<s:textfield  name="account.email" value="%{#session.account.email}" key="email"/>
	</tr>
	<tr>
		<s:textfield  name="account.phone" value="%{#session.account.phone}" key="phone"/>
	</tr>
	<tr>
		<s:textfield  size="40" name="account.address1" value="%{#session.account.address1}" key="address1"/>
	</tr>
	<tr>
		<s:textfield  size="40" name="account.address2" value="%{#session.account.address2}" key="address2"/>
	</tr>
	<tr>
		<s:textfield  name="account.city" value="%{#session.account.city}" key="city"/>
	</tr>
	<tr>
		<s:textfield  name="account.state" value="%{#session.account.state}" key="state"/>
	</tr>
	<tr>
		<s:textfield  name="account.zip" value="%{#session.account.zip}" key="zip"/>
	</tr>
	<tr>
		<s:textfield  name="account.country" value="%{#session.account.country}" key="country"/>
	</tr>
</table>

<h3><s:text name="profileInformation"/></h3>

<table>
	<tr>
		<td><s:text name="languagePreference"/></td>
		<td>
			<%
				ActionContext actionContext = ActionContext.getContext();
				Map session1 = actionContext.getSession();
				Account a =(Account)session1.get("account");
				String m ="";
				if(a!=null) m=a.getLanguagePreference();
			%>
			<select id="languagePreference" name="account.languagePreference">
				<option value="english" <%="english".equals(m)?"selected":"" %>><s:text name="english"/></option>
				<option value="japanese" <%="japanese".equals(m)?"selected":"" %>><s:text name="japanese"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td><s:text name="favouriteCategory"/></td>
		<td>
			<%
				String s ="";
				if(a!=null) s=a.getFavouriteCategoryId();
			%>
			<select id="favouriteCategoryId" name="account.favouriteCategoryId">
			<option value="FISH" <%="FISH".equals(s)?"selected":"" %>><s:text name="fish"/></option>
			<option value="DOGS" <%="DOGS".equals(s)?"selected":"" %>><s:text name="dogs"/></option>
			<option value="REPTILES"<%="REPTILES".equals(s)?"selected":"" %>><s:text name="reptiles"/></option>
			<option value="CATS" <%="CATS".equals(s)?"selected":"" %>><s:text name="cats"/></option>
			<option value="BIRDS"<%="BIRDS".equals(s)?"selected":"" %>><s:text name="birds"/></option>
		</select>
		</td>
	</tr>
	<tr>
		<td><s:text name="enableMyList"/></td>
		<td>
			<input type="checkbox" name="account.listOption"
			<s:if test="#session.account.listOption"> checked="checked"</s:if>
			value="1"/>
		</td>
	</tr>
	<tr>
		<td><s:text name="enableMybanner"/></td>
		<td>
			<input type="checkbox" name="account.bannerOption"
					<s:if test="#session.account.bannerOption"> checked="checked"</s:if>
				   value="1"/>
		</td>
	</tr>
</table>
