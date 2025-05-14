-- 'plannerd' 스키마 사용
USE plannerd;

-- schedule 테이블 생성
CREATE TABLE schedule (
  id INT PRIMARY KEY AUTO_INCREMENT,  -- id는 기본키, 자동 증가
  user VARCHAR(100) NOT NULL,          -- user는 문자열로 100자 제한
  task VARCHAR(255) NOT NULL,          -- task는 문자열로 255자 제한
  password VARCHAR(255) NOT NULL,      -- password는 문자열로 255자 제한
  created DATETIME DEFAULT CURRENT_TIMESTAMP,  -- created는 현재 시각으로 기본값 설정
  updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- updated는 변경 시점으로 자동 갱신
);
