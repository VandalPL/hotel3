<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="supply"></c:param>
</c:import>
<html>
<head>
    <title>Bar</title>
</head>
<body>
<div id="main" class="container">
    <H1>Menu</H1>

<form:form id="id" modelAttribute="supplyList">


    <form:form id="asearchForm" modelAttribute="searchCommand">
        <div class="row">
            <div class="form-group col-md-6">
                <label for="phrase">Szukana fraza:</label>
                <form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="phrase" cssClass="error text-danger" element="div"/>
            </div>

        </div>
        <div class="row">

            <div class="form-group col-md-8"></div>

                <div class="form-group col-md-2">
                <c:if test="${searchCommand.isEmpty() eq false}">
                    <a href="/vehicleList.html?all" class="btn btn-success">
                        <span class="glyphicon glyphicon-refresh"></span> Pokaż wszystko
                    </a>
                </c:if>
            </div>

                <div class="form-group col-md-2">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Wyszukaj
                </button>
            </div>
        </div>
    </form:form>

    <c:if test="${empty supplyList.content}">
        ${searchCommand.isEmpty() ? 'Lista jedzenia jest pusta':'Brak wyników wyszukiwania'}
    </c:if>

    <c:if test="${not empty supplyList.content}">

        <c:choose>
            <c:when test="${searchCommand.isEmpty()}">
                Lista zawiera ${supplyList.totalElements} pozycji
            </c:when>
            <c:otherwise>
                Wynik wyszukiwania: ${supplyList.totalElements} pozycji
            </c:otherwise>
        </c:choose>


        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Nazwa</th>

                <th>Ilosc</th>


                <security:authorize access="hasAnyRole('ADMIN','USER')">
                <th>Opcje</th>
            </security:authorize>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${supplyList.content}" var="supply">

                <tr>
                    <td>${supply.name}</td>
                    <td>${supply.ilosc}</td>


                    <td>
                            <security:authorize access="hasRole('ADMIN')">
                            <c:url var="deleteUrl" value="/supply.html?did=${supply.id}" />
                            <%--<c:url var="editUrl" value="/vehicleForm.html?id=${room.id}" />--%>
                            <c:url var="editUrl" value="/supplyForm.html?id=${supply.id}" />
                            <a class="btn btn-warning" href="${editUrl}">Edytuj</a>
                            <a class="btn btn-danger" href="${deleteUrl}">Usuń</a>

                            <%--<a class="btn btn-success" href="${editUrl}">Edytuj</a>--%>
                        </td>
                        </security:authorize>

                </tr>
            </c:forEach>
            <%--</c:forEach>--%>
            </tbody>
        </table>

        <c:set var="page" value="${vehicleListPage}" scope="request"/>
        <c:set var="mainUrl" value="bar.html" scope="request"/>
        <%--<jsp:include page="shared/pagination.jsp"/>--%>

    </c:if>
    <security:authorize access="hasRole('ADMIN')">
        <a class="btn btn-success" href="supplyForm.html">Dodaj Nowy</a>
    </security:authorize>
</form:form>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>
