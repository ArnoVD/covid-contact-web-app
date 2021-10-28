<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Contacts</title>
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
            Contact Overview
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

        <table>
            <c:choose>
                <c:when test="${not empty contacts}">
                    <tr>
                        <th>Date</th>
                        <th>Hour</th>
                        <th>Name</th>
                    </tr>
                    <c:forEach var="c" items="${contacts}">
                        <tr>
                            <td>${c.contactDate}</td>
                            <td>${c.contactHour}</td>
                            <td>${c.firstName} ${c.lastName}</td>
                            <td>
                                <a id="deleteContact" href="Controller?command=DeleteContact&firstname=${c.firstName}&lastname=${c.lastName}&contactdate=${c.contactDate}&contacthour=${c.contactHour}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2 id="emptyListH2">There are currently no contacts registered.</h2>
                </c:otherwise>
            </c:choose>

            <caption>Contacts Overview</caption>
        </table>

        <form action="Controller?command=Contacts" method="post"  novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p>
                <label for="contactFirstName" class="${contactFirstNameClass}">First Name</label>
                <input type="text" id="contactFirstName" name="contactFirstName" value="${contactFirstNamePreviousValue}" required>
            </p>
            <p>
                <label for="contactLastName" class="${contactLastNameClass}">Last Name</label>
                <input type="text" id="contactLastName" name="contactLastName" value="${contactLastNamePreviousValue}" required>
            </p>
            <p>
                <label for="contactDate" class="${contactDateNameClass}">Date</label>
                <input type="text" id="contactDate" name="contactDate" value="${contactDatePreviousValue}" required>
            </p>
            <p>
                <label for="contactHour" class="${contactHourNameClass}">Hour</label>
                <input type="number" id="contactHour" name="contactHour" value="${contactHourPreviousValue}" required>
            </p>
            <p>
                <label for="contactPhoneNumber" class="${contactPhoneNumberClass}">Phone</label>
                <input type="text" id="contactPhoneNumber" name="contactPhoneNumber" value="${contactPhoneNumberPreviousValue}" required>
            </p>
            <p>
                <label for="contactEmail" class="${contactEmailClass}">Email</label>
                <input type="email" id="contactEmail" name="contactEmail" value="${contactEmailPreviousValue}" required>
            </p>
            <p>
                <input type="submit" id="addContact" name="addContact" value="Add Contact">
            </p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>