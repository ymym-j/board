<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<form action="/oauth/board/post/${board.id}" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="_method" value="put"/>
<Input type="hidden" id="boardId" value='${board.id }' />
    제목 : <input type="text" name="title" value = '${board.title}'> <br>
    내용 :
    <textarea name="content">${board.content}</textarea><br>
	작성자 : <input type="text" name="content" value="${board.userName}" readonly> <br>
    <input type="submit" id="updateButton" value="수정">
</form>
<form id="delete-form" action="/oauth/board/post/${board.id}" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     <input type="hidden" name="_method" value="delete"/>
     <button id="delete-btn" type="submit" class="btn btn-danger">삭제</button>
</form>
<a href="/oauth/board">목록으로</a>
</body>
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
</html>