FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY ./target/*.jar target/app.jar


VOLUME /data

COPY ./src/main/java/com/example/data/carts.json /data/carts.json

COPY ./src/main/java/com/example/data/orders.json /data/orders.json

COPY ./src/main/java/com/example/data/products.json /data/products.json

COPY ./src/main/java/com/example/data/users.json /data/users.json

ENV spring.application.userDataPath=/data/users.json

ENV spring.application.productDataPath=/data/products.json

ENV spring.application.orderDataPath=/data/orders.json

ENV spring.application.cartDataPath=/data/carts.json

EXPOSE 8080

CMD ["java","-jar","/app/target/mini1.jar"]