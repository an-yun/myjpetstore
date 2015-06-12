<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="viewCategory?categoryId=<s:property value="product.categoryId"/>">Return to <s:property value="product.categoryId"/></a>
</div>

<div id="Catalog">

<h2><s:property value="product.name"/></h2>

<table>
	<tr>
		<th>Item ID</th>
		<th>Product ID</th>
		<th>Description</th>
		<th>List Price</th>
		<th>&nbsp;</th>
	</tr>
	<s:iterator value="itemList">
		<tr>
			<td>
				<a href="viewItem?itemId=<s:property value="itemId"/>"><s:property value="itemId"/></a>
			</td>
			<td>
				<s:property value="product.productId"/>
			</td>
			<td>
				<s:property value="attribute1"/>
				<s:property value="attribute2"/>
				<s:property value="attribute3"/>
				<s:property value="attribute4"/>
				<s:property value="attribute5"/>
				<s:property value="attribute1"/>
				<s:property value="product.name"/>
			</td>
			<td>$<s:property value="listPrice"/></td>
			<td>
				<a class="Button" href="addItemToCart?workingItemId=<s:property value="itemId"/>">Add to Cart</a>
			</td>
		</tr>
	</s:iterator>
	<tr>
		<td>
		</td>
	</tr>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>





