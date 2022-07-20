# Open Liberty - JMS & IBM MQ

[![Maven](https://img.shields.io/github/workflow/status/epomatti/openliberty-jms-mq-example/Maven)](https://github.com/epomatti/openliberty-jms-mq-example/actions/workflows/maven.yml)

A sample application that uses Open Liberty to connect to IBM MQ to enqueue & dequeue messages.

> Updated to use Jakarta 9.1 and IBM MQ 9.3

## Setup

For this project you'll need to install the following requirements:

- JDK 17 (or later) - E.g. [Temurim](https://adoptium.net/installation/linux)
- Latest Maven, it must be Java 17 compatible - https://maven.apache.org/install.html
- Docker

## Running it

Pull and start the IBM MQ docker container:

```sh
docker pull icr.io/ibm-messaging/mq:latest

docker run --env LICENSE=accept \
  --env MQ_QMGR_NAME=QM1 \
  --publish 1414:1414 \
  --detach \
  --env MQ_APP_PASSWORD=passw0rd \
  --name QM1 icr.io/ibm-messaging/mq:latest
```
> _Since 9.2.5.0 new MQ images will be published to IBM's registry._

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

- openjdk 17.0.3 2022-04-19
- Jakarta EE 9.1
- Apache Maven 3.8.6
- OpenLibery 22.0.0.7
- IBM MQ & Resource Adapter 9.3.0.0

For convenience I'm versioning the adapter here. But you should get your specific version from [Fix Central](https://www.ibm.com/support/fixcentral/), following guidelines such as in [this procedure](https://www.ibm.com/docs/en/ibm-mq/9.3?topic=adapter-installing-resource-in-liberty) for your particular version.

## Sources

```
https://developer.ibm.com/tutorials/mq-connect-app-queue-manager-containers/
https://www.ibm.com/docs/en/was-liberty/nd?topic=dmal-deploying-jms-applications-liberty-use-mq-messaging-provider
https://developer.ibm.com/tutorials/mq-develop-mq-jms/
https://aguibert.github.io/openliberty-cheat-sheet/
```
