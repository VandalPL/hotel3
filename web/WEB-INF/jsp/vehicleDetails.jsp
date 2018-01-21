<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="shared/header.jsp">
    <jsp:param name="pageName" value="vehicleDetails"/>
</jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Szczegóły pojazdu</title>
</head>
<body>
<div id="main" class="container">
<H1>Dane pojazdu</H1>
<security:authorize access="hasRole('ADMIN')">
    Id: <b>${roomDetail.id}</b><br/>
</security:authorize>
    Nazwa: <b><c:out value="${empty roomDetail.name?'Brak danych': roomDetail.name}" escapeXml="true"/> </b><br/>
    Typ pokoju: <b><c:out value="${empty roomDetail.roomType.name?'Brak danych': roomDetail.roomType.name}" /> </b><br/>
    Cena: <b><fmt:formatNumber type="CURRENCY" value="${roomDetail.price}"  currencySymbol="PLN"/></b><br/>


<security:authorize access="hasRole('ADMIN')">
    <c:url var="editUrl" value="/vehicleForm.html?id=${roomDetail.id}" />
    <a class="btn btn-success" href="${editUrl}">Edytuj</a>
</security:authorize>
    <a class="btn btn-success" href="/vehicleList.html">Wstecz</a>
</div>
</body>
</html>

<jsp:include page="shared/footer.jsp"/>