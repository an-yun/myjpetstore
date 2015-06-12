<%@ include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
  <a href="main">Return to Main Menu</a>
</div>
<div id="Catalog">
  <h2>System Log</h2>

  <table>
    <tr>
      <th>Time</th>
      <th>Event</th>
    </tr>
    <s:iterator value="logList">
      <tr>
        <td><s:property value="time"/></td>
        <td><s:property value="event"/></td>
      </tr>
    </s:iterator>
  </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>