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

- **Отримати список усіх клієнтів**
    - **URL:** `/api/customers`
    - **Method:** `GET`
    - 
- **Отримати клієнта за його ідентифікатором**
    - **URL:** `/api/customers/{id}`
    - **Method:** `GET`
    - **Path Parameters:**
        - `id` (long): Ім'я клієнта

- **Додає нового клієнта**
    - **URL:** `/api/customers`
    - **Method:** `POST`
    - **Request Body:**
      ```json
      {
        "name": "John Doe",
        "phoneNumber": "1234567890",
        "email": "john.doe@example.com"
      }
      ```

- **Оновити наявного клієнта за його іменем**
    - **URL:** `/api/customers/{name}`
    - **Method:** `PUT`
    - **Path Parameters:**
        - `name` (string): Ім'я замовника
    - **Request Body:**
      ```json
      {
        "email": "new.email@example.com"
      }
      ```
- **Видалити клієнта за його ідентифікатором**
    - **URL:** `/api/customers/{id}`
    - **Method:** `DELETE`
    - **Path Parameters:**
        - `id` (long): Ідентифікатор клієнта

#### Dish Endpoints

- **Отримати список усіх страв**
    - **URL:** `/api/dishes`
    - **Method:** `GET`

- **Отримати страву за її ідентифікатором**
    - **URL:** `/api/dishes/{id}`
    - **Method:** `GET`
    - **Path Parameters:**
        - `id` (long): Ідентифікатор страви

- **Створити нову страву**
    - **URL:** `/api/dishes`
    - **Method:** `POST`
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

- **Оновити наявну страву за її ідентифікатором**
    - **URL:** `/api/dishes/{id}`
    - **Method:** `PUT`
    - **Path Parameters:**
        - `id` (long): Ідентифікатор страви
    - **Request Body:**
      ```json
      {
        "name": "Updated Pizza",
        "category": "Main Course"
      }
      ```

- **Видалити страви за її ідентифікатором**
    - **URL:** `/api/dishes/{id}`
    - **Method:** `DELETE`
    - **Path Parameters:**
        - `id` (long): Ідентифікатор страви

#### Order Endpoints

- **Отримати список усіх замовлень**
    - **URL:** `/api/orders`
    - **Method:** `GET`

- **Отримати замовлення за його ідентифікатором**
    - **URL:** `/api/orders/{id}`
    - **Method:** `GET`
    - **Path Parameters:**
        - `id` (integer): Ідентифікатор замовлення

- **Отримати замовлення за його ідентифікатором**
    - **URL:** `/api/orders/customer/{id}`
    - **Method:** `GET`
    - **Path Parameters:**
        - `id` (integer): Ідентифікатор замовлення

- **Створити нове замовлення**
    - **URL:** `/api/orders`
    - **Method:** `POST`
    - **Request Body:**
      ```json
      {
        "customerId": 1,
        "dishIds": [1, 2, 3]
      }
      ```

- **Видалити замовлення за його ідентифікатором**
    - **URL:** `/api/orders/{id}`
    - **Method:** `DELETE`
    - **Path Parameters:**
        - `id` (integer): Ідентифікатор замовлення

---
## ER Diagram

![ER-diagram](https://github.com/cirin0/cafe-order-management/blob/main/Er.png "ER Diagram")

