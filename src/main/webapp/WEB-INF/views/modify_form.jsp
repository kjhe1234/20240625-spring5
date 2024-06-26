<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용 수정</title>
</head>
<body>
	<h2>글 내용 수정</h2>
	<hr>
	<form action="modifyOk">
	<input type="hidden" name="bnum" value="${boarddto.bnum }">
	<table border="1" cellspacing="0" cellpadding="0" width="600">
		<tr>
			<td>번호</td>
			<td>${boarddto.bnum }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${boarddto.bhit }</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${boarddto.bid }</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${boarddto.bname }</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td>
			<input type="text" name="btitle" value="${boarddto.btitle }" size="60">
			</td>
		</tr>
		<tr>
			<td height="100" valign="top">글내용</td>
			<td valign="top">
				<textarea rows="15" cols="50" name="bcontent">${boarddto.bcontent }</textarea>
			</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${boarddto.bdate }</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="수정 완료" >
				<input type="button" value="수정 취소" onclick="javascript:history.go(-1)">
				
			</td>
		</tr>
		
	</table>
	</form>
</body>
</html>