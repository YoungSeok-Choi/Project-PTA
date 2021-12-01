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

	<DIV class='title_line'> ${memberVO.name }님 > ${categoryVO.name } 조회(수정)</DIV>
	<DIV class='content_body'>
	  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
	    <FORM name='frm_update' id='frm_update' method='POST' action='/category/update.do'>
	    <!-- 여기서 이름 속성이 같아야 값이 바인딩? -->
	      <input type="hidden" name="cate_id" value="${categoryVO.cate_id }">
	        
	      <label>카테고리 이름</label>
	      <input type='text' name='name' value="${categoryVO.name }" required="required" 
	                 autofocus="autofocus" style='width: 10%;'>
	                 
	      <button type="submit" id='submit'>저장</button>
	      <button type="button" onclick="location.href='/members'">취소</button>
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
	      <TH class="th_bs">카테고리<br> 이름 </TH>
	      <TH class="th_bs">사진개수</TH>
	      <TH class="th_bs">생성일자</TH>
	      <TH class="th_bs">기타</TH>
	    </TR>
	    </thead>
	    
	    <tbody>
	    <c:forEach var="category" items="${categories }">
	      <c:set var="name" value="${categoryVO.name }" />
	      <c:set var="cnt" value="${categoryVO.cnt }" />
	      <c:set var="cdate" value="${categoryVO.cdate }" />
	      <c:set var="member_id" value="${memberVO.member_id}"/> <!-- redundant value -->
	      <c:set var="cate_id" value="${categoryVO.cate_id }"></c:set>
	      
	      <TR>
	        <TD class="td_bs"><a href="">${name }</a></TD>
	        <TD class="td_bs">${cnt }</TD>
	        <TD class="td_bs_left">${cdate }</TD>
	        <TD class="td_bs">
	          <A href="/category/read_update?cate_id=${cate_id }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
	          <A href="/category/read_delete?cate_id=${cate_id }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
	          <%-- <button type="button" class="btn"><a href="./category?member_id=${member_id }">카테고리 생성</a></button> --%>
	        </TD>  	      
	      </TR>   
	    </c:forEach> 
	    </tbody>
	   
	  </TABLE>
	</DIV>

</body>
</html>