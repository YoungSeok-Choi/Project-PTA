<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Studio Ghibli</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
 
<DIV class='title_line'>
  <A href="/categories?member_id=${categoryVO.member_id }" class='title_link'>카테고리 그룹</A> > ${categoryVO.name }
  <%-- <A href="/list_by_cate_id.do?cate_id=${categoryVO.cate_id }" class='title_link'></A> --%>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="/picture?cate_id=${categoryVO.cate_id }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="/list_by_cate_id_grid1.do?cate_id=${categoryVO.cate_id }">갤러리형</A>
  </ASIDE> 
    <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='/list_by_name_search'>
      <input type='hidden' name='cate_id' value='${categoryVO.cate_id }'>
      <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list_by_cate_id_search_paging.do?cate_id=${categoryVO.cate_id}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>

    <tbody>
      <c:forEach var="pictureVO" items="${list }">
        <c:set var="picture_id" value="${pictureVO.picture_id }" />
        <c:set var="thumb1" value="${pictureVO.thumb1 }" />
        
        <tr> 
          <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                <%-- /static/contents/storage/ --%>
                <IMG src="/contents/storage/${thumb1 }" style="width: 120px; height: 80px;"> 
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                ${pictureVO.file1}
              </c:otherwise>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'>
            <a href="/read.do?picture_id=${picture_id}"><strong>${pictureVO.title}</strong> ${pictureVO.content}</a> 
          </td> 

          </td>
          <td style='vertical-align: middle; text-align: center;'>수정/삭제<br>사진 정보</td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
</DIV>

</body>
 
</html>