<%--
  Created by IntelliJ IDEA.
  User: irina.patularu
  Date: 22/06/16
  Time: 12:49
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="../css/style.css" >
    <title>
    </title>
</head>

<body>
<section class="banner">
    <div class="container">
        <div class="search">
        <g:form class="form" controller="Weather" action="save">
            <g:textField class="textarea" name="location"/><br/>
            <g:submitButton class="button" name="save" value="Get Weather"/>
        </g:form>
        </div>

        <div class="info">
            <div class="card">
                <h2>Country: ${country}</h2>
            </div>

            <div class="card">
                <h2>Description: ${description}</h2>
            </div>

            <div class="card">
                <h2>Temperature: <g:formatNumber number="${temp}" type="number" maxFractionDigits="0"/></h2>
            </div>

            <div class="card">
                <h2>Humidity: ${humidity}</h2>
            </div>
        </div>
</section>
</body>
</html>