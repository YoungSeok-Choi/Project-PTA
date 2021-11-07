package dev.mvc.picture;

/*
pictureno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
adminno                              NUMBER(10)     NOT NULL ,
cateno                                NUMBER(10)         NOT NULL ,
title                                 VARCHAR2(300)         NOT NULL,
content                               CLOB                  NOT NULL,
recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
passwd                                VARCHAR2(15)         NOT NULL,
word                                  VARCHAR2(300)         NULL ,
rdate                                 DATE               NOT NULL,
file1                                   VARCHAR(100)          NULL,
file1saved                            VARCHAR(100)          NULL,
thumb1                              VARCHAR(100)          NULL,
size1                                 NUMBER(10)      DEFAULT 0 NULL,  

  /*  추후 추가 가능성 있는 속성들
   private int price;
   private int dc; 
   private int saleprice;
   private int point;
   private int salecnt;
   * */

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PictureVO {
    
          /** 컨텐츠 번호 */
          private int picture_id;
          /** 관리자 번호 */
          private int admin_id;
          /** 카테고리 번호*/
          private int cate_id;
          /** 제목 */
          private String title = "";
          /** 내용 */
          private String content = "";
          /** 추천수 */
          private int recom;
          /** 조회수 */
          private int cnt = 0;
          /** 댓글수 */
          private int replycnt = 0;
          /** 패스워드 */
          private String passwd = "";
          /** 검색어 */
          private String word = "";
          /** 등록 날짜 */
          private String rdate = "";
          
          /** 메인 이미지 */
          private String file1 = "";
          /** 실제 저장된 메인 이미지 */
          private String file1saved = "";  
          /** 메인 이미지 preview */
          private String thumb1 = "";
          /** 메인 이미지 크기 */
          private long size1;

          
          /**
           * 파일입력용 객체
           * name속성의 이름과 자바 클래스의 객체명이 정확하게 일치해야함. 그렇지않으면 바인딩오류 발생
           * <input type='file' class="form-control" name='file1MF' id='file1MF' 
           *    value='' placeholder="파일 선택">
           * 
           */
          private MultipartFile file1MF;
          
          /**
           * 파일 크기 단위 출력
           */
          private String size1_label;
          
          // 생성자없?
          
  
}
