<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="../../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../../css/validation.css" />
	<title>Ошибка 500!</title>
</head>
<body>
<jsp:include page="../security/auth.jsp" />
     <div id="signup-form">
        	<div class="clearfix" id="header">
			    <img id="signup-icon" src="../../images/group.png" alt="" />
                    <h1>
                       Ошибка. Такая сущность не найдена!
				    </h1>
            </div>
            <div align = center>
             <img src="../../images/error500.png" />
			<h2>Пожалуйста, проверьте правильность ввода ID
			    или вернитесь на <a href="../../">главную страницу</a>.
			</h2>
            </div>
		<div id="required">
			<p align="center">VCPROJECT <script>document.write(new Date().getFullYear())</script></p>
		</div>
            </div>
   </div>
</body>
</html>
