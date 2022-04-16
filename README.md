# Open Liberty - JMS & IBM MQ

A sample application that uses Open Liberty to connect to IBM MQ to enqueue & dequeue messages.

## Setup

You'll need to install these requirements:

- JDK 17 - [Temurim](https://adoptium.net/installation/linux) is a good distro
- Latest Maven, it must be Java 17 compatible - https://maven.apache.org/install.html
- Docker to run IBM MQ

## Running it

Pull and start the IBM MQ docker container:

```sh
docker pull icr.io/ibm-messaging/mq:latest

docker run --env LICENSE=accept \
  --env MQ_QMGR_NAME=QM1 \
  --publish 1414:1414 \
  --publish 9443:9443 \
  --detach \
  --env MQ_APP_PASSWORD=passw0rd \
  --name QM1 icr.io/ibm-messaging/mq:latest
```
_As of today IBM won't maintain images in Docker Hub anymore (>=9.2.5.0)_

Install the dependencies and start OpenLibery:

```sh
mvn install
mvn liberty:dev
```

Once the server is running, send a message to the queue:

```sh
curl -X POST -d 'msg=test message' http://localhost:9080/libertymq/api/enqueue
```

## Versions

Implementation / tests performed with the latest versions of all components:

- openjdk 17.0.2 2022-01-18
- Java EE 8 - _Couldn't use Jakarta EE 9.1 due to [this issue](https://stackoverflow.com/questions/71888497/openliberty-jakartaee-with-ibm-mq-resorce-adapater-java-lang-noclassdeffounder) with IBM MQ Resource Adapter_
- Apache Maven 3.8.5
- OpenLibery 22.0.0.4
- IBM MQ & Resource Adapter 9.2.5.0

For convenience I'm versioning the adapter here but you should get your specific version from [Fix Central](https://www.ibm.com/support/fixcentral/).

## Sources

```
https://developer.ibm.com/tutorials/mq-connect-app-queue-manager-containers/
https://www.ibm.com/docs/en/was-liberty/nd?topic=dmal-deploying-jms-applications-liberty-use-mq-messaging-provider
https://developer.ibm.com/tutorials/mq-develop-mq-jms/
https://aguibert.github.io/openliberty-cheat-sheet/
```