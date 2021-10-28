<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Personal Contact Tracing App</span>
        </h1>
        <nav>
            <ul>
                <li id="actual"><a href="Controller?command=Home">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contacts">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>

    </header>
    <main>
        <h2>Home</h2>

        <c:choose>
            <c:when test="${person != null}">
                <p id="welcomeParagraph">Welcome, ${person.firstName}</p>
                <form action="Controller?command=LogOut" method="POST">
                    <input id="logOutButton" type="submit" value="Log Out">
                </form>
            </c:when>
            <c:otherwise>
                <p id="loginParagraph">Please log in</p>
                <c:if test="${error ne null}">
                    <br>
                    <div class="alert-danger">
                        <p id="loginError">
                            ${error}
                        </p>
                    </div>
                </c:if>
                <form action="Controller?command=LogIn" method="POST">
                    <p class="input">
                        <label for="userid">Your user id</label>
                        <input type="text" id="userid" name="userid">
                    </p>
                    <p class="input">
                        <label for="password">Your password</label>
                        <input type="password" id="password" name="password">
                    </p>
                    <input id="logInButton" type="submit" value="Log In">
                </form>
            </c:otherwise>
        </c:choose>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>