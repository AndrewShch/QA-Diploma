## Отчет по итогам автоматизированного тестирования

### Краткое описание

Было проведено автоматизированное тестирование веб-сервиса покупки тура, в соответсвии с [планом](Plan.md)
* Проведены позитивные и негативные сценарии
* Реализована поддержка и взаимодействие с базами данных  MySQL и PostgreSQL


### Тест-кейсы



* Общее количество тест-кейсов - 40
* Успешных - 28
* Неуспешных - 12 

### Отчет Gradle

![gradle](https://user-images.githubusercontent.com/84085779/141511365-60b0cb99-e418-4c43-8cc1-474c3de67f93.jpg)


### Отчет Allure
![allure](https://user-images.githubusercontent.com/84085779/141511382-b54f3d44-7d0e-4f3e-8af8-7b49025d8bf5.jpg)
![allure2](https://user-images.githubusercontent.com/84085779/141511390-e9a51c13-8b56-4aa3-ac05-5972b14cb054.jpg)
![allure3](https://user-images.githubusercontent.com/84085779/141511407-61f97690-7edb-4f5e-87a4-66fd0608a045.jpg)

### Общие рекомендации

* Составить документацию для веб-сервиса
* Исправить найденные ошибки:
1. [Появляется неверная ошибка, при не заполнении поля, кроме поля "Владелец"](https://github.com/AndrewShch/QA-Diploma/issues/11)
2. [После исправления ошибки по полях "Номер карты" , "Владелец" и "CVC/CVV", надписи об ошибках остаются](https://github.com/AndrewShch/QA-Diploma/issues/10)
3. [При вводе в поле Номер карты всех нулей, заявка отправляется](https://github.com/AndrewShch/QA-Diploma/issues/9)
4. [Название города Марракеш написано неправильно](https://github.com/AndrewShch/QA-Diploma/issues/8)
5. [В базе данных значение в поле "credit_id" - <null>, после покупки тура в кредит](https://github.com/AndrewShch/QA-Diploma/issues/7)
6. [Отсутствует валидация поля "Владелец"](https://github.com/AndrewShch/QA-Diploma/issues/6)
7. [Валидация пропускает значение "00" в поле месяц](https://github.com/AndrewShch/QA-Diploma/issues/5)
8. [Название страницы не подходит для сайта покупки тура](https://github.com/AndrewShch/QA-Diploma/issues/4)
9. [Сумма стоимости тура и сумма в базе данных отличается](https://github.com/AndrewShch/QA-Diploma/issues/3)
10. [Банк одобряет покупку в кредит картой со статусом "DECLINED"](https://github.com/AndrewShch/QA-Diploma/issues/2)
11. [Банк одобряет операцию по оплате картой со статусом "DECLINED"](https://github.com/AndrewShch/QA-Diploma/issues/1)
