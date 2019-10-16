<%--
  Created by IntelliJ IDEA.
  User: vasilevvs
  Date: 15.10.2019
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>AjaxTest</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>

    <script>
        // wait for the DOM to be loaded
      /*  $(document).ready(function() {
            // bind 'searchForm' and provide a simple callback function
            $('#searchForm').ajaxForm(function() {


                success: function(response){
                    var resdata = response;
                    alert(resdata['name']);
                }





/!*
                   success: function(responseText){
                    alert(responseText);

                }
*!/

                /!*$.getJSON('http://localhost:8082/ajaxprocessform', function(data) {

                 alert(data);
                 //data is the JSON string
                 });*!/



            });
        });*/
    </script>
</head>
<body>

<!-- searchForm -->
<!-- modelAttribute="searchString" -->


<form id="searchForm" action="../ajaxprocessform" method="post" >

        <center>
            Введите критерий поиска:
        <br/>
            <input type="text" name="searchString" /><!-- name = field -->

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" modelAttribute="searchString" />

            <input type="submit" value="Поиск" />

        <br/>

            <input type = "text" value="${searchStr}">


        </center>

    </form>




</body>
</html>
