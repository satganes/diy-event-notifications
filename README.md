# diy-trade-messages


curl -X GET \
  http://localhost:8080/tradeMessages \
  -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: ec24f1da-4ff0-4213-8f54-aa30b4370c38'


curl -X POST \
    http://localhost:8080/tradeMessages \
    -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
    -H 'Cache-Control: no-cache' \
    -H 'Content-Type: application/json' \
    -H 'Postman-Token: b7e06373-2ffd-435e-b6db-be9d4074a81b' \
    -d '{"currencyFrom":"EUR","currencyTo":"GBP","amountSell":1001.0,"amountBuy":501.0,"rate":0.7,"timePlaced":"2018-04-09T16:49:43.765","originatingCountry":"FR"}'

curl -X PUT \
      http://localhost:8080/tradeMessages/5acf3422a41dde5f1719d1b2 \
      -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
      -H 'Cache-Control: no-cache' \
      -H 'Content-Type: application/json' \
      -H 'Postman-Token: e5a873d7-61fe-4e50-a2a7-f0ff3ec5b0df' \
      -d '{"currencyFrom":"EUR5","currencyTo":"GBP78","amountSell":1001.0,"amountBuy":501.0,"rate":0.7,"timePlaced":"2018-04-09T16:49:43.765","originatingCountry":"FR"}'


docker-compose up -d

http://localhost:8081/
Click on Connect to open the WebSocket
