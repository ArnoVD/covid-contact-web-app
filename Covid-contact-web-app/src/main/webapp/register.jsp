<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Personal Contact Tracing App</span></h1>
        <nav>
            <ul>
                <li><a href="Controller?command=Home">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contacts">Contacts</a></li>
                <li id="actual"><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>
            Register
        </h2>

    </header>
    <main>

        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul id="errorsList">
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form action="Controller?command=Register" method="post"  novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p>
                <label for="userid" class="${useridClass}">User id:</label>
                <input type="text" id="userid" name="userid" value="${useridPreviousValue}" required>
            </p>
            <p>
                <label for="firstName" class="${firstNameClass}">First Name</label>
                <input type="text" id="firstName" name="firstName" value="${firstNamePreviousValue}" required>
            </p>
            <p>
                <label for="lastName" class="${lastNameClass}">Last Name</label>
                <input type="text" id="lastName" name="lastName" value="${lastNamePreviousValue}" required>
            </p>
            <p>
                <label for="email" class="${emailClass}">Email</label>
                <input type="email" id="email" name="email" value="${emailPreviousValue}" required>
            </p>
            <p>
                <label for="password" class="${passwordClass}">Password</label>
                <input type="password" id="password" name="password" value="${passwordPreviousValue}" required>
            </p>
            <p>
                <input type="submit" id="signUp" name="signUp" value="Sign Up">
            </p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
