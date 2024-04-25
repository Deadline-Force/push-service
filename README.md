## Hack'24 | Deadline-Force

### Окружение проекта
- PostgreSQL (v.12)
- Java (v.17)
- NodeJS (v.18)

### Скачайте проект
```
cd ~
git clone https://github.com/Deadline-Force/push-service.git
```

### Запустите проект
```
cd ./push-service/
docker-compose up
```

### Или соберите образы отдельно
#### Для Backend
```
docker build -t java-backend:1.0 ./backend
```
#### Для Frontend
```
docker build -t react-frontend:1.0 ./front
```

### Тестируйте после запуска
#### Backend
```
xdg-open http://localhost:8080/docs
```
#### Frontend
```
xdg-open http://localhost:5173/
```

## Дополнительная информация
### Пользователи по умолчанию:
- login:password | ROLE
- employeetest1@gmail.com:123 | EMPLOYEE
- testtest2employee@mail.ru:123 | EMPLOYEE
- superuser@gmail.com:123 | ADMIN
- senioradmin@gmail.com:123 | ADMIN

### Описание основных HTTP Handlers
> [./readme/http-module/README.md](./readme/http-module/README.md)

#### Все остальные HTTP Handlers
> http://localhost:8080/docs
