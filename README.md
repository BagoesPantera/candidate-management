# Spring Boot REST API

This project implements a REST API built with Spring Boot and MongoDB. It supports candidate management, job vacancy management, and candidate ranking based on vacancy criteria.

## Requirements

- Java 20
- Gradle
- MongoDB (running locally or via MongoDB Atlas)
- IntelliJ IDEA (recommended)

## Setup

1. **Clone the repository**


2. **Run the project**
   ```bash
   ./gradlew bootRun
   ```

3. **API is available at**
   ```
   http://localhost:8080
   ```

## API Endpoints

### Candidate Management

| Method | Endpoint           | Description |
|--------|--------------------|-------------|
| POST | `/api/candidates`  | Create new candidate |
| GET | `/api/candidates`      | List all candidates |
| PUT | `/api/candidates/{id}` | Update candidate |
| DELETE | `/api/candidates/{id}` | Delete candidate |

### Vacancy Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/vacancies` | Create new vacancy |
| GET | `/api/vacancies` | List all vacancies |
| PUT | `/api/vacancies/{id}` | Update vacancy |
| DELETE | `/api/vacancies/{id}` | Delete vacancy |

### Candidate Ranking

| Method | Endpoint                                     | Description |
|--------|----------------------------------------------|-------------|
| GET | `/api/vacancies/{vacancyId}/rank-candidates` | Rank candidates for vacancy |

## Example Request Payloads

### Create Candidate

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "birthDate": "1990-01-01",
  "gender": "MALE",
  "currentSalary": 8000
}
```

### Create Vacancy

```json
{
  "name": "Backend Developer",
  "criteriaList": [
    {
      "type": "AGE",
      "minValue": 25,
      "maxValue": 35,
      "weight": 2
    },
    {
      "type": "GENDER",
      "value": "MALE",
      "weight": 1
    },
    {
      "type": "SALARY_RANGE",
      "minValue": 5000,
      "maxValue": 10000,
      "weight": 3
    }
  ]
}
```

## API Documentation

For detailed API documentation with examples and testing capabilities, visit our Postman collection:

**[View Complete API Documentation](https://documenter.getpostman.com/view/18748358/2sB3B8rswM)**

The Postman documentation includes:
- Complete request/response examples
- Interactive API testing
- Detailed parameter descriptions
- Error response examples
- Collection for easy import into Postman

## Notes

- Default MongoDB configuration uses `mongodb://localhost:27017/db-candidate-management` (can be changed in `application.properties`)
- Email must be unique when creating candidates
- Each vacancy must have at least one criterion