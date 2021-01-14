# funbox-task
Инструкция по развертывания приложения в Docker:
  1) Выполнить в терминале: echo $JAVA_HOME
  1A) Если вывелась пустая строка выполнить команду: sudo apt install openjdk-8-jdk
  1Б) export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
  2) Если строка не пустая, выполнить команду из корневаой папки проекта: ./gradlew clean build -Penv=docker -x test
  3) Из корневой папки проекта: docker-compose up -d link
  4) Проверим что контейнеры понялись командой docker-compose ps
 
По умолчанию приложение разворачивается на 8088 порту

 
