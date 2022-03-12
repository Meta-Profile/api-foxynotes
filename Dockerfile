FROM gradle:7.3.3-jdk17-alpine AS build

COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon


FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
