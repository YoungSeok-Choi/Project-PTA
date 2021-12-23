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
	<h1 style="text-align:center">Studio Ghibli 회원가입</h1>
	<br>
	
    <div class="container-fluid">
    	<div class="row">
    		<div class="col-sm-4"></div>
    		<div class="col-sm-4">
    			<form name='frm' method='POST' action='/member' class="">
			        <div class="form-group">
			           <label class="control-label"> 회원이름 </label>
			           <input type='text' name='name' required="required" placeholder="이름을 입력하세요." autofocus="autofocus" class="form-control">
			        </div>
			        <div class="form-group">
			           <label class="control-label"> 회원 아이디 </label>
			           <input type='text' name='id' required="required" placeholder="아이디를 입력하세요." class="form-control">
			        </div>  
			        <div class="form-group">
			           <label class="control-label"> 비밀번호 </label>
			           <!--* 수정사항: 못해도 20자리 까지? -->
			           <input type='password' name='passwd' required="required" placeholder="영문, 숫자 조합 20자리 이하"
			                    class="form-control">
			        </div>
			        <div class="form-group">
			           <label class="control-label"> 주소 </label>
			           <input type='text' name='address' required="required" placeholder="주소를 입력하세요." class="form-control" >
			        </div>  
			      
			        <div style="float:right">
			          <button type="submit" class="btn btn-info">등록</button>
			          <button type="button" class="btn" onclick="location.href='/members'">목록으로</button>
			        </div>
			      
			      </form>
    		</div>
    		<div class="col-sm-4"></div>
      	</div>
    </div>

</body>
</html>