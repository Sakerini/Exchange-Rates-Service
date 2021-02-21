
# Exchange Currency Gif

This project is a Test Task for Junior Java Developer position.

This is a service for checking currency rates compared to yesterdays.
If the currency exchange rate we are looking for has risen compared to yesterday. 
The service returns positive gif ('rich'). If rate has fallen the service return negative gif ('Poor')
or if it is the same ('equal).

Requirements
========
* Spring Boot 2 + Java / Kotlin
* Request on HTTP endpoint
* Feign for external api calls
* Urls, Parameters and etc should be configured in property file
* Tests
* Gradle Build
* Docker container

Usage
=====
This service responses only to get request with the following endpoint
```
http://localhost:8080/api/get-currency-gif/{currency}
```

Valid currency path variables are 3-letter code:

RUB - Russian Ruble

BGN - Bulgarian lev

USD - United States Dollar

...

Example `http://localhost:8080/api/get-currency-gif/RUB`

Service's response:

```
{
    "id": "qCcoE7b6VHygF0GUFO",
    "url": "https://giphy.com/gifs/IntoAction-qCcoE7b6VHygF0GUFO"
}
``` 

Build
=====
To build and run this application locally, you'll need Git, Gradle and JDK installed on your computer. 

From your command line:

```
# Clone this repository
$ git clone https://github.com/Sakerini/Exchange-Rates-Service.git

# Go into the repository
$ cd Exchange-Rates-Service

# Build
$ ./gradlew build

# Run the app
$ java -jar build/libs/exchange-0.0.1-SNAPSHOT.jar
```

Docker instructions:

```
# Clone this repository
$ git clone https://github.com/Sakerini/Exchange-Rates-Service.git

# Build
$ ./gradlew build

# Build Docker Image
$ docker build -f Dockerfile -t exchangecurrencygif .

# Run Docker Container
$ docker run -p 8080:8080 exchangecurrencygif

```