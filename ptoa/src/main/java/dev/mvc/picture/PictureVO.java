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

  /*  ���� �߰� ���ɼ� �ִ� �Ӽ���
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
    
          /** ������ ��ȣ */
          private int picture_id;
          /** ������ ��ȣ */
          private int admin_id;
          /** ī�װ� ��ȣ*/
          private int cate_id;
          /** ���� */
          private String title = "";
          /** ���� */
          private String content = "";
          /** ��õ�� */
          private int recom;
          /** ��ȸ�� */
          private int cnt = 0;
          /** ��ۼ� */
          private int replycnt = 0;
          /** �н����� */
          private String passwd = "";
          /** �˻��� */
          private String word = "";
          /** ��� ��¥ */
          private String rdate = "";
          
          /** ���� �̹��� */
          private String file1 = "";
          /** ���� ����� ���� �̹��� */
          private String file1saved = "";  
          /** ���� �̹��� preview */
          private String thumb1 = "";
          /** ���� �̹��� ũ�� */
          private long size1;

          
          /**
           * �����Է¿� ��ü
           * name�Ӽ��� �̸��� �ڹ� Ŭ������ ��ü���� ��Ȯ�ϰ� ��ġ�ؾ���. �׷��������� ���ε����� �߻�
           * <input type='file' class="form-control" name='file1MF' id='file1MF' 
           *    value='' placeholder="���� ����">
           * 
           */
          private MultipartFile file1MF;
          
          /**
           * ���� ũ�� ���� ���
           */
          private String size1_label;
          
          // �����ھ�?
          
  
}
