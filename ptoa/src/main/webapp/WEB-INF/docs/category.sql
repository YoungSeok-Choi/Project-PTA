/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		member_id                     		NUMBER(10)		 DEFAULT 0		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(20)		 NOT NULL,
		ident                         		VARCHAR2(30)		 NOT NULL,
		passwd                        		VARCHAR2(20)		 NOT NULL,
		address                       		VARCHAR2(100)		 NOT NULL
);

CREATE SEQUENCE member_member_id_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER member_member_id_TRG
BEFORE INSERT ON member
FOR EACH ROW
BEGIN
IF :NEW.member_id IS NOT NULL THEN
  SELECT member_member_id_SEQ.NEXTVAL INTO :NEW.member_id FROM DUAL;
END IF;
END;

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.member_id is '회원번호';
COMMENT ON COLUMN member.name is '회원이름';
COMMENT ON COLUMN member.ident is '회원아이디';
COMMENT ON COLUMN member.passwd is '비밀번호';
COMMENT ON COLUMN member.address is '회원주소';


/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE Category(
		cate_id                       		NUMBER(10)		 DEFAULT 0		 NOT NULL		 PRIMARY KEY,
		cnt                           		NUMBER(10)		 DEFAULT 0		 NULL ,
		name                          		VARCHAR2(30)		 NOT NULL,
		date                          		DATE		 NOT NULL,
		member_id                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE SEQUENCE Category_cate_id_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Category_cate_id_TRG
BEFORE INSERT ON Category
FOR EACH ROW
BEGIN
IF :NEW.cate_id IS NOT NULL THEN
  SELECT Category_cate_id_SEQ.NEXTVAL INTO :NEW.cate_id FROM DUAL;
END IF;
END;

COMMENT ON TABLE Category is '카테고리';
COMMENT ON COLUMN Category.cate_id is '카테고리_id';
COMMENT ON COLUMN Category.cnt is '사진개수';
COMMENT ON COLUMN Category.name is '카테고리이름';
COMMENT ON COLUMN Category.date is '생성일자';
COMMENT ON COLUMN Category.member_id is '회원번호';


