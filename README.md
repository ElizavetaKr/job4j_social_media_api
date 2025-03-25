![Build Status](https://github.com/ElizavetaKr/job4j_social_media_api/actions/workflows/maven.yml/badge.svg)

# "Social Media API"
***
## RESTful API для социальной медиа платформы. 
Как пользователь Вы можете зарегистрироваться, входить в личный кабинет, создавать посты, переписываться, 
подписываться на других пользователей и просматривать свою ленту активности.

***
## В данном проекте используются: 
Java 17, Spring Boot 3.4.4, Checkstyle 9.0, 
Spring Security
***
## Требования к окружению:
Java 17

Maven 3.10

PostgreSQL 16
***
## Запуск проекта:
1. Скачиваете проект из данного репозитория
2. Откройте командную строку
3. Перейдите в корневой каталог проекта
4. Выполнить команду mvn clean install
5. Выполнить команду mvn spring-boot:run
6. Откройте ссылку http://localhost:8080/index
***
## Описание проекта:

### Аутентификация и авторизация:
- Вы можете зарегистрироваться, указав имя, электронную почту и пароль.


- Вы можете войти в систему, предоставив правильные учетные данные.


- API обеспечивает защиту конфиденциальности пользовательских данных, включая хэширование паролей и использование JWT.


### Управление постами:

- Вы можете создавать новые посты, указывая текст, заголовок и прикрепляя изображения.


- Вы можете просматривать посты других пользователей.


- Вы можете обновлять и удалять свои собственные посты.


### Взаимодействие пользователей:

- Вы можете отправлять заявки в друзья другим пользователям. Если Вашу заявку приняли, то вы становитесь друзьями, если нет, то остаетесь подписчиком.


- Пользователи, являющиеся друзьями, также являются подписчиками друг на друга.


- Если один пользователь удаляет другого из друзей, то он также отписывается. Второй пользователь при этом остается подписчиком.


- Друзья могут писать друг другу сообщения.


### Подписки и лента активности:

- Лента активности пользователя отображает последние посты от пользователей, на которых он подписан.


- Лента активности поддерживает разделение и сортировку по времени создания постов.


***
job4j_social_media_api
