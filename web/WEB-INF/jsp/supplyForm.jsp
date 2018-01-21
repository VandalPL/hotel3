<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="supplyForm"></c:param>
</c:import>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div id="main" class="container">


    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form:form modelAttribute="supplies">

                <div class="form-group">
                    <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="nazwa"/>
                    <form:errors path="name" cssClass="error text-danger" element="div"/>
                </div>
                <div class="form-group">
                    <form:input path="ilosc" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="ilosc"/>
                    <form:errors path="ilosc" cssClass="error text-danger" element="div"/>
                </div>

                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:input path="model" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="model"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:errors path="model" cssClass="error text-danger" element="div"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--<div class="form-group">--%>
                    <%--<form:input path="price" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="cena"/>--%>
                    <%--<form:errors path="price" cssClass="error text-danger" element="div"/>--%>
                <%--</div>--%>
                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:input path="productionDate" cssClass="form-control" type="date" cssErrorClass="form-control is-invalid" placeholder="data produkcji"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:errors path="productionDate" cssClass="error text-danger" element="div"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--<div class="form-group">--%>
                    <%--<form:select path="roomType.id" cssClass="form-control" cssErrorClass="form-control is-invalid">--%>
                        <%--<form:option value="-1">--wybierz typ pojazdu--</form:option>--%>
                        <%--<form:options items="${vehicleTypes}" itemLabel="name" itemValue="id"/>--%>
                    <%--</form:select>--%>
                    <%--<form:errors path="roomType.id" cssClass="error text-danger" element="div"/>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label>Wyposażenie pojazdu:</label>--%>
                    <%--<form:checkboxes path="accessories" element="div class='checkbox' style='left:25px;'" items="${accessoryList}" itemLabel="name" itemValue="id"/>--%>
                    <%--<form:errors path="accessories" cssClass="error text-danger" element="div"></form:errors>--%>
                <%--</div>--%>
                <div class="form-group">
                    <button type="submit" class="btn btn-success" class="btn btn-success">Wyślij</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>



<jsp:include page="shared/footer.jsp"/>
