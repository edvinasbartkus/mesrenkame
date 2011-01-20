<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Crawl 2007 Municipality elections</title></head>
  <body>
    <table>
      <tr>
        <th>Key</th>
        <th>Before</th>
        <th>After</th>
      </tr>
    <g:each in="${before.keySet()}" var="key">
      <tr>
        <td>${key}</td>
        <td>${before[key]}</td>
        <td>${after[key]}</td>
      </tr>
    </g:each>
    </table>
  </body>
</html>