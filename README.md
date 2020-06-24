# scaling-train

REST API тесты на создание, редактирование должности и удаление пользователя для сайта https://reqres.in/

Для запуска тестов необходимо выполнить следующую команду:

```mvn clean test -DsuiteXMLFile=src/test/resources/testng.xml```

После выполнения тестов для формирования отчета следует выполнить следующую команду:

```allure serve build/allure-results```
