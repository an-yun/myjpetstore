<%@ include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>
<div id="Catalog">

<h2><s:property value="category.name"/></h2>

<table>
	<tr>
		<th>Product ID</th>
		<th>Name</th>
	</tr>
	<s:iterator value="productList">
		<tr>
			<td>
				<a href="viewProduct?productId=<s:property value="productId"/> " name="categoryId"><s:property value="productId"/></a>
			</td>
			<td><s:property value="name"/></td>
		</tr>
	</s:iterator>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


