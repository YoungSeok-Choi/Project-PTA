<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Studio Ghibli</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>

<body>

	<DIV class='title_line'> 실시간 회원조회 </DIV>
	
	<DIV class='content_body'>
	  <TABLE class='table table-striped'>
	    <colgroup>
	      <col style='width: 10%;'/>
	      <col style='width: 10%;'/>
	      <col style='width: 35%;'/>
	      <col style='width: 15%;'/>    
	      <col style='width: 10%;'/>
	      <col style='width: 20%;'/>
	    </colgroup>
	   
	    <thead>  
	    <TR>
	      <TH class="th_bs">회원이름<br> 성명 </TH>
	      <TH class="th_bs">회원 아이디<br> id</TH>
	      <TH class="th_bs">회원 주소</TH>
	      <TH class="th_bs">기타</TH>
	    </TR>
	    </thead>
	    
	    <tbody> 
	    <c:forEach var="member" items="${members}">
	      <c:set var="name" value="${member.name }" />
	      <c:set var="id" value="${member.id }" />
	      <c:set var="address" value="${member.address }" />
	      <c:set var="member_id" value="${member.member_id}"/>
	      
	      <TR>
	        <TD class="td_bs"><a href="/categories?member_id=${member_id }">${name }</a></TD>
	        <TD class="td_bs">${id }</TD>
	        <TD class="td_bs_left">${address }</TD>
	        <TD class="td_bs">
	          <A href="./read_update.do?member_id=${member_id }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
	          <A href="./read_delete.do?member_id=${member_id }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
	          <button type="button" class="btn"><a href="/category?member_id=${member_id }">카테고리 생성</a></button>
	        </TD>  	      
	      </TR>   
	    </c:forEach> 
	    </tbody>
	   
	  </TABLE>
	</DIV>
    <hr>
    
    <div style="text-align:left;">
        <a href="/"  class="btn btn-info">메인페이지</a>
    </div>
</body>
</html>