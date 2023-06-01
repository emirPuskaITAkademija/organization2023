<%--
  Created by IntelliJ IDEA.
  User: Grupa 1
  Date: 5/29/2023
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
The JavaServer Pages Standard Tag Library (JSTL) is a collection of useful JSP tags which encapsulates the core functionality common to many JSP applications.
JSTL has support for common, structural tasks such as iteration and conditionals, tags for manipulating XML documents, internationalization tags, and SQL tags.
It also provides a framework for integrating the existing custom tags with the JSTL tags.
-->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Registration</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
        integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous"/>
</head>
<body>
<div style="padding:20px">
  <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Registration Form</h3>

  <!-- Form input fields -->
  <form action="register" method="post" style="border:1px solid #ccc; padding: 20px;">

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="name" id="fn">Name :</label>
        <input id="name" class="form-control" required="true" type="text" name="name"><br/>
      </div>
    </div>

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="surname" id="ln">Surname :</label>
        <input id="surname" class="form-control" required="true" type="text" name="surname"><br/>
      </div>
    </div>

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="username">Username :</label>
        <input id="username" class="form-control" required="true" type="text" name="username"><br/>
      </div>
    </div>

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="password">Password :</label>
        <input minlength="3" maxlength="15" id="password" class="form-control" required="true" type="password" name="password"><br/>
      </div>
    </div>

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="email">Email :</label>
        <input id="email" class="form-control" required="true" type="text" name="email"><br/>
      </div>
    </div>

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="contact">Contact :</label>
        <input id="contact" class="form-control" required="true" type="text" name="contact"><br/>
      </div>
    </div>

    <div class="col-md-4">
      <div class="form-outline">
        <label class="form-label" for="town">Town: </label>
        <select id="town" class="form-control" required="true" name="town">
          <c:forEach items="${towns}" var="town">
            <option>${town}</option>
          </c:forEach>
        </select><br/><br/>
      </div>
    </div>

    <div class="col-md-6">
      <button class="col-md-8 btn btn-info" type="submit" value="Submit">Register</button>
      <button class="col-md-8 btn btn-link" type="button" onclick="location.href = 'authenticate';">Back to
        login page
      </button>
    </div>
    <div><%=request.getAttribute("message")!=null ? request.getAttribute("message"):""%></div>
  </form>
</div>
</body>
</html>