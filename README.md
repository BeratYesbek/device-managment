# Device Management REST Service

## Description

This project provides a RESTful service for managing a device database. The service supports operations for adding, retrieving, updating, deleting, and searching devices. Devices are associated with brands, and both entities are represented in the system.

## Represented Entities

### Device
- `deviceName`: The name of the device.
- `brand`: The brand associated with the device.
- `createdAt`: The time when the device was created.

### Brand
- `name`: The name of the brand.
- `devices`: A list of devices associated with the brand.

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/77804034/354692811-b066ee13-f91f-4960-ad8b-03cc2fe31d50.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240802%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240802T183946Z&X-Amz-Expires=300&X-Amz-Signature=16a55072f4e63a912b3507061656e1d6d8c8729130dede6b0efea7a4198d6991&X-Amz-SignedHeaders=host&actor_id=77804034&key_id=0&repo_id=837116755">"

## Supported Operations

1. **Add Device**: Adds a new device to the database.
2. **Get Device by Identifier**: Retrieves a device by its unique identifier.
3. **List All Devices**: Lists all devices in the database.
4. **Update Device**: Updates an existing device. Supports both full and partial updates.
5. **Delete Device**: Deletes a device from the database.
6. **Search Device by Brand**: Searches for devices based on the brand name.

## Features

- **Soft Delete**: Devices are not permanently deleted from the database but marked as deleted.
- **Optimistic Locking**: Ensures data consistency in concurrent transactions.
- **Transactional Management**: Operations are wrapped in transactions to ensure atomicity.
- **Controller Advice**: Provides centralized exception handling.
- **Unit Tests & Integration Tests**: Ensures the reliability and functionality of the service.
- **Docker Configuration**: Includes Docker configuration for containerized deployment.
- **Test Containers**: Used for running integration tests in isolated environments.
- **MapStruct**: Used for mapping between DTOs and entities.
- **Lombok**: Used for reducing boilerplate code.
- **Spring Data JPA**: Used for data access and persistence.
- **Flyway**: Used for database migration.

## Usage

### Setting Up

1. **Clone the Repository**
   ```sh
   git clone https://github.com/BeratYesbek/device-managment.git
   cd device-managment
   ```

2. **Run**
   ```sh
   docker-compose up --build
   ```

### API Endpoints
You can access the postman collection under the `postman` directory.:
- **POST /devices**: Add a new device.
- **GET /devices/{id}**: Get a device by its identifier.
- **GET /devices**: List all devices.
- **PUT /devices/{id}**: Update a device by its identifier.
- **DELETE /devices/{id}**: Delete a device by its identifier.
- **GET /devices/search**: Search devices by brand name.
