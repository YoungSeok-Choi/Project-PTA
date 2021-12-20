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

	<DIV class='title_line'> <i>${memberVO.name }</i> 님 카테고리 조회 </DIV>
	
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
	      <TH class="th_bs">카테고리<br> 이름 </TH>
	      <TH class="th_bs">사진개수</TH>
	      <TH class="th_bs">생성일자</TH>
	      <TH class="th_bs">기타</TH>
	    </TR>
	    </thead>
	    
	    <tbody>
	    <c:forEach var="category" items="${categories }">
	      <c:set var="name" value="${category.name }" />
	      <c:set var="cnt" value="${category.cnt }" />
	      <c:set var="cdate" value="${category.cdate }" />
	      <c:set var="member_id" value="${member.member_id}"/> <!-- redundant value -->
	      <c:set var="cate_id" value="${category.cate_id }"></c:set>
	      
	      <TR>
	        <TD class="td_bs"><a href="/list_by_cate_id?cate_id=${cate_id }">${name }</a></TD>
	        <TD class="td_bs">${cnt }</TD>
	        <TD class="td_bs_left">${cdate }</TD>
	        <TD class="td_bs">
	          <A href="/category/read_update?cate_id=${cate_id }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
	          <A href="/category/read_delete?cate_id=${cate_id }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
              <br>
              <a href="/picture?cate_id=${cate_id }" class="btn btn-default">사진등록</a>
	          <%-- <button type="button" class="btn"><a href="/category?member_id=${member_id }">카테고리 생성</a></button> --%>
	        </TD>  	      
	      </TR>   
	    </c:forEach> 
	    </tbody>
	   
	  </TABLE>
	</DIV>

</body>
</html>