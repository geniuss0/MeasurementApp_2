<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="var" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
    <title>Measurement list</title>
    <meta charset="utf-8">
    <link type="text/css"
          rel="stylesheet"
          media="screen"
          href="${pageContext.request.contextPath}/resources/css/measurement-list-style.css"/>
    <link type="text/css"
          rel="stylesheet"
          media="screen"
          href="${pageContext.request.contextPath}/resources/css/measurement-list-style-tablet.css"/>
    <link type="text/css"
          rel="stylesheet"
          media="screen"
          href="${pageContext.request.contextPath}/resources/css/measurement-list-style-mobile.css"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
    <%--<script language="javascript"--%>
    <%--src="${pageContext.request.contextPath}/resources/js/measurement-search.js"></script>--%>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="wrapper">

    <h1>
        <div class="left-arrow">
            <a href="${pageContext.request.contextPath}/project/list">
                <img src="${pageContext.request.contextPath}/resources/images/arrow-left.png" alt="back">
            </a>
        </div>
        Project details
    </h1>
    <div class="title-wrapper">
        <h2>
            <span class="single-id"> ${project.id} </span>
            <span id="projectname" class="single-name" contentEditable="true" onkeyup="getelementname()">${project.name}</span>
        </h2>
            <form:form style="display: inline;" action="saveProjectName" modelAttribute="project" method="POST">
                <button class="btn-change">
                        <form:input style="display: none;" type="hidden" path="id" value="${project.id}"/>
                        <form:input style="display: none;" id="franszeska" type="hidden" path="name" value=""/>
                    Save
                </button>
            </form:form>
            <c:url var="deleteProjectLink" value="/measurement/deleteProject">
                <c:param name="projectId" value="${project.id}"/>
            </c:url>
            <button class="btn-change">
                <a href="${deleteProjectLink}" onclick="if(!(confirm('Are you sure?'))) return false">
                    Delete
                </a>
            </button>
            <button class="btn-change"><a href="${pageContext.request.contextPath}/measurement/download">Download</a></button>

        <button class="btn-change"
                onclick="var test = document.querySelector('.add-new');
                test.style.display = 'block';
                document.getElementById('add').click();">
            Add
        </button>
    </div>
    <div class="list-wrapper" id="style-1">
        <ul>
            <li class="add-new">
                <form:form action="addNewMeasurement" modelAttribute="newMeasurement" method="POST">
                    <button type="submit" id="add"></button>
                    <%--class="collapsible"--%>
                </form:form>
            </li>

            <c:forEach items="${measurements}" var="measurement">
                <c:url var="deleteLink" value="/measurement/delete">
                    <c:param name="measurementId" value="${measurement.id}"/>
                </c:url>
                <li>
                    <button class="collapsible">${measurement.name}</button>
                    <div class="content">
                        <div>
                            <form:form method="POST" action="saveMeasurement" modelAttribute="inListMeasurement">
                                <form:input class="input-style" type="hidden" path="id" value="${measurement.id}"/>
                                <form:input class="input-style" path="name" value="${measurement.name}" placeholder="Name"/>
                                <form:input class="input-style" path="description" value="${measurement.description}" placeholder="Description"/>
                                <form:input class="input-style" path="unit" value="${measurement.unit}" placeholder="Unit"/>
                                <form:input class="input-style" path="result" value="${measurement.result}" placeholder="Result"/>
                                <div style="display: flex; justify-content: space-between;">
                                <input class="btn-change" style="width: 135px; margin-bottom: 0;" type="submit" value="Submit"/>
                                <button class="btn-change">
                                    <a href="${deleteLink}" onclick="if(!(confirm('Are you sure?'))) return false">
                                        Delete
                                    </a>
                                </button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="footer"> ItMove
<script>
    var coll = document.getElementsByClassName("collapsible");
    var i;
    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.maxHeight){
                content.style.paddingTop = null;
                content.style.paddingBottom = null;
                content.style.maxHeight = null;
            } else {
                content.style.maxHeight = content.scrollHeight + "px";
                content.style.paddingBottom = 20 + "px";
                content.style.paddingTop = 20 + "px";
            }
        });
    }
    function getelementname() {
        var name = document.getElementById('projectname').innerText;
        console.log(name);
        var fra = document.getElementById('franszeska');
        fra.value = name;
    }
</script>
</body>
</html>
