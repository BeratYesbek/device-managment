FROM gradle:7.6.1-jdk17 as builder

RUN mkdir -p /app/source
COPY . /app/source

WORKDIR /app/source

RUN gradle clean build -x test

FROM eclipse-temurin:17-jre

COPY --from=builder /app/source/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
