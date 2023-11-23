<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="aaaa.TableRow" %>
<%--<jsp:useBean id="lastPoint" scope="request" class="aaaa.TableRow"/>--%>
<%List<TableRow> tableRows1 = (List<TableRow>) session.getAttribute("tableRows");%>
<html>
<head>
  <title>Result</title>
  <style>
    th  {
      border: 1px solid grey;
      padding: 10px 10px;
      font-weight: normal;
      background-color: #ddd;
    }
    #res {
      border: 1px solid grey;
      border-collapse: collapse;
      margin: auto;
      width: 970px;
    }
    td {
      border: 1px solid grey;
      padding: 10px 10px;
      font-weight: normal;
      text-align: center;
    }
    input[type="button"] {
      background-color: #333;
      color: #fff;
      border: none;
      cursor: pointer;
      text-align: center;
      font-size: 17px;
    }
    caption {
      caption-side: top;
      text-align: center;
      padding: 10px 0;
      font-size: 25px;
    }
  </style>
</head>
<body>
<table id="column result_table">
  <caption>Чеклист</caption>
  <tr>
    <th>X</th>
    <th>Y</th>
    <th>R</th>
    <th>попадание</th>
    <th>текущее время</th>
  </tr>
  <%
    if (tableRows1 != null && !tableRows1.isEmpty()) {
      TableRow lastPoint = tableRows1.get(tableRows1.size() - 1);
  %>
  <tr>
    <td class="x_cell"><%= lastPoint.getX() %>
    </td>
    <td class="y_cell"><%= lastPoint.getY() %>
    </td>
    <td class="r_cell"><%= lastPoint.getR() %>
    </td>
    <td><%= lastPoint.getInside()%>
    </td>
    <td><%= lastPoint.getLocalTime() %>
    </td>
  </tr>
  <%}%>
</table>
<a href="${pageContext.request.contextPath}">
  <button id="goBackButton">Go back</button>
</a>
</body>
</html>
