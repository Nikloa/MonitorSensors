1. Создаем базу данных в PostgreSQL
2. Вызываем Query и копируем в неё полностью всё содержимое файла DatabaseLaunch.txt. Нажимаем выполнить.
3. Открываем рабочую среду, клонируем проект с GitHub https://github.com/Nikloa/MonitorSensors
4. Открываем файл application.properties вводим в соответсвующие поля: актуальное имя базы данных, username, password для PostgreSQL
5. Нажимаем Run, запускаем приложение
6. Открываем браузер переходим на http://localhost:8080, после перенаправления на логин, вводим логин (user, admin) и пароль (user, admin - соответственно)
7. При использовании приложения через Postman следует вначале перейти на вкладку логина http://localhost:8080/login
        выбрать метод Post и на вкладке Body выбрать режим form-data, после чего ввести во вкладке KEY два ключа username и password, и учетные данные во вкладке VALUE
8. Доступные пути
        для Администратора: Delete - /sensors/**/delete, Put - /sensors/**/update, Post - /sensors/create
        для Администратора и Пользователя: Get - /sensors, Get - /sensors/**, Get - /search
