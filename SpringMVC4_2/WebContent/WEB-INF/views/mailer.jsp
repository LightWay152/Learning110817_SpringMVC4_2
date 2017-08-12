<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<base href="/SpringMVC4_2/">
	<meta charset="utf-8">
	<title>Insert title here</title>
</head>
<body>
	${thongbao}
	<form:form action="mailer/send.php" 
		modelAttribute="mail" enctype="multipart/form-data">
		<div>
			<div>FROM</div>
			<form:input path="from"/>
		</div>
		<div>
			<div>TO</div>
			<form:input path="to"/>
		</div>
		<div>
			<div>SUBJECT</div>
			<form:input path="subject"/>
		</div>
		<div>
			<div>BODY</div>
			<form:textarea path="body" rows="3"/>
		</div>
		<div>
			<div>ATTACHMENT</div>
			<input type="file" name="file"/>
		</div>
		<hr>
		<button>Send</button>
	</form:form>
</body>
</html>