<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Personal Contact Tracing App</span></h1>
        <nav>
            <ul>
                <li><a href="Controller?command=Home">Home</a></li>
                <li id="actual"><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Contacts">Contacts</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
        <table>

            <c:choose>
                <c:when test="${not empty persons}">
                    <tr>
                        <th>E-mail</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                    <c:forEach var="p" items="${persons}">
                        <tr>
                            <td>${p.email}</td>
                            <td>${p.firstName}</td>
                            <td>${p.lastName}</td>
                            <td>
                                <a id="delete" href="Controller?command=Delete&userid=${p.userid}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2 id="emptyListH2">There are currently no persons registered.</h2>
                </c:otherwise>
            </c:choose>

            <caption>Users Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>