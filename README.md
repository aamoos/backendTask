## CHAT GPT 백엔드 과제

## 목차
1. [과제 1: 간단한 RESTful API 구현](#과제-1-간단한-restful-api-구현)
2. [과제 2: 사용자 인증 시스템](#과제-2-사용자-인증-시스템)
3. [과제 3: 데이터베이스 모델링 및 CRUD 작업](#과제-3-데이터베이스-모델링-및-crud-작업)
4. [과제 4: 간단한 게시판 시스템](#과제-4-간단한-게시판-시스템)
5. [과제 5: 시스템 성능 최적화](#과제-5-시스템-성능-최적화)
6. [과제 6: 비동기 작업 처리 시스템](#과제-6-비동기-작업-처리-시스템)
7. [과제 7: 실시간 채팅 애플리케이션](#과제-7-실시간-채팅-애플리케이션)
8. [과제 8: 로깅 및 에러 추적 시스템](#과제-8-로깅-및-에러-추적-시스템)

---

## 과제 1: 간단한 RESTful API 구현

### 설명:
다음의 RESTful API를 구현하시오:

- `GET /items`: 아이템 목록 조회
- `POST /items`: 새 아이템 생성
- `PUT /items/{id}`: 특정 아이템 수정
- `DELETE /items/{id}`: 특정 아이템 삭제

### 요구 사항:
- 간단한 메모리 저장소(예: JSON 파일 또는 메모리 DB)를 사용하여 아이템을 저장.
- 응답은 JSON 형식으로 반환.
- 각 요청에 대해 적절한 HTTP 상태 코드 반환.
- 각 아이템은 `id`, `name`, `description`을 포함하며, `id`는 자동 증가하는 값으로 설정.

---

### 정답:
- 간단한 메모리 저장소(예: JSON 파일 또는 메모리 DB)를 사용하여 아이템을 저장.

![image](https://github.com/user-attachments/assets/f37bc55b-df90-4eb5-8d96-6aa477e34453)

- 아이템 생성

![image](https://github.com/user-attachments/assets/d5b872e4-0c3b-487c-b1ea-3f5834939a8a)

- 아이템 전체 목록 조회
![image](https://github.com/user-attachments/assets/4cea9017-9c3e-4fbd-be11-2cc4a1aa13f3)

![image](https://github.com/user-attachments/assets/42b7dd08-4911-4074-8081-3d6d26a703c3)

![image](https://github.com/user-attachments/assets/e348da43-658f-41fc-973a-ff93032bbe40)

## 과제 2: 사용자 인증 시스템

### 설명:
다음의 기능을 가진 사용자 인증 시스템을 구현하시오:

- `POST /register`: 새 사용자 등록 (이메일, 비밀번호)
- `POST /login`: 로그인 (이메일, 비밀번호)
- `GET /profile`: 로그인한 사용자의 프로필 정보 조회

### 요구 사항:
- 비밀번호는 안전하게 해시화하여 저장.
- JWT를 사용하여 인증.
- 비밀번호는 최소 8자 이상, 대문자와 숫자를 포함해야 한다는 규칙을 검증.
- 각 요청에 대한 유효성 검증 필수.

## 정답:
- 비밀번호는 안전하게 해시화하여 저장.
![image](https://github.com/user-attachments/assets/226e9171-52e3-4414-b375-41f60a2f0433)

- JWT를 사용하여 인증.
![image](https://github.com/user-attachments/assets/87efe2b3-1e12-4a5f-9331-7ebd1ddda0b3)

- 비밀번호는 최소 8자 이상, 대문자와 숫자를 포함해야 한다는 규칙을 검증.
![image](https://github.com/user-attachments/assets/f71cab40-0bdd-418b-82a5-3c0ef47dd83e)

- 회원가입 정상적으로 요청시
![image](https://github.com/user-attachments/assets/00b66255-a99a-48a4-9c33-5fba2491e233)

- 회원가입 요청시 중복회원
![image](https://github.com/user-attachments/assets/3a686d71-3e44-403f-b413-6d8dcee3e316)

- 로그인 요청시 회원이 없을경우
![image](https://github.com/user-attachments/assets/ba1841da-d49a-400b-807c-3fcfdd2195f9)

- 로그인한 사용자의 프로필 조회
![image](https://github.com/user-attachments/assets/fbba3a41-7452-4396-b305-ffc109178054)

---



## 과제 3: 데이터베이스 모델링 및 CRUD 작업

### 설명:
주어진 요구 사항에 맞춰 데이터베이스 모델을 설계하고 CRUD 작업을 구현하시오. 예를 들어, 쇼핑몰 시스템에서 주문(Order)과 사용자(Member) 모델을 설계하시오.

- `Order`: 주문번호, 사용자ID, 상품명, 주문일자, 상태
- `Member`: 사용자ID, 이름, 이메일, 비밀번호

### 요구 사항:
- 모델 설계 및 CRUD 구현.
- 기본적인 데이터 유효성 검증.
- API에서 데이터를 JSON 형식으로 응답.

## 정답:

![image](https://github.com/user-attachments/assets/23e9363f-8d65-4299-98f7-5520c258ea00)

![image](https://github.com/user-attachments/assets/b5c73378-58f7-4f00-88c2-890190467d60)

![image](https://github.com/user-attachments/assets/d0c343fd-7f16-4065-8a25-e2b63d5589ef)

![image](https://github.com/user-attachments/assets/fc743212-7f94-4e6b-bcea-1c8e7dc7ca5d)

![image](https://github.com/user-attachments/assets/3e4875f2-a301-46d6-a38d-3fd9505183b9)

![image](https://github.com/user-attachments/assets/5fceeeb5-1b80-4cf3-8d71-530d4a70a3fe)







---

## 과제 4: 간단한 게시판 시스템

### 설명:
다음 기능을 갖춘 간단한 게시판 시스템을 구현하시오:

- `GET /posts`: 게시물 목록 조회
- `POST /posts`: 게시물 작성
- `GET /posts/{id}`: 게시물 상세 조회
- `PUT /posts/{id}`: 게시물 수정
- `DELETE /posts/{id}`: 게시물 삭제

### 요구 사항:
- 게시물은 제목, 내용, 작성자, 작성일을 포함.
- 로그인한 사용자만 게시물을 작성, 수정, 삭제할 수 있도록 권한 관리.
- 게시물 목록 조회 시 페이징 처리 구현.

---

## 과제 5: 시스템 성능 최적화

### 설명:
특정 API의 성능이 떨어진다고 가정하고, 이를 최적화하시오. 예를 들어, `GET /orders` API가 느리다면, 성능을 개선할 방법을 제시하고 코드로 구현하시오.

### 요구 사항:
- Redis 등의 캐싱 기법을 사용하여 성능을 향상시킬 것.
- SQL 쿼리 최적화 (예: 인덱스 추가, 복잡한 쿼리 리팩토링).
- 부하 분산 기법을 적용할 것 (예: 로드 밸런서 사용).
- 성능 측정 및 개선된 성능을 비교하는 벤치마킹 구현.

---

## 과제 6: 비동기 작업 처리 시스템

### 설명:
사용자가 요청을 보내면 비동기적으로 처리해야 하는 작업을 처리하는 시스템을 구현하시오. 예를 들어, `POST /send-email` 요청을 받으면 이메일을 비동기적으로 발송하는 시스템을 구현하시오.

### 요구 사항:
- RabbitMQ, Kafka 또는 워커 시스템을 사용하여 비동기 작업 처리.
- 요청 후 즉시 응답을 보내고, 이메일 발송 작업은 백그라운드에서 처리.
- 작업 처리 후 결과를 DB에 기록하고 사용자가 결과를 추후 확인할 수 있도록 구현.

---

## 과제 7: 실시간 채팅 애플리케이션

### 설명:
실시간 채팅 애플리케이션을 개발하시오. 기본적인 채팅 기능을 제공하며, 채팅방 생성, 메시지 전송, 실시간 메시지 수신 기능을 구현하시오.

### 요구 사항:
- WebSocket 또는 Server-Sent Events(SSE)를 사용하여 실시간 메시지 처리.
- 채팅방 생성 및 메시지 기록을 DB에 저장.
- 사용자 인증 및 권한 관리 (로그인, 채팅방 접근 제어).

---

## 과제 8: 로깅 및 에러 추적 시스템

### 설명:
시스템에서 발생하는 로그를 수집하고 에러가 발생할 경우 이를 추적할 수 있는 시스템을 개발하시오.

### 요구 사항:
- ELK 스택, Splunk 등의 중앙 집중식 로그 수집 시스템 사용.
- 에러 발생 시 Slack, 이메일, SMS 등으로 알림을 보낼 것.
- 로그 레벨 관리 (INFO, WARN, ERROR 등).
- 실시간으로 로그를 조회하고 분석할 수 있는 대시보드 제공.

---
