FROM eclipse-temurin:17-jre-alpine

RUN apk update

RUN apk add --no-cache openjdk17-jre

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

WORKDIR /app

COPY build/libs/BingeBuddy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]