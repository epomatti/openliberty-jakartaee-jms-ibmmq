# Open Liberty - JMS & IBM MQ

A sample application that uses Open Liberty to connect to IBM MQ to enqueue & dequeue messages.

## Setup

For this project you'll need to install the following requirements:

- JDK 21
- Maven
- Docker

## Running it

Pull and start the IBM MQ docker container:

> [!TIP]
> Since 9.2.5.0 new MQ images will be published to IBM's registry

```sh
docker pull icr.io/ibm-messaging/mq:latest

docker run --env LICENSE=accept \
  --env MQ_QMGR_NAME=QM1 \
  --publish 1414:1414 \
  --detach \
  --env MQ_APP_PASSWORD=passw0rd \
  --name QM1 icr.io/ibm-messaging/mq:latest
```

Install the dependencies and start OpenLiberty:

```sh
mvn install
mvn liberty:dev
```

Once the server is running, send a message to the queue:

```sh
curl -X POST -d 'msg=test message' http://localhost:9080/libertymq/api/enqueue
```

## Versions

Implementation / tests performed with the latest/LTS versions of all components:

- Temurin-21.0.4+7
- Jakarta EE 10.0.0
- Apache Maven 3.9.9
- OpenLiberty 24.0.0.12
- IBM MQ & Resource Adapter 9.4.1.1

ℹ️ For convenience I'm versioning the adapter here. But you should get your specific version from [Fix Central](https://www.ibm.com/support/fixcentral/), following guidelines such as in [this procedure](https://www.ibm.com/docs/en/ibm-mq/9.4?topic=adapter-installing-resource-in-liberty) for your particular version.

## Sources

```
https://developer.ibm.com/tutorials/mq-connect-app-queue-manager-containers/
https://www.ibm.com/docs/en/was-liberty/nd?topic=dmal-deploying-jms-applications-liberty-use-mq-messaging-provider
https://developer.ibm.com/tutorials/mq-develop-mq-jms/
https://aguibert.github.io/openliberty-cheat-sheet/
```
