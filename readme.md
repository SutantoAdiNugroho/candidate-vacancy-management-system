# Candidate and Vacancy Management API

## This project is candidate and vacancy management system built using Spring Boot 3.5.6 and MongoDB part of technical test Jobseeker Company

## How to run this project?

### Prerequisites
* Java Development Kit (JDK) version 17 or higher
* Maven version 3.8.5 or higher
* Docker and Docker Compose (optional, if you want to run MongoDB using Docker)
* MongoDB (optional, if not using Docker Compose for database)

### Setup
1. Clone the repository:
```bash
git clone https://github.com/SutantoAdiNugroho/candidate-vacancy-management-system.git
```
2. Configure database:
* If don't have mongodb instance installed on local computer, we can run docker compose command below</p>
```bash
docker-compose up
```
* Open `src/main/resources/application.properties` and update MongoDB connection
```console
spring.data.mongodb.uri=mongodb://localhost:27017/db_candidate_management
```
3. Run application:
```bash
mvn spring-boot:run
```
The API will be available at http://localhost:8080

### API
Following API endpoints are available for interaction with backend

#### Candidate API
* `POST /api/candidates` - Create new candidate
* `GET /api/candidates` - Fetch list of candidates
* `GET /api/candidates/:id` - Fetch a specific candidate by it's ID
* `PUT /api/candidates/:id` - Modify existing candidate
* `DELETE /api/candidates/:id` - Delete a candidate

#### Vacancy API
* `POST /api/vacancies` - Create new vacancy with it's criteria
* `GET /api/vacancies` - Fetch list of vacancies
* `GET /api/vacancies/:id` - Fetch a specific vacancy by it's ID
* `PUT /api/vacancies/:id` - Modify existing vacancy
* `DELETE /api/vacancies/:id` - Delete a vacancy
* `GET /api/vacancies/:id/ranked` - Ranks candidates for specific vacancy

Full details of API documentation can be found at [here](https://documenter.getpostman.com/view/9584185/2sB3BDJAWy#intro)
