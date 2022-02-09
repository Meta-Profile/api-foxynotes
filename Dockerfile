FROM gradle:7.3.3-jdk17-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app/api.jar

ENTRYPOINT ["java","-jar","/app/api.jar"]
