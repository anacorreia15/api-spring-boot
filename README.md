# Voll.med API – Spring Boot

REST API developed for the management of a fictional medical clinic, allowing the registration of doctors and patients, as well as the scheduling and cancellation of appointments.
This project is part of a course from Alura. The original idea is not mine, but I implemented and developed the code myself as part of the learning process.

## 💡 Overview

This is a Spring Boot API developed for learning and practice purposes.
It is part of a larger application, where the front-end will be developed later with another tool.

## ⚙️ Features

-  CRUD for doctors
-  CRUD for patients
-  Appointment scheduling (in development)
-  Appointment cancellation (in development)

## 🛠 Technologies Used

- Java 17
- Spring Boot 3.4.4
- Maven
- MySQL
- Hibernate
- Flyway
- Lombok

## 🚀 How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/anacorreia15/api-spring-boot.git
   ```

2. Navigate to the project directory:

   ```bash
   cd api-spring-boot
   ```

3. Configure your MySQL database and update the credentials in `application.properties`.

4. Run Flyway migrations (if applicable).

5. Build and run the application:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 📄 API Documentation

Below are the available API endpoints for the Voll.med management system.
### 🔹 Doctors

#### ➕ Register a new doctor
- **Method**: POST
- **Endpoint**: /medicos
- **Request Body**:
```json
{
  "nome": "Ana Correia",
  "email": "ana@voll.med",
  "crm": "123456",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cep": "12345678",
    "cidade": "Lisboa",
    "uf": "PT",
    "numero": "100",
    "complemento": "Bloco B"
  }
}
```
- **Response**: 201 Created

---

#### 📋 List doctors (paginated)
- **Method**: GET
- **Endpoint**: /medicos
- **Query Params**: page, size (optional)
- **Response**: List of doctors (only active)

---

#### ✏️ Update a doctor
- **Method**: PUT
- **Endpoint**: /medicos
- **Request Body**:
```json
{
  "id": 1,
  "nome": "Ana C. Correia",
  "telefone": "123456789",
  "endereco": {
    "logradouro": "Rua B"
  }
}
```

---

#### ❌ Delete a doctor (soft delete)
- **Method**: DELETE
- **Endpoint**: /medicos/{id}
- **Response**: 204 No Content

---

#### 🔍 Get doctor by ID
- **Method**: GET
- **Endpoint**: /medicos/{id}

---

### 🔹 Patients

#### ➕ Register a new patient
- **Method**: POST
- **Endpoint**: /pacientes
- **Request Body**:
```json
{
  "nome": "João Silva",
  "email": "joao@voll.med",
  "cpf": "12345678900",
  "telefone": "987654321",
  "endereco": {
    "logradouro": "Rua C",
    "bairro": "Norte",
    "cep": "87654321",
    "cidade": "Porto",
    "uf": "PT",
    "numero": "200"
  }
}
```

---

#### 📋 List patients (paginated)
- **Method**: GET
- **Endpoint**: /pacientes

---

#### ✏️ Update a patient
- **Method**: PUT
- **Endpoint**: /pacientes

---

#### ❌ Delete a patient (soft delete)
- **Method**: DELETE
- **Endpoint**: /pacientes/{id}

---

#### 🔍 Get patient by ID
- **Method**: GET
- **Endpoint**: /pacientes/{id}

---

### 🔜 Coming Soon

- ✅ Schedule appointment: `/consultas`
- ✅ Cancel appointment: `/consultas/{id}`


## 🧪 Manual Testing

All API endpoints were tested manually using [Insomnia](https://insomnia.rest/). 
A complete collection is available and contains all CRUD operations and authentication flow using JWT tokens.

### How to Test
1. Import the provided Insomnia collection (`Insomnia_2025-04-16.yaml`).
2. Set the environment base URL to `http://localhost:8080`.
3. Run the login request to obtain a JWT token.
4. Use the token in the Authorization header to test secured endpoints.

### Test Scenarios Covered
- ✅ Successful registration, update, and deletion of doctors and patients.
- ✅ Validation error scenarios and meaningful responses.
- ✅ Full JWT authentication flow.
- ✅ Custom exception handling tested for all major use cases.
