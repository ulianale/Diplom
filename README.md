# Дипломный проект по профессии «Тестировщик»

## Описание приложения
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:
1. Обычная оплата по дебетовой карте.
2. Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

* сервису платежей, далее Payment Gate;
* кредитному сервису, далее Credit Gate.

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

## Запуск автотестов
### Перед запуском тестов
1. Необходимо установить и настроить:
* [Intellij IDEA](https://www.jetbrains.com/ru-ru/idea/download/?section=windows#section=windows)
* [Google Chrome](https://www.google.ru/chrome/)
* [Docker](https://www.docker.com)

2. Склонировать [репозиторий](https://github.com/ulianale/Diplom) командой `git clone https://github.com/ulianale/Diplom`
и открыть его в IDEA

### Запуск
1. Запускакм контейнер Docker командой в терминале:

   `docker-compose up`
2. Открываем новую вкладку терминала и запускаем приложение (jar файл) командой:

   * *для MySQL:*
   
   `java -jar ./artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app`

   * *для PostgreSQL:*
  
   `java -jar ./artifacts/aqa-shop.jar -P:jdbc.url=jdbc:postgresql//localhost:5432/app`
      
    **Важно!!!**
   перед запуском для *PostgreSQL* необходимо изменить код:

     в application.properties
   
      ![](./png/1.png)
   
     в ru/netology/web/data/SQLHelper

      ![](./png/2.png)
   

   

4. Открываем новую вкладку терминала и запускаем автотесты командой:
   
   `./gradlew clean test`
5. Для формирования и отображения отчета Allure вводим команду:

   `./gradlew allureserve`
    
    Отчет открывается автоматически в браузере после прогона тестов. 
    Останавливаем Allure, нажав `Ctrl+C`

## Завершение работы приложения, остановка и удаление контейнера
1. В терминале, где был запущен jar-файл, нажимаем `Ctrl+C`
2. В терминале, где запущен docker, пишем команду `docker-compose down`
   
