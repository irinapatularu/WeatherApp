<%--
  Created by IntelliJ IDEA.
  User: irina.patularu
  Date: 22/06/16
  Time: 12:49
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>

    </title>
</head>

<body>

<g:form controller="Weather" action="save">
     <label>First Name: </label>
     <g:textField name="location"/><br/>
     <g:submitButton name="save" value="Save"/>
</g:form>

<p>${location}</p>
<p>${temp}</p>
<p>${description}</p>
<p>${humidity}</p>
<p>${country}</p>

</body>
</html>