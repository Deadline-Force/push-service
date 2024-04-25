# About HTTP Hadlers

## Вход пользователя

**Метод:** `POST`  
**Эндпоинт:** `/auth/login`  
**Тип контента:** `application/json`

**Описание:**  
Этот запрос предназначен для аутентификации пользователя. Пользователь должен предоставить адрес электронной почты (login) и пароль (password) для входа в систему.

**Параметры запроса:**  
- `login`: Адрес электронной почты, используемый в качестве логина.
- `password`: Пароль пользователя.

**Пример запроса:**
```http
POST http://localhost:8080/auth/login  
Content-Type: application/json

{
    "login": "pavel@xml.com",
    "password": "12345"
}
```

**Пример ответа (в случае успешного входа):**
```{
    "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTM5OTc1NjYsInVzZXJJZCI6MywidXNlcm5hbWUiOiLQn9Cw0LLQtdC7Iiwicm9sZSI6IlVTRVIifQ.y8yUWHn_4Eaib57qKizKnBD49xQS_qsf0ZZn3pX-rKfj-yyWRLN2ipaB1QdqoGmmztftrPCTfRyYMUDPipGYug"
}
```
Ответы:

- 200 OK: Успешный вход пользователя в систему. В ответе содержится JWT-токен, который можно использовать для аутентификации в последующих запросах.
- 400 Bad Request: Некорректный запрос (например, неверные учетные данные).
- 401 Unauthorized: Неверные учетные данные пользователя.

 ## Отправка уведомления

**Метод:** `POST`  
**Эндпоинт:** `/notifications/send/{userId}`  
**Тип контента:** `application/json`

**Описание:**  
Этот запрос предназначен для отправки уведомления указанному пользователю с заданным идентификатором. Для успешного выполнения запроса необходимо предоставить заголовок авторизации с действительным JWT-токеном.

**Параметры запроса:**  
- `userId`: Идентификатор пользователя, которому отправляется уведомление.
- `title`: Заголовок уведомления.
- `message`: Текст уведомления.

**Пример запроса:**
```
{
    "title": "Greeting",
    "message": "Hello World"
}

```

**Пример ответа (в случае успешной отправки уведомления):**
```{
    "id": 123,
    "title": "Greeting",
    "message": "Hello, my friend",
    "userOwner": {
        "id": 1,
        "username": "username",
        "login": "example@example.com",
        "password": "password",
        "role": "USER"
    },
    "createdAt": "2024-04-27T12:00:00Z"
}
```
Ответы:

- 200 OK: Уведомление успешно отправлено.
- 401 Unauthorized: Недействительный или отсутствующий токен авторизации.
- 404 Not Found: Пользователь с указанным идентификатором не найден.

 ## Получение истории уведомлений

**Метод:** `GET`  
**Эндпоинт:** `/notifications/history`  
**Тип контента:** `application/json`

**Описание:**  
Этот запрос предназначен для получения истории уведомлений текущего пользователя. Для успешного выполнения запроса необходимо предоставить заголовок авторизации с действительным JWT-токеном.

**Параметры запроса:**  
- `page`: Номер страницы (начиная с 0).
- `size`: Количество записей на странице.

**Пример запроса:**
```
GET http://localhost:8080/notifications/history?page=0&size=20
Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTQwMDIxMzgsInVzZXJJZCI6MSwidXNlcm5hbWUiOiJ0ZXN0Iiwicm9sZSI6IlVTRVIifQ.25fUdA1t94DYaf5UpAwS5zlIwU3zuVTTthZEvcG5DWgmbtEEoNUXjfllUUkZAecCs2ZtwZQnSqW-cgypxXN94A

```

**Пример ответа (в случае успешного получения истории уведомлений):**
```{
    "content": [
        {
            "id": 1,
            "title": "New message",
            "message": "You have a new message",
            "userOwner": {
                "id": 1,
                "username": "test",
                "login": "test@example.com",
                "password": "password",
                "role": "USER"
            },
            "createdAt": "2024-04-26T12:00:00Z"
        },
        {
            "id": 2,
            "title": "Reminder",
            "message": "Don't forget about the meeting",
            "userOwner": {
                "id": 1,
                "username": "test",
                "login": "test@example.com",
                "password": "password",
                "role": "USER"
            },
            "createdAt": "2024-04-25T09:00:00Z"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 20,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}
```
Ответы:

- 200 OK: Успешное получение истории уведомлений.
- 401 Unauthorized: Недействительный или отсутствующий токен авторизации.

 ## Получение моих уведомлений

**Метод:** `GET`  
**Эндпоинт:** `/notifications/my`  
**Тип контента:** `application/json`

**Описание:**  
Этот запрос предназначен для получения уведомлений, отправленных текущему пользователю. Для успешного выполнения запроса необходимо предоставить заголовок авторизации с действительным JWT-токеном.

**Параметры запроса:**  
- `page`: Номер страницы (начиная с 0).
- `size`: Количество записей на странице.

**Пример запроса:**
```
GET http://localhost:8080/notifications/my?page=0&size=20
Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTQwMDExMjUsInVzZXJJZCI6MywidXNlcm5hbWUiOiLQn9Cw0LLQtdC7Iiwicm9sZSI6IlVTRVIifQ.WQ5NnLMg_ExgeRvy44gXhAYWLcC8dX-Vgb1S3vmQEU3MM9WJ9Q3DJzamsvKNB_4dminItrg4UDUwlkXCQQF7Jw

```

**Пример ответа (в случае успешного получения уведомлений):**
```{
    "content": [
        {
            "id": 1,
            "title": "New message",
            "message": "You have a new message",
            "userOwner": {
                "id": 3,
                "username": "user",
                "login": "user@example.com",
                "password": "$2a$10$RgY0X4rlLmwDljAmYtY2DOFKfmPL/evphr9iI4Rt5vXe6YhDT4ZSe",
                "role": "USER"
            },
            "createdAt": "2024-04-26T12:00:00Z"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 20,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```
Ответы:

- 200 OK: Успешное получение истории уведомлений.
- 401 Unauthorized: Недействительный или отсутствующий токен авторизации.

 ## Регистрация нового пользователя

**Метод:** `GET`  
**Эндпоинт:** `/auth/signup`  
**Тип контента:** `application/json`

**Описание:**  
Этот запрос предназначен для регистрации нового пользователя. Пользователь должен предоставить уникальное имя пользователя (username), адрес электронной почты (login) и пароль (password).

**Параметры запроса:**  
- `username`: Уникальное имя пользователя.
- `login`: Адрес электронной почты, используемый в качестве логина.
- `password`: Пароль пользователя.

**Пример запроса:**
```
POST http://localhost:8080/auth/signup
Content-Type: application/json

{
    "username": "Павел",
    "login": "pavel@xml.com",
    "password": "12345"
}

```

**Пример ответа (в случае успешной регистрации):**
```{
    "message": "User registered successfully"
}
```
Ответы:

- 200 OK: Успешная регистрация нового пользователя.
- 400 Bad Request: Некорректный запрос (например, неправильный формат электронной почты или слишком короткий пароль).
- 409 Conflict: Пользователь с указанным адресом электронной почты уже существует.
