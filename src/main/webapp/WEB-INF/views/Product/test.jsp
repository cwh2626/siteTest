<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <script type="text/javascript" src="../resources/ckeditor/ckeditor.js"></script>
    <script type = "text/javascript">
	alert('비밀번호을 입렵해주세요');
    
    window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}','${filePath}', '업로드완료');
    </script>


</head>
<body>

</body>
</html>