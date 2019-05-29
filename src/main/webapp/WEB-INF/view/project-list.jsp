<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Projects list</title>
    <meta charset="utf-8">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style-tablet.css"/>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style-mobile.css"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <script language="javascript"
            src="${pageContext.request.contextPath}/resources/js/project-search.js"></script>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="wrapper">
    <h1 class="project-title">
        Projects list
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input class="btn-change btn-logout" type="submit" value="Logout"/>
        </form:form>
    </h1>
    <div class="input-wrapper">
        <h2>Add new project</h2>
        <form:form action="addNewProjectForm" modelAttribute="project" method="POST">
            <form:hidden path="id"/>
            <form:input path="name" required="required" maxlength="45" placeholder="Name of project" autocomplete="off"/>
            <button type="submit" name="add-button"  class="hide-on-mobile">Add</button>
            <button type="submit" name="add-button"  class="button-mobile show-on-mobile">Add</button>

            <div class="show-on-mobile show-on-tablet">
                <br>
            </div>
            <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search">
        </form:form>
    </div>
    <div class="list-wrapper" id="style-1">
        <ul style="list-style:none;" id="myUL">
            <c:forEach var="tempProject" items="${projects}">

                <c:url var="into_project" value="/measurement/list">
                    <c:param name="projectId" value="${tempProject.id}"/>
                </c:url>

                <a href="${into_project}" style="text-decoration: none; color: black">
                    <li class="single-item"><span class="single-id"><c:out value="${tempProject.id}"/></span>
                        <span class="single-name"><c:out value="${tempProject.name}"/></span></li>
                </a>

            </c:forEach>
        </ul>
    </div>
    <div class="footer"> ItMove </div>
</div>

</body>
</html>
