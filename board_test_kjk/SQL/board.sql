drop Table board;

-- board
-- board_no
-- title = 제목
-- wrtier = 작성자
-- content = 내용
-- updated_at = 수정날짜
-- created_at = 등록날짜

CREATE TABLE `board` (
	`no`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY	COMMENT 'PK',
	`title`	VARCHAR(100)	NOT NULL	COMMENT '제목',
	`writer`	VARCHAR(100)	NOT NULL	COMMENT '작성자',
	`content`	TEXT	NULL	COMMENT '내용',
	`created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '수정일자'
);

-- 목록
SELECT * FROM board;

-- 등록
INSERT INTO board (title, writer, content)
VALUES ('제목2', '작성자2', '내용2');

-- 조회 no 기준 조회
SELECT * FROM board
WHERE no = 1; 

-- 수정
UPDATE board
SET title = '수정제목',
writer = '수정작성자',
content = '수정내용'
WHERE no = 1;

-- 삭제
DELETE FROM board
WHERE no = 1;