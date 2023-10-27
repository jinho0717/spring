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
<!-- email, pwd, nick_name -->
<div class="container">
	<h4>Input your Infomation</h4>
<form action="/member/register" method="post">
	<div class="mb-3">
	  <label for="e" class="form-label">EMAIL</label>
	  <input type="email" class="form-control" name="email" id="e" placeholder="example@OOO.com">
	</div>
	<div class="mb-3">
	  <label for="p" class="form-label">PASSWORD</label>
	  <input type="password" class="form-control" name="pwd" id="p" placeholder="password">
	</div>
	<div class="mb-3">
	  <label for="n" class="form-label">NICK_NAME</label>
	  <input type="text" class="form-control" name="nickName" id="n" placeholder="nick_name">
	</div>
	<button type="submit" class="btn btn-dark">등록</button>
</form>	
</div>


<jsp:include page="../common/footer.jsp" />
</body>
</html>