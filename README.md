# aliexpress-parser

This application parses 100 products from the [AliExpress site](https://flashdeals.aliexpress.com/en.htm) and writes their in CSV file. 

# Used technologies: 
* Java 1.8
* [Maven](http://maven.apache.org) (for building)
* [Jackson](https://github.com/FasterXML/jackson)
* [Apache HttpComponents](https://hc.apache.org)


# Build Instructions

This is a [Spring Boot](https://spring.io/projects/spring-boot) project. To run it from the command line, use this command

`mvn spring-boot:run`

This command will build the project, start the application within embedded Tomcat server, start [ngrok](https://ngrok.com/) tunnel to localhost and fire [Telegram WebHook](https://core.telegram.org/bots/api#setwebhook) to the bot which token is placed in `telegram.token` in `application.yml`. 

Once the webhook has successfully fired, you can start chatting.