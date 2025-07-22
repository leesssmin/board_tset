-- src/main/resources/db/data.sql

INSERT INTO board_tb (title, content, username, created_at) VALUES
('Spring Boot 시작하기', 'Spring Boot를 이용한 웹 애플리케이션 개발 가이드입니다.', '홍길동', NOW()),
('JPA와 Hibernate 이해', 'JPA와 Hibernate를 활용한 데이터베이스 연동 방법을 소개합니다.', '이몽룡', NOW()),
('REST API 설계 원칙', '효율적인 REST API 설계를 위한 주요 원칙과 베스트 프랙티스를 설명합니다.', '성춘향', NOW()),
('Mustache 템플릿 사용법', 'Mustache 템플릿 엔진의 기본 사용법과 적용 방법에 대해 다룹니다.', '임꺽정', NOW()),
('Spring Security 기초', 'Spring Security를 통해 간단한 인증 및 인가 기능 구현하기.', '장보고', NOW()),
('MySQL 데이터베이스 최적화', 'MySQL 성능 튜닝과 인덱스 활용 전략을 안내합니다.', '김유신', NOW()),
('Jenkins를 이용한 CI/CD 구축', 'Jenkins로 자동 빌드와 배포 파이프라인을 만드는 방법.', '유관순', NOW()),
('Docker 컨테이너 입문', 'Docker 기본 개념과 컨테이너 생성, 관리 방법을 소개합니다.', '안중근', NOW()),
('Vue.js 시작하기', 'Vue.js 프레임워크의 기본 개념과 프로젝트 설정 방법.', '신사임당', NOW()),
('Java 17 신기능', 'Java 17에서 추가된 주요 기능과 개선 사항 소개.', '이순신', NOW()),
('JUnit5로 단위 테스트 작성하기', 'JUnit5를 활용한 효과적인 단위 테스트 작성법.', '윤봉길', NOW()),
('Kafka 메시징 시스템 개요', 'Kafka의 아키텍처와 메시징 패턴을 살펴봅니다.', '정약용', NOW()),
('AWS EC2 인스턴스 관리', 'AWS EC2 인스턴스 생성부터 운영까지의 실무 가이드.', '김구', NOW()),
('React Hooks 활용법', 'React Hooks를 이용해 컴포넌트 상태 관리와 라이프사이클 다루기.', '유관순', NOW()),
('API 문서 자동화 도구 Swagger', 'Swagger를 활용한 API 문서 자동화와 테스트 방법.', '이황', NOW());

