
다음의 스키마를 참고해서 restapi를 작성해줘. 

```sql
-- Create the student table
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL
);

-- Create the student_grades table
CREATE TABLE student_grades (
    student_id INT NOT NULL,
    subject VARCHAR(20) NOT NULL,
    grade INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);
```
---


제공해주신 데이터베이스 스키마(`student`, `student_grades`)를 기반으로 설계한 **REST API 명세**입니다.

직관적이고 표준적인 RESTful 규칙을 따라 엔드포인트를 구성했습니다. 학생(`students`)은 독립적인 자원(Resource)이고, 성적(`grades`)은 특정 학생에 종속된 하위 자원이므로 URL 경로를 `/students/{student_id}/grades` 형태로 설계하는 것이 가장 깔끔합니다.

---

## 1. 학생 (Student) 관련 API

### 👥 학생 등록 (Create)

* **Method:** `POST`
* **URL:** `/api/v1/students`
* **Request Body (JSON):**
```json
{
  "student_id": 101,
  "name": "홍길동",
  "age": 20
}

```


* **Response (201 Created):**
```json
{
  "message": "학생 정보가 성공적으로 등록되었습니다.",
  "student_id": 101
}

```



### 👥 전체 학생 조회 (Read All)

* **Method:** `GET`
* **URL:** `/api/v1/students`
* **Response (200 OK):**
```json
[
  { "student_id": 101, "name": "홍길동", "age": 20 },
  { "student_id": 102, "name": "이순신", "age": 22 }
]

```



### 👥 특정 학생 조회 (Read One)

* **Method:** `GET`
* **URL:** `/api/v1/students/{student_id}` (예: `/api/v1/students/101`)
* **Response (200 OK):**
```json
{
  "student_id": 101,
  "name": "홍길동",
  "age": 20
}

```



### 👥 학생 정보 수정 (Update)

* **Method:** `PUT`
* **URL:** `/api/v1/students/{student_id}`
* **Request Body (JSON):**
```json
{
  "name": "홍길동",
  "age": 21
}

```


* **Response (200 OK):**
```json
{
  "message": "학생 정보가 수정되었습니다."
}

```



### 👥 학생 정보 삭제 (Delete)

* **Method:** `DELETE`
* **URL:** `/api/v1/students/{student_id}`
* **Response (200 OK / 204 No Content):**
```json
{
  "message": "학생 정보가 삭제되었습니다."
}

```



---

## 2. 성적 (Student Grades) 관련 API

### 📝 특정 학생의 성적 등록 (Create)

* **Method:** `POST`
* **URL:** `/api/v1/students/{student_id}/grades` (예: `/api/v1/students/101/grades`)
* **Request Body (JSON):**
```json
{
  "subject": "Math",
  "grade": 95
}

```


* **Response (201 Created):**
```json
{
  "message": "성적이 등록되었습니다.",
  "student_id": 101,
  "subject": "Math"
}

```



### 📝 특정 학생의 전체 성적 조회 (Read)

* **Method:** `GET`
* **URL:** `/api/v1/students/{student_id}/grades`
* **Response (200 OK):**
```json
{
  "student_id": 101,
  "name": "홍길동",
  "grades": [
    { "subject": "Math", "grade": 95 },
    { "subject": "English", "grade": 88 }
  ]
}

```



### 📝 특정 학생의 특정 과목 성적 수정 (Update)

* **Method:** `PUT`
* **URL:** `/api/v1/students/{student_id}/grades/{subject}` (예: `/api/v1/students/101/grades/Math`)
* **Request Body (JSON):**
```json
{
  "grade": 98
}

```


* **Response (200 OK):**
```json
{
  "message": "성적이 수정되었습니다."
}

```



### 📝 특정 학생의 특정 과목 성적 삭제 (Delete)

* **Method:** `DELETE`
* **URL:** `/api/v1/students/{student_id}/grades/{subject}`
* **Response (200 OK):**
```json
{
  "message": "성적 데이터가 삭제되었습니다."
}

```



---

> **💡 설계 팁:**
> 현재 `student_grades` 테이블에는 별도의 기본키(PK)가 없고 `student_id`와 `subject`가 복합적으로 의미를 가집니다. 따라서 특정 과목의 성적을 수정하거나 삭제할 때는 URL 경로의 끝에 `/{subject}`를 붙여 식별하도록 설계했습니다. 만약 특정 백엔드 프레임워크(Spring Boot, Node.js, FastAPI 등)로 실제 코드 구현이 필요하시다면 말씀해 주세요!