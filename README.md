# Clinical Staff Administration System

A RESTful API for managing healthcare staff (Doctors & Receptionists) built with JAX-RS and deployed on Apache Tomcat.

---

## Technologies Used

- Java 17
- JAX-RS (Jersey 2.41)
- Jackson (JSON)
- Apache Tomcat (via Maven plugin)
- JUnit 5

---

## Project Structure

```
src/
├── main/
│   ├── java/com/healthcentre/
│   │   ├── config/         HealthCentreApplication.java
│   │   ├── exception/      CustomException, DuplicateIdException,
│   │   │                   InvalidInputException, StaffNotFoundException
│   │   ├── model/          StaffMember (abstract), Doctor, Receptionist
│   │   ├── resource/       StaffResource.java  ← REST endpoints
│   │   └── service/        StaffService.java   ← business logic
│   └── webapp/WEB-INF/
│       └── web.xml
└── test/
    └── java/com/healthcentre/
        └── StaffServiceTest.java  ← 12 JUnit 5 tests
```

---

## Prerequisites

- Java 17+
- Maven 3.6+

Check you have both:
```bash
java -version
mvn -version
```

---

## How to Run

```bash
cd Health-Centre-Management-System
mvn clean package
mvn tomcat7:run
```

Server starts at: **http://localhost:8080**

---

## API Endpoints

Base URL: `http://localhost:8080/api/staff`

| Method   | Endpoint                    | Description                  |
|----------|-----------------------------|------------------------------|
| GET      | `/api/staff`                | Get all staff                |
| GET      | `/api/staff/{id}`           | Get staff member by ID       |
| GET      | `/api/staff/sorted`         | Get all staff sorted by name |
| GET      | `/api/staff/statistics`     | Get statistics               |
| POST     | `/api/staff/doctor`         | Add a new doctor             |
| POST     | `/api/staff/receptionist`   | Add a new receptionist       |
| DELETE   | `/api/staff/{id}`           | Remove a staff member        |

---

## Testing with Postman

### 1. Add a Doctor
**POST** `http://localhost:8080/api/staff/doctor`

Headers:
```
Content-Type: application/json
```

Body:
```json
{
  "staffId": "D001",
  "name": "John",
  "surName": "Smith",
  "dob": "1985-06-15",
  "phoneNo": "07700900001",
  "licenceNumber": "LIC-001",
  "specialization": "Cardiology",
  "numberOfConsultationPerWeek": 20
}
```

Expected response (201 Created):
```json
{
  "staffId": "D001",
  "name": "John",
  "surName": "Smith",
  "dob": "1985-06-15",
  "phoneNo": "07700900001",
  "licenceNumber": "LIC-001",
  "specialization": "Cardiology",
  "numberOfConsultationPerWeek": 20,
  "role": "Doctor"
}
```

---

### 2. Add a Receptionist
**POST** `http://localhost:8080/api/staff/receptionist`

Body:
```json
{
  "staffId": "R001",
  "name": "Jane",
  "surName": "Doe",
  "dob": "1990-03-20",
  "phoneNo": "07700900002",
  "deskNumber": 5,
  "hoursPerWeek": 40
}
```

Expected response (201 Created):
```json
{
  "staffId": "R001",
  "name": "Jane",
  "surName": "Doe",
  "role": "Receptionist",
  "deskNumber": 5,
  "hoursPerWeek": 40
}
```

---

### 3. Get All Staff
**GET** `http://localhost:8080/api/staff`

Expected response (200 OK):
```json
[
  { "staffId": "D001", "name": "John", "role": "Doctor", ... },
  { "staffId": "R001", "name": "Jane", "role": "Receptionist", ... }
]
```

---

### 4. Get Staff by ID
**GET** `http://localhost:8080/api/staff/D001`

Expected response (200 OK) — returns the Doctor object.

If not found (404):
```json
{ "error": "Staff member with ID 'D001' not found." }
```

---

### 5. Get Staff Sorted by Name
**GET** `http://localhost:8080/api/staff/sorted`

Returns all staff sorted alphabetically by surname, then first name.

---

### 6. Get Statistics
**GET** `http://localhost:8080/api/staff/statistics`

Expected response (200 OK):
```json
{
  "totalStaff": 2,
  "totalDoctors": 1,
  "totalReceptionists": 1,
  "doctorPercentage": 50.0,
  "receptionistPercentage": 50.0,
  "avgConsultationsPerDoctorPerWeek": 20.0,
  "avgHoursPerReceptionistPerWeek": 40.0,
  "staffLimit": 50
}
```

---

### 7. Delete a Staff Member
**DELETE** `http://localhost:8080/api/staff/D001`

Expected response (200 OK) — returns the removed staff object.

If not found (404):
```json
{ "error": "Staff member with ID 'D001' not found." }
```

---

## Run JUnit Tests

```bash
mvn test
```

Expected output:
```
Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Validation Rules

| Field                      | Rule                          |
|----------------------------|-------------------------------|
| staffId                    | Must not be empty             |
| name / surName             | Must not be empty             |
| dob                        | Format: YYYY-MM-DD            |
| phoneNo                    | Must not be empty             |
| licenceNumber              | Must not be empty (Doctor)    |
| specialization             | Must not be empty (Doctor)    |
| numberOfConsultationPerWeek| 0 – 100 (Doctor)              |
| deskNumber                 | 1 – 999 (Receptionist)        |
| hoursPerWeek               | 1 – 80 (Receptionist)         |

---

## Error Responses

| Status | Meaning                        |
|--------|--------------------------------|
| 200    | Success                        |
| 201    | Created successfully           |
| 400    | Bad request / validation error |
| 404    | Staff member not found         |
