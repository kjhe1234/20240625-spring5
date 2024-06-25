<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 조회 결과</title>
</head>
<body>
	<h2>회원 정보</h2>
	<hr>
	<h3>${searchFail  }</h3>
	<h3>
		아이디 : ${memberdto.mid }<br><br>
		비밀번호 : ${memberdto.mpw }<br><br>
		이름 : ${memberdto.mname }<br><br>
		이메일 : ${memberdto.memail }<br><br>
		가입일 : ${memberdto.mdate }<br><br>

	</h3>


</body>
</html>