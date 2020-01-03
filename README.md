# Spring boot email microservice

> Simple email microservice REST API to send emails to multiple recipients

## Installation and running guide

### Installation

#### Clone repo

```aidl
git clone https://github.com/panakran/email-microservice.git
```

#### Configure properties file and env variables (or plain strings)

```
email.address=moo@gsmail.com
email.contenttype=text/html

spring.mail.host=${EMAIL_HOST}
spring.mail.port=${EMAIL_PORT}
spring.mail.username=${EMAIL_USERNAME}
spring.mail.password=${EMAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Run locally

#### Run spring boot maven goal

```cmd
mvn spring-boot:run
```

#### Open local url
[http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)

## REST API

POST /api/v1.0/email

request example
```
{
  "body": "<h2>Email content with html support</h2>",
  "recipients": [
    "adr1@yahoo.com",
    "adr2@yahoo.com"
  ],
  "sender": "Example sender",
  "title": "Example title"
}
```

response example

`STATUS 200`

## TODO

- [x] Multiple recipients
- [X] Dockerize
- [ ] Add templating support
- [ ] Add attachments support
- [ ] Add CC/BCC support