# hospital-visitor
병원 방문 후기 등록 및 조회 서비스

### Stack
Sprint Boot, JPA, Spring Security, JWT Token

### End point
병원 목록 조회 : GET /api/v1/hospital <br>
병원별 리뷰 조회 : GET /api/v1/hospital/{병원id} <br>
병원 리뷰 전체 조회 : GET /api/v1/hospital/reviews <br>
병원 리뷰 단건 조회 : GET /api/v1/hospital/reviews/{리뷰id} <br>
병원 리뷰 등록 : POST /api/v1/hospital/reviews <br>


### 호출주소
---
http://ec2-18-182-28-139.ap-northeast-1.compute.amazonaws.com:8081/api/v1/book
[현재 배포 중지]
