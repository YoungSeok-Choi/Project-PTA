<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Studio Ghibli</title>
 
<link href="./css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<style type="text/css">
    
    body {background-color: lightblue;}
    
</style>
</head>


<body>
    <div style="text-align: center;">
        <h1>Studio Ghibli</h1> 
        <br><br>
        <a href="/member" >
        	<button class="btn btn-primary">회원 등록</button>
        </a>
        <br><br>
        <a href="/members" >
        	<button class="btn btn-info">회원 조회</button>
        </a>
        <br><br>
        <a href="/member/login">
        	<button class="btn btn-info">로그인</button>
        </a>
 </div>
	<br> 
    <div style="text-align: center;">
         <h1>* 사이트 사용방법 * {Ver. 1.0}</h1><br> <br> 
      
        <ol>
            <li>1. 회원가입 하기.</li>
            <li>2. 회원 별 카테고리 생성하기 (풍경, 여행 등등...)</li>
            <li>3. 만들어진 카테고리에 사진 + 게시글을 작성하여 저장하기.</li>
        </ol>     
    </div>
	
</body>
</html>