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

	<DIV class='title_line'>회원정보 > ${memberVO.name } 삭제</DIV>
	
	<DIV class='content_body'>
	  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
	    <div class="msg_warning">회원정보를 삭제하면 복구 할 수 없습니다.</div>
	    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
	      <input type='hidden' name='member_id' id='member_id' value='${memberVO.member_id }'>
	        
	      <label>회원 이름</label>: ${memberVO.name }  
	      <label>아이디</label>: ${memberVO.id }   
	      <label>주소</label>: ${memberVO.address }  
	       
	      <button type="submit" id='submit'>삭제</button>
	      <button type="button" onclick="location.href='./members'">취소</button>
	    </FORM>
	  </DIV>
	   
	  <TABLE class='table table-striped'>
	    <colgroup>
	      <col style='width: 10%;'/>
	      <col style='width: 40%;'/>
	      <col style='width: 20%;'/>
	      <col style='width: 10%;'/>    
	      <col style='width: 20%;'/>
	    </colgroup>
	   
	    <thead>  
	    <TR>
	      <TH class="th_bs">성명</TH>
	      <TH class="th_bs">id</TH>
	      <TH class="th_bs">주소</TH>
	      <TH class="th_bs">기타</TH>
	    </TR>
	    </thead>
	    
	    <tbody>
	    <c:forEach var="memberVO" items="${members }">
	      <c:set var="member_id" value="${memberVO.member_id }" />
	      <TR>
	        <TD class="td_bs">${memberVO.name }</TD>
	        <TD class="td_bs_left">${memberVO.id }</TD>
	        <TD class="td_bs">${memberVO.address }</TD>
	        <TD class="td_bs">
	          <A href="./read_update.do?member_id=${member_id }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
	          <A href="./read_delete.do?member_id=${member_id }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>         
	         </TD>   
	  
	      </TR>   
	    </c:forEach> 
	    </tbody>
	   
	  </TABLE>
	</DIV> 

</body>
</html>