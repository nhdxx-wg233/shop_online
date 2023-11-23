FROM maven:3.9.5-eclipse-temurin-17

WORKDIR /app

ADD ./ .

RUN mvn package -DskipTests

FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /app

COPY --from=0 /app/target/shop_online-0.0.1-SNAPSHOT.jar ./shop_online.jar

ENTRYPOINT ["java", "-jar", "/app/shop_online.jar"]

EXPOSE 8088