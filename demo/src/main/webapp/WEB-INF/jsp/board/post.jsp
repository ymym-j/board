<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
	<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <form action="post" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        제목 : <input type="text" name="title"><br>
        내용 :
        <textarea name="content"></textarea><br>
 		작성자 : <input type="text" name="name" value="${sessionScope.userName}"  readonly> <br>
        <input type="submit" value="등록" id="saveButton">
    </form>
</body>
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
</html>