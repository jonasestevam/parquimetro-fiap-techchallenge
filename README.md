# Documentação de Arquitetura da Aplicação em Produção

Esta documentação descreve a arquitetura da aplicação em produção, destacando os serviços AWS e tecnologias utilizadas para garantir escalabilidade, disponibilidade e eficiência. A aplicação serveless é projetada para lidar com milhões de usuários, utiliza RabbitMQ como fila e tem bancos de dados MongoDB e MySQL em contêineres.

## Visão Geral da Arquitetura

A aplicação é composta por vários componentes que trabalham juntos para fornecer serviços eficientes e confiáveis. A arquitetura é baseada na AWS e faz uso extensivo de contêineres Docker, RabbitMQ para processamento de mensagens e os bancos de dados MongoDB e MySQL.

### AWS Services Utilizados

1. **AWS Lambda**: A aplicação serveless utiliza funções Lambda para executar código em resposta a eventos, como requisições HTTP e mensagens da fila RabbitMQ.

2. **Amazon API Gateway**: O Amazon API Gateway é usado para expor endpoints HTTP RESTful que direcionam as solicitações para as funções Lambda apropriadas.

3. **Amazon SQS (Simple Queue Service)**: O serviço de fila da AWS é usado em conjunto com o RabbitMQ para garantir a escalabilidade e processamento confiável de mensagens.

4. **Amazon EKS (Elastic Kubernetes Service)**: O Amazon EKS é utilizado para orquestrar os contêineres Docker e fornecer escalabilidade, alta disponibilidade e gerenciamento de clusters.

5. **Amazon RDS (Relational Database Service)**: O Amazon RDS é usado para hospedar o banco de dados MySQL, garantindo alta disponibilidade, escalabilidade e gerenciamento simplificado.

6. **Amazon DocumentDB**: O Amazon DocumentDB é usado para o banco de dados MongoDB, fornecendo escalabilidade horizontal e gerenciamento facilitado.

### Componentes da Aplicação

A aplicação é composta pelos seguintes componentes:

1. **Lambda Functions**: As funções Lambda são os blocos de construção da aplicação. Elas executam a lógica de negócios em resposta a eventos e são escalonadas automaticamente de acordo com a demanda.

2. **API Gateway Endpoints**: O Amazon API Gateway define endpoints para acessar a funcionalidade da aplicação. Ele mapeia as requisições HTTP para as funções Lambda apropriadas.

3. **Containers Docker**: A aplicação roda em contêineres Docker gerenciados pelo Amazon EKS. Isso permite escalabilidade, isolamento e facilidade de implantação.

4. **RabbitMQ**: O RabbitMQ é usado como sistema de mensagens para processar tarefas em segundo plano e coordenar ações dentro da aplicação.

5. **Bancos de Dados RDS e DocumentDB**: Os bancos de dados MySQL e MongoDB são hospedados em instâncias RDS e DocumentDB separadas para armazenamento e recuperação de dados.

## Fluxo de Dados

O fluxo de dados na aplicação segue as etapas a seguir:

1. O usuário envia uma solicitação HTTP para um dos endpoints expostos pelo Amazon API Gateway.

2. O API Gateway encaminha a solicitação para a função Lambda apropriada.

3. A função Lambda processa a solicitação, interagindo com os contêineres Docker quando necessário e escrevendo mensagens na fila RabbitMQ para processamento assíncrono.

4. Os trabalhadores em contêineres Docker lêem mensagens da fila RabbitMQ e executam tarefas em segundo plano, atualizando os bancos de dados RDS e DocumentDB conforme necessário.

5. Dados são lidos dos bancos de dados quando solicitados e retornados ao usuário por meio do API Gateway.

## Monitoramento e Escalabilidade

Os alarmes configurados acionam a escalabilidade automática quando necessário, adicionando mais instâncias de contêineres Docker, funções Lambda ou recursos de banco de dados.

## Conclusão

A arquitetura da aplicação em produção é altamente escalável e disponível, atendendo às necessidades de milhões de usuários. Ela faz uso eficiente dos serviços da AWS, contêineres Docker e bancos de dados gerenciados para garantir um desempenho confiável e eficaz.


# API Documentation

This is the documentation for the API endpoints of the Parquimetro application.

## Conductors

### Get conductor by ID
- **URL:** `/api/conductors/{id}`
- **Method:** GET
- **Description:** Retrieves a conductor by its ID.
- **Parameters:**
  - `id` (Path) - Conductor ID (required, integer)
- **Responses:**
  - 200 OK: Returns a `ConductorDTO` object.

### Update conductor
- **URL:** `/api/conductors/{id}`
- **Method:** PUT
- **Description:** Updates a conductor with the specified ID.
- **Parameters:**
  - `id` (Path) - Conductor ID (required, integer)
- **Request Body:**
  - `ConductorDTO` (application/json, required)
- **Responses:**
  - 200 OK: Returns the updated `ConductorDTO`.

### Delete conductor
- **URL:** `/api/conductors/{id}`
- **Method:** DELETE
- **Description:** Deletes a conductor with the specified ID.
- **Parameters:**
  - `id` (Path) - Conductor ID (required, integer)

## Payment Methods

### Get payment method by ID
- **URL:** `/api/paymentMethods/{id}`
- **Method:** GET
- **Description:** Retrieves a payment method by its ID.
- **Parameters:**
  - `id` (Path) - Payment Method ID (required, integer)
- **Responses:**
  - 200 OK: Returns a `PaymentMethodDTO` object.

### Update payment method
- **URL:** `/api/paymentMethods/{id}`
- **Method:** PUT
- **Description:** Updates a payment method with the specified ID.
- **Parameters:**
  - `id` (Path) - Payment Method ID (required, integer)
- **Request Body:**
  - `PaymentMethodDTO` (application/json, required)
- **Responses:**
  - 200 OK: Returns the updated `PaymentMethodDTO`.

### Delete payment method
- **URL:** `/api/paymentMethods/{id}`
- **Method:** DELETE
- **Description:** Deletes a payment method with the specified ID.
- **Parameters:**
  - `id` (Path) - Payment Method ID (required, integer)

## Receipts

### Get receipt by ID
- **URL:** `/api/receipts/{id}`
- **Method:** GET
- **Description:** Retrieves a receipt by its ID.
- **Parameters:**
  - `id` (Path) - Receipt ID (required, integer)
- **Responses:**
  - 200 OK: Returns a `ReceiptDTO` object.

## Parking Alerts

### Get parking alert by ID
- **URL:** `/api/parkingAlerts/{id}`
- **Method:** GET
- **Description:** Retrieves a parking alert by its ID.
- **Parameters:**
  - `id` (Path) - Parking Alert ID (required, integer)
- **Responses:**
  - 200 OK: Returns a `ParkingAlertDTO` object.

### Update parking alert
- **URL:** `/api/parkingAlerts/{id}`
- **Method:** PUT
- **Description:** Updates a parking alert with the specified ID.
- **Parameters:**
  - `id` (Path) - Parking Alert ID (required, integer)
- **Request Body:**
  - `ParkingAlertDTO` (application/json, required)
- **Responses:**
  - 200 OK: Returns the updated `ParkingAlertDTO`.

### Delete parking alert
- **URL:** `/api/parkingAlerts/{id}`
- **Method:** DELETE
- **Description:** Deletes a parking alert with the specified ID.
- **Parameters:**
  - `id` (Path) - Parking Alert ID (required, integer)

## Parking Sessions

### Start a parking session
- **URL:** `/api/parksessions/start`
- **Method:** POST
- **Description:** Starts a new parking session and returns it.
- **Request Body:**
  - `ParkingSessionDTO` (application/json, required)
- **Responses:**
  - 200 OK: Returns the created `ParkingSessionDTO`.

### Get all parking sessions
- **URL:** `/api/parksessions`
- **Method:** GET
- **Description:** Retrieves a list of all parking sessions.
- **Responses:**
  - 200 OK: Returns a list of `ParkingSessionDTO` objects.

## Vehicles

### Create a new vehicle
- **URL:** `/api/vehicles`
- **Method:** POST
- **Description:** Creates a new vehicle and returns it.
- **Request Body:**
  - `VehicleDTO` (application/json, required)
- **Responses:**
  - 200 OK: Returns the created `VehicleDTO`.

### Get all vehicles
- **URL:** `/api/vehicles`
- **Method:** GET
- **Description:** Retrieves a list of all vehicles.
- **Responses:**
  - 200 OK: Returns a list of `VehicleDTO` objects.

### Get vehicle by ID
- **URL:** `/api/vehicles/{id}`
- **Method:** GET
- **Description:** Retrieves a vehicle by its ID.
- **Parameters:**
  - `id` (Path) - Vehicle ID (required, integer)
- **Responses:**
  - 200 OK: Returns a `VehicleDTO` object.

### Update vehicle
- **URL:** `/api/vehicles/{id}`
- **Method:** PUT
- **Description:** Updates a vehicle with the specified ID.
- **Parameters:**
  - `id` (Path) - Vehicle ID (required, integer)
- **Request Body:**
  - `VehicleDTO` (application/json, required)
- **Responses:**
  - 200 OK: Returns the updated `VehicleDTO`.

### Delete vehicle
- **URL:** `/api/vehicles/{id}`
- **Method:** DELETE
- **Description:** Deletes a vehicle with the specified ID.
- **Parameters:**
  - `id` (Path) - Vehicle ID (required, integer)

