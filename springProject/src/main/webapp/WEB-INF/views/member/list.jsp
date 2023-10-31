<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<table class="table table-hover">
	<tr>
		<th>아이디</th>
		<th>닉네임</th>
		<th>계정 생성일</th>
		<th>마지막 로그인</th>
	</tr>	
	
	
	
	<c:forEach items="${memList }" var="mvo">
	<tr>
		<th>${mvo.email }</th>
		<th>${mvo.nickName }</th>
		<th>${mvo.regAt }</th>
		<th>${mvo.lastLogin }</th>
	</tr>
	</c:forEach>
</table>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>