<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="/SpringMVC4_2/">
	<meta charset="utf-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>Result of Upload</h1>
	<img src="images/${filename}">
	<ul>
		<li>FileName: ${filename}</li>
		<li>FileType: ${filetype}</li>
		<li>FileSize: ${filesize}</li>
	</ul>
	${message}
</body>
</html>