<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" --%>
<!-- Удалить лишнее, юзаю только джеестеель кор -->
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="<c:url value="\css\style.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="\css\validation.css" />" />

    <script src="<c:url value="\js\tableOperationz.js" />"></script>

	<title>Добавление/удаление группы</title>

</head>

<body>

     <div id="signup-form">

        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		
			    <img id="signup-icon" src="<c:url value="\images\group.png" />" alt="" />

                    <h1>
                        <c:choose>
                            <c:when test="${update}">
                                <c:out value="Редактирование группы с ID = ${group.id}" />


                            </c:when>
                            <c:otherwise>
                                <c:out value="Добавление группы"/>
                            </c:otherwise>
                        </c:choose>
				
				    </h1>
            
            </div>
			
			
			<p>Пожалуйста, заполните поля ниже.</p>
            
            <form:form method="POST" action="http://localhost:8082/groups/processform" modelAttribute="group" id="send">

				<form:input type="hidden" value="${group.id}" path="id" />

                <p>
                <label for="title">Название *</label>
                <form:input id="title" path = "title" value="${group.title}" minlength="2" maxlength="35" required = "true" /> <!-- required -->
                </p>

				<p>
                <label for="teachers">Список преподавателей</label>
                <input id="teachers" type="text" value="" />
                </p>
                
                <p>
                <label for="students">Список студентов</label>


                <table border = "1 px solid" id="studentsTable">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Действие</th>
                    </tr>
                </thead>
                <!-- group - модель, студенты - аттрибут -->

                <tbody>


                <!-- при нажатии на кнопку уменьшить индекс? -->

                <!-- JSTL -- -->

<!--
tagStatus--

<c:out value="${16+64*2}" />
-->


   <!--
   <c:set var="count" value="${count + 1}" scope="page"/>
                        -->




                    <!--  begin="1970" end="2000" -->



                        <!-- group.students[].id -->


<!--
                    <c:set value = "${group.students}" var = "groupSt" />
-->
                    <c:forEach items="${group.students}" var="stdList" varStatus="tagStatus">


                        <tr>


                            <input type = "text" value =
                                "<c:out value="${tagStatus.index}" />"
                             />


                            <!-- Индексы пофиксить -->
                            <!-- HIDDENS -->

                            <td><form:hidden path="students[${tagStatus.index}].id" value="${stdList.id}" /></td>
                            <td><form:hidden path="students[${tagStatus.index}].name" value="${stdList.name}" /></td>
                            <td><form:hidden path="students[${tagStatus.index}].fam" value="${stdList.fam}" /></td>
                            <td><form:hidden path="students[${tagStatus.index}].otch" value="${stdList.otch}" /></td>
                            <td><form:hidden path="students[${tagStatus.index}].date_of_birth" value="${stdList.date_of_birth}" /></td>
                            <td><form:hidden path="students[${tagStatus.index}].phone_number" value="${stdList.phone_number}" /></td>
                            <td><form:hidden path="students[${tagStatus.index}].gruppa" value="${stdList.gruppa.id}" /></td>


                            <!-- /HIDDENS  -->

                            <td>${stdList.fam}</td>
                            <td>${stdList.name}</td>
                            <td>${stdList.otch}</td>

                            <!-- Все поля hidden а вывод в таблицу отдельно? -->
                            <!-- передается только тайтл -->



                            <td><a href = "#" onclick = "deleteRow(this);

                                                      "<c:set var="tagStatus.index" value="${tagStatus.index - 1}" />"




                            " >Удалить</a></td>





                        </tr>



                    </c:forEach>




                </tbody>

                </table>

<br/>
<br/>
<br/>
<br/>

<!--
                <input type = "button" onclick = "alert(${tagStatus})" value = "showTagStatus" />
-->

                <input id="students" type="text" value="" />
                </p>
                
                <p>
                <input type="submit" id="submit" value="Отправить" />
                </p>
                
            </form:form>
            
		<div id="required">
		<p>* Поля, обязательные для заполнения</p>
		</div>


            </div>

        </div>

    </div>

</body>
</html>
