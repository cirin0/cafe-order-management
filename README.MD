# Система управління замовленнями для кафе

## Опис
Цей проєкт реалізує просту систему для управління замовленнями в кафе. Основна мета проєкту — забезпечити зручний інтерфейс для роботи з меню, замовленнями та клієнтами. Система включає REST API, що дозволяє адміністратору та персоналу кафе ефективно управляти замовленнями.

---

## Функціональні вимоги
1. **Управління меню**:
  - Додавання нових страв.
  - Перегляд доступного меню.
  - Оновлення інформації про страву (назва, ціна, опис, наявність).
  - Видалення страв із меню.

2. **Управління замовленнями**:
  - Створення нового замовлення.
  - Перегляд усіх замовлень.
  - Перегляд замовлень конкретного клієнта.
  - Оновлення статусу замовлення (Очікується, "Готується", "Готово", "Виконано", "Скасовано").
  - Видалення замовлень.

3. **Управління клієнтами**:
  - Додавання клієнтів (ім'я, контактний номер, email).
  - Перегляд клієнта за ім'ям.
  - Перегляд клієнта за ID.
  - Перегляд усіх клієнтів.
  - Отримання інформації про конкретного клієнта та його замовлення.
  - Видалення клієнтів.

---

## API Endpoints

#### Customer Endpoints

- **Get All Customers**
    - **URL:** `/api/customers`
    - **Method:** `GET`
    - **Description:** Retrieves a list of all customers

- **Get Customer by I'd**
    - **URL:** `/api/customers/{id}`
    - **Method:** `GET`
    - **Description:** Retrieves a customer by their id
    - **Path Parameters:**
        - `id` (long): The name of the customer

- **Add Customer**
    - **URL:** `/api/customers`
    - **Method:** `POST`
    - **Description:** Adds a new customer
    - **Request Body:**
      ```json
      {
        "name": "John Doe",
        "phoneNumber": "1234567890",
        "email": "john.doe@example.com"
      }
      ```

- **Update Customer**
    - **URL:** `/api/customers/{name}`
    - **Method:** `PUT`
    - **Description:** Updates an existing customer by their name
    - **Path Parameters:**
        - `name` (string): The name of the customer
    - **Request Body:**
      ```json
      {
        "email": "new.email@example.com"
      }
      ```
- **Delete Customer**
    - **URL:** `/api/customers/{id}`
    - **Method:** `DELETE`
    - **Description:** Deletes a customer by their id
    - **Path Parameters:**
        - `id` (long): The id of the customer

#### Dish Endpoints

- **Get All Dishes**
    - **URL:** `/api/dishes`
    - **Method:** `GET`
    - **Description:** Retrieves a list of all dishes

- **Get Dish by ID**
    - **URL:** `/api/dishes/{id}`
    - **Method:** `GET`
    - **Description:** Retrieves a dish by its ID
    - **Path Parameters:**
        - `id` (long): The ID of the dish

- **Create Dish**
    - **URL:** `/api/dishes`
    - **Method:** `POST`
    - **Description:** Creates a new dish
    - **Request Body:**
      ```json
      {
        "name": "Pizza",
        "price": 10.99,
        "description": "Delicious pizza with cheese and tomato sauce",
        "available": true,
        "category": "Main Course"
      }
      ```

- **Update Dish**
    - **URL:** `/api/dishes/{id}`
    - **Method:** `PUT`
    - **Description:** Updates an existing dish by its ID
    - **Path Parameters:**
        - `id` (long): The ID of the dish
    - **Request Body:**
      ```json
      {
        "name": "Updated Pizza",
        "category": "Main Course"
      }
      ```

- **Delete Dish**
    - **URL:** `/api/dishes/{id}`
    - **Method:** `DELETE`
    - **Description:** Deletes a dish by its ID
    - **Path Parameters:**
        - `id` (long): The ID of the dish

#### Order Endpoints

- **Get All Orders**
    - **URL:** `/api/orders`
    - **Method:** `GET`
    - **Description:** Retrieves a list of all orders

- **Get Order by ID**
    - **URL:** `/api/orders/{id}`
    - **Method:** `GET`
    - **Description:** Retrieves an order by its ID
    - **Path Parameters:**
        - `id` (integer): The ID of the order

- **Get Orders by Customer ID**
    - **URL:** `/api/orders/customer/{id}`
    - **Method:** `GET`
    - **Description:** Retrieves a list of orders by customer ID
    - **Path Parameters:**
        - `id` (integer): The ID of the customer

- **Create Order**
    - **URL:** `/api/orders`
    - **Method:** `POST`
    - **Description:** Creates a new order
    - **Request Body:**
      ```json
      {
        "customerId": 1,
        "dishIds": [1, 2, 3]
      }
      ```

- **Delete Order**
    - **URL:** `/api/orders/{id}`
    - **Method:** `DELETE`
    - **Description:** Deletes an order by its ID
    - **Path Parameters:**
        - `id` (integer): The ID of the order

---
## ER Diagram

![ER-diagram](https://github.com/{{name}}/cafe-order-management/blob/main/Er.png)

