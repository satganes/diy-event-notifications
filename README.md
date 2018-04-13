# Trade Message handling and processing

This is a Trade Message Processor Implementation. This has 3 components

  - Message Consumer - RESTful implementation to handle incoming messages
  - Message Processor - Process the information which is sent from above module, aggregate and process the same to render
  - UI - A Simple UI to render the messages

> The design is keeping in mind of the scalability, security and other aspects which
> are required for the enterprise applications.

### Technical Stack used,

The Technical Stack used for this project is,

* [Spring Web Flux](https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html) - Non-blocking, asynchronous and event-driven
* [Mongodb](https://www.mongodb.com/) - To store the information which comes as payload
* [RabbitMQ](https://www.rabbitmq.com/) - For publishing/subscribings of the events
* [Basic JS] - To render UI


### Installation

Need docker to run the Mongo and Rabbit MQ

```sh
$ cd diy-event-notifications
$ docker-compose up -d
```

For RESTful service to start running in port 8080

```sh
$ cd diy-event-notifications/message-consumer
$ mvn clean spring-boot:run
```

For Message Processor and UI to start running in 8081

```sh
$ cd diy-event-notifications/message-processor
$ mvn clean spring-boot:run
```

### How to send requests
Launch the client
```sh
http://localhost:8081/
Click on Connect to open the WebSocket
```
Basic Authentication username/password = rob/rob, admin/admin
For GET REST request

```sh
curl -X GET \
  http://localhost:8080/tradeMessages \
  -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: ec24f1da-4ff0-4213-8f54-aa30b4370c38'
```
And ideally the data populator would have populated with two basic entries. And the response should look like
```sh
[
    {
        "userId": "5ad056406621857ad870fbf9",
        "currencyFrom": "EUR",
        "currencyTo": "GBP",
        "amountSell": 100,
        "amountBuy": 50,
        "rate": 0.74,
        "timePlaced": "2018-04-13T12:33:28.548",
        "originatingCountry": "FR"
    },
    {
        "userId": "5ad056406621857ad870fbfa",
        "currencyFrom": "EUR",
        "currencyTo": "GBP",
        "amountSell": 200,
        "amountBuy": 50,
        "rate": 0.74,
        "timePlaced": "2018-04-13T12:33:28.582",
        "originatingCountry": "FR"
    }
]
```
For POST requests
```sh
curl -X POST \
    http://localhost:8080/tradeMessages \
    -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
    -H 'Cache-Control: no-cache' \
    -H 'Content-Type: application/json' \
    -H 'Postman-Token: b7e06373-2ffd-435e-b6db-be9d4074a81b' \
    -d '{"currencyFrom":"EUR","currencyTo":"GBP","amountSell":1001.0,"amountBuy":501.0,"rate":0.7,"timePlaced":"2018-04-09T16:49:43.765","originatingCountry":"FR"}'
```
And the response should look like
```sh
{
    "userId": "5ad056d76621857ad870fbff",
    "currencyFrom": "EUR5",
    "currencyTo": "GBP78",
    "amountSell": 1001,
    "amountBuy": 501,
    "rate": 0.7,
    "timePlaced": "2018-04-09T16:49:43.765",
    "originatingCountry": "FR"
}
```
For PUT requests
```sh
curl -X PUT \
      http://localhost:8080/tradeMessages/5ad056d76621857ad870fbff \
      -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
      -H 'Cache-Control: no-cache' \
      -H 'Content-Type: application/json' \
      -H 'Postman-Token: e5a873d7-61fe-4e50-a2a7-f0ff3ec5b0df' \
      -d '{"currencyFrom":"EUR5","currencyTo":"GBP78","amountSell":1001.0,"amountBuy":501.0,"rate":0.7,"timePlaced":"2018-04-09T16:49:43.765","originatingCountry":"FR"}'
```
And the response should look like
```sh
{
    "userId": "5ad056d76621857ad870fbff",
    "currencyFrom": "EUR5",
    "currencyTo": "GBP78",
    "amountSell": 1001,
    "amountBuy": 501,
    "rate": 0.7,
    "timePlaced": "2018-04-09T16:49:43.765",
    "originatingCountry": "FR"
}
```
And you must see the json sent to the UI using websocket.
