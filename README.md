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
