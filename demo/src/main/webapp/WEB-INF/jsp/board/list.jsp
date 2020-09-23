<%@ page language= "java" contentType= "text/html; charset=utf-8" pageEncoding= "utf-8" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel='stylesheet' href='/webjars/bootstrap/4.5.0/css/bootstrap.min.css'>
<head>
    <meta charset="UTF-8">
    <title>게시판 - 목록</title>
</head>
<style>
.progress { position:relative; width:100%; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
.bar { background-color: #17a2b8; width:0%; height:40px; border-radius: 6px; }
.percent { position:absolute; display:inline-block; top:5px; left:48%; }
</style>
<body>
<div class="container">
    <div class="modal-dialog">
        <h3>게시판</h3>
    </div>
    <table class="table">
        <thead class="thead-light">
        <tr class="text-center">
            <th scope="col">no</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">등록일</th>
            <th scope="col">수정일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boardList}" varStatus="status">
	        <tr class="text-center">
	            <th scope="row">${status.count}</th>
	            <td><a href="/oauth/board/post/${board.id}"> ${board.title} </a></td>
	            <td>${board.userName}</td>
	            <td>
					<javatime:format value="${board.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	            </td>
	            <td>
					<javatime:format value="${board.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	            </td>
	        </tr>
        </c:forEach>
        
        </tbody>
    </table>
    <a href="/oauth/board/post"> 등록</a>
</div>


<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
