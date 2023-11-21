FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /app

RUN apt-get update && \
    apt-get install -y \
        maven \
        && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ADD ./ .

RUN mvn dependency:resolve

RUN mvn package -DskipTests

FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /app

COPY --from=0 /app/target/shop_online-0.0.1-SNAPSHOT.jar ./shop_online.jar

ENTRYPOINT ["java", "-jar", "/app/shop_online.jar"]

EXPOSE 8088