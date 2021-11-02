### Запуск Авто-тестов
#### Предусловие
* Для запуска контейнеров ипользовалась виртуальная машина. 
   * Адрес - `185.119.57.64`
   * Логин - `student`
   * Пароль - `netologystudent`
* Для запуска виртуальной машины использовалась программа PuTTY.

#### Шаги для запуска тестов
1. Запускаем IntelliJ IDEA
2. Запускаем программу PuTTY.
3. В окне `Host Name (or IP address)` вводим адрес
4. Вводим логин и пароль
5. Клонируем репозиторий 
   * `git clone https://github.com/AndrewShch/QA-Diploma`
6. Переходим в каталог
   * `cd QA-Diploma`
7. Запускаем Docker 
   * `docker-compose up -d`
8. Запускаем SUT с поддержкой MySQL 
   * `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`
9. Переходим в IntelliJ IDEA
10. Запускаем тесты с базой данных MySQL 
    * `./gradlew clean test -Durl=jdbc:mysql://185.119.57.64:3306/app`
11. Переходим в виртуальную машину
12. Запускаем SUT с поддержкой PostgreSQL 
    * `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar` 
13. Переходим в IntelliJ IDEA
14. Запускаем тесты с базой данных PostgreSQL 
    * `./gradlew clean test -Durl=jdbc:postgresql://185.119.57.64:5432/app`
15. Генерируем отчет спомощью фреймворка Allure
    * `./gradlew allureServe`
16. Останавливаем контейнеры 
    * `docker-compose down`