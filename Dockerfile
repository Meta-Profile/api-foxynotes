FROM gradle:7.3.3-jdk17-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/api-0.0.1-SNAPSHOT.jar.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
