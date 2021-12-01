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

    <div style="text-align: center;">
            <h1>신규 회원가입</h1>
    </div>
	
    <DIV class='content_body'>
      <FORM name='frm' method='POST' action='/member' class="form-horizontal">
        <div class="form-group">
           <label class="control-label col-md-4"> 회원이름 </label>
           <div class="col-md-8">
             <input type='text' name='name' value='' required="required" placeholder="이름"
                        autofocus="autofocus" class="form-control" style='width: 50%;'>
           </div>
        </div>
        <div class="form-group">
           <label class="control-label col-md-4"> 회원 아이디 </label>
           <div class="col-md-8">
             <input type='text' name='id' value='333' required="required" placeholder="아이디"
                         class="form-control" style='width: 50%;'>
           </div>
        </div>  
        <div class="form-group">
           <label class="control-label col-md-4"> 비밀번호 </label>
           <div class="col-md-8">
             <input type='password' name='passwd' value='444' required="required" placeholder="영문 + 숫자 10자리 이하"
                    class="form-control" style='width: 50%;'>
           </div>
        </div>
        <div class="form-group">
           <label class="control-label col-md-4"> 주소 </label>
           <div class="col-md-8">
             <input type='text' name='address' value='555' required="required" placeholder="주소"
                    class="form-control" style='width: 50%;'>
           </div>
        </div>  
      
        <div class="content_body_bottom" style="padding-right: 50%;">
          <button type="submit" class="btn">등록</button>
          <button type="button" onclick="location.href='/members'" class="btn">목록</button>
        </div>
      
      </FORM> 
    </DIV>

</body>
</html>